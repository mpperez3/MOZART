package es.uvigo.ei.sing.MOZART.utils;


import com.google.common.util.concurrent.AtomicDouble;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import es.uvigo.ei.sing.MOZART.FinalPredictorModel;
import es.uvigo.ei.sing.MOZART.entities.SmileDescriptorsEntity;
import es.uvigo.ei.sing.MOZART.services.SmileDescriptorsService;
import javafx.util.Pair;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.openscience.cdk.IImplementationSpecification;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.qsar.DescriptorValue;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


@Component
@Scope("prototype")
@Log4j2
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PredictionTask implements Runnable {

    private String name = "PredictionTask";
    private String taskId;
    private List<String> smiles;
    private final AtomicDouble finished = new AtomicDouble(0d);
    private final List<Map<String, String>> outputData = new ArrayList<Map<String, String>>();
    private final CDKEngine CDKEngine = new CDKEngine();
    private Map<String, Map<String, String>> molecularDescriptors;
    private final List<Map<String, Object>> columns = new LinkedList<>();
    private List<Map<String, String>> inputDataMap = new ArrayList<>();
    private Exception exception;
    private Map<String, SmileDescriptorsEntity> smilesToSave = Collections.synchronizedMap(new HashMap<>());
    private SmileDescriptorsService smileDescriptorsService;
//
//    @Value("${predictor.maxSmiles}")
//    private int maxSmiles;


    public PredictionTask(final List<String> smilesToPredict, ApplicationContext applicationContext) {

        smileDescriptorsService = applicationContext.getBean(SmileDescriptorsService.class);

        smiles = smilesToPredict;
    }


    public PredictionTask(final MultipartFile file, final String taskId, ApplicationContext applicationContext) throws IOException {
        smileDescriptorsService = applicationContext.getBean(SmileDescriptorsService.class);

        try {
            String content = new String(file.getBytes(), StandardCharsets.UTF_8);

            if (content.contains("\t") && content.toLowerCase().contains("id")) {

                inputDataMap = Functions.readCSV(file.getInputStream());

                smiles = inputDataMap.stream().map(stringStringMap -> stringStringMap.get("SMILE")).collect(Collectors.toList());
                if (smiles.isEmpty())
                    smiles = inputDataMap.stream().map(stringStringMap -> stringStringMap.get("smile")).collect(Collectors.toList());

            } else {
                smiles = Arrays.asList(content.split("\n")).stream().map(s -> s.trim()).filter(s -> !s.isEmpty()).collect(Collectors.toList());

            }


            int maxSize = Constants.maxSmiles;
            if (smiles.size() < Constants.maxSmiles) {
                maxSize = smiles.size();
            }
            smiles = smiles.subList(0, maxSize);

            this.taskId = taskId;
        } catch (Exception e) {
            this.exception = e;
        }
    }

    @Override
    public void run() {

        try {


            log.info("Started prediction task id: {}", taskId);

            log.debug(name + " is running");

            finished.set(15d);
            molecularDescriptors = obtainMolecularDescriptors(smiles);
//            Thread.sleep(1000);
            finished.set(75d);
            Thread.sleep(3000);
            Map<String, Map<String, Pair<String, Double>>> activityMap = predictActivity(molecularDescriptors);

            final AtomicBoolean visibleSmile = new AtomicBoolean(true);
            if (!inputDataMap.isEmpty()) {
                columns.add(new HashMap<String, Object>() {{
                    put("title", "ID");
                    put("data", "compound_id");
                    put("visible", true);
                    put("width", "50px");
                }});
                visibleSmile.set(false);
            }

//            https://stackoverflow.com/questions/39693168/populate-datatable-from-ajax-json
            columns.add(new HashMap<String, Object>() {{
                put("title", "SMILE");
                put("data", "SMILE");
                put("visible", visibleSmile.get());
                put("width", "50px");
            }});
            ;
//            columns.add(new HashMap<String, Object>() {{
//                put("title", "ERROR");
//                put("data", "ERROR");
//                put("visible", false);
//
//            }});
            ;
            final AtomicInteger count = new AtomicInteger(0);

            Constants.constantDescriptors.entrySet().stream()
                    .forEach(stringMapEntry -> {
                        String key = "EC_" + stringMapEntry.getKey();
                        columns.add(new HashMap<String, Object>() {{
                            put("title", "EC " + stringMapEntry.getKey());
                            put("data", key.replace(".", ""));
                            put("visible", true);

//                            if (count.incrementAndGet() < 10) {
//
//                                put("visible", true);
//                            }


                        }});
                        ;
                    });

            count.set(0);
            smiles.stream().forEach(SMILE -> {

                Map<String, String> outputSmileData = new LinkedHashMap<>();
//                List<Object> outputSmileData = new LinkedList<>();


                if (!inputDataMap.isEmpty()) {
                    outputSmileData.put("compound_id", inputDataMap.get(count.getAndIncrement()).get("ID"));

                }

                outputSmileData.put("SMILE", SMILE);
//                    outputSmileData.put("ERROR", molecularDescriptors.get(SMILE).get("ERROR"));

//                outputSmileData.add( SMILE);
//                outputSmileData.add(molecularDescriptors.get(SMILE).get("success"));


                activityMap.get(SMILE).entrySet().stream().forEach(stringPairEntry -> {
                    String key = ("EC_" + stringPairEntry.getKey()).replace(".", "");
                    if (molecularDescriptors.get(SMILE).get("ERROR").equals("TRUE")) {
                        outputSmileData.put(key, "SMILE ERROR");
                    } else {
                        Double confidence = 0d;
                        if (Integer.parseInt(stringPairEntry.getValue().getKey()) > 0) {
                            confidence = stringPairEntry.getValue().getValue();
                        }
                        outputSmileData.put(key, String.valueOf(confidence));

                    }

                });

                outputData.add(outputSmileData);
            });


            finished.set(100d);

            if (!smilesToSave.isEmpty()) {
                smilesToSave.entrySet().stream().parallel().forEach(stringSmileDescriptorsEntityEntry -> {
                    try {
                        smileDescriptorsService.save(stringSmileDescriptorsEntityEntry.getValue());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
            this.exception = e;
            finished.set(-1d);


        }


        log.debug(name + " is ended");

    }


    private Map<String, Map<String, String>> obtainMolecularDescriptors(List<String> smilesList) {
        Map<String, Map<String, String>> output = Collections.synchronizedMap(new HashMap<>());


        final AtomicInteger loader = new AtomicInteger();
        int fivePercent = smilesList.size() / 20;
        Type empMapType = new TypeToken<Map<String, String>>() {
        }.getType();


        smilesList.stream()
                .parallel()
                .forEach(line -> {
                    line = line.trim();

                    if (!output.containsKey(line)) {
                        final Gson gson = new Gson();

                        if (smilesList.size() > 20 && loader.incrementAndGet() % fivePercent == 0) {
                            finished.set(finished.get() + ((5 * (loader.get() / fivePercent))) / 18);
                        }


                        Optional<SmileDescriptorsEntity> optionalSmileDesc = smileDescriptorsService.hasAny(line);
                        Map<String, String> descriptionsDefaultCopy;
                        if (optionalSmileDesc.isPresent() && !optionalSmileDesc.get().getJsonDescriptors().isEmpty()) {
                            Map<String, String> tempMap = gson.fromJson(optionalSmileDesc.get().getJsonDescriptors(), empMapType);
                            descriptionsDefaultCopy = new LinkedHashMap<>(tempMap);
                        } else {


                            descriptionsDefaultCopy = new LinkedHashMap<>(CDKEngine.getDescriptionsDefault());
                            descriptionsDefaultCopy.put("ERROR", "TRUE");
                            descriptionsDefaultCopy.put("SMILE", line);

                            try {

                                IAtomContainer molecule = CDKEngine.getSmilesParser().parseSmiles(line);

                                CDKEngine.getEngine().process(molecule);

                                for (IImplementationSpecification sp : CDKEngine.getEngine().getDescriptorSpecifications()) {
                                    int rcount = molecule.getProperty(sp) == null ?
                                            0 :
                                            ((DescriptorValue) (molecule.getProperty(sp))).getValue().length();
                                    if (rcount > 0) {
                                        String[] results =
                                                ((DescriptorValue) (molecule.getProperty(sp))).getValue().toString().split(",", rcount);
                                        DescriptorValue moleculeProperties = ((DescriptorValue) (molecule.getProperty(sp)));

                                        for (int i = 0; i < rcount; i++) {
                                            descriptionsDefaultCopy.put(moleculeProperties.getNames()[i], results[i]);
//                        System.out.println(moleculeProperties.getNames()[i] + " : " + results[i]);
                                        }
                                    }
                                }
                                descriptionsDefaultCopy.put("ERROR", "FALSE");


                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            if (!smilesToSave.containsKey(line) && descriptionsDefaultCopy.get("ERROR").equals("FALSE")) {
                                SmileDescriptorsEntity smileEntitiy = new SmileDescriptorsEntity(line, descriptionsDefaultCopy);
                                smilesToSave.put(line, smileEntitiy);

                            }
                        }
                        output.put(line, descriptionsDefaultCopy);
                    }
                });

        return output;
    }


    private Map<String, Map<String, Pair<String, Double>>> predictActivity(Map<String, Map<String, String>> molecularDescriptors) {

        final Map<String, Map<String, Pair<String, Double>>> output = new HashMap<>();

        molecularDescriptors.entrySet().forEach(stringMapEntry -> {
            output.put(stringMapEntry.getKey(), FinalPredictorModel.predictActivity(stringMapEntry.getValue()));
        });

        return output;
    }

}
