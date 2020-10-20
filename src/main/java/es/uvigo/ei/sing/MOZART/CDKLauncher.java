package es.uvigo.ei.sing.MOZART;


import com.google.common.util.concurrent.AtomicDouble;
import lombok.extern.log4j.Log4j2;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openscience.cdk.DefaultChemObjectBuilder;
import org.openscience.cdk.IImplementationSpecification;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.qsar.DescriptorEngine;
import org.openscience.cdk.qsar.DescriptorValue;
import org.openscience.cdk.qsar.descriptors.molecular.*;
import org.openscience.cdk.silent.SilentChemObjectBuilder;
import org.openscience.cdk.smiles.SmilesParser;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


@Log4j2
public class CDKLauncher {
    static {
        System.setProperty("log4j.configurationFile", "E:\\Documentos\\NetBeansProjects\\ChemicalSmile\\src\\main\\resources\\log4j2-spring.xml");
    }

    private static final Logger LOGGER = LogManager.getLogger(CDKLauncher.class);

    public static void main(String[] args) {
        SmilesParser smilesParser = new SmilesParser(SilentChemObjectBuilder.getInstance());
        try {


//            StringWriter writer = new StringWriter();
//            CMLWriter cmlWriter = new CMLWriter(writer);
//            cmlWriter.registerCustomizer(new QSARCustomizer());
//            jar://C:/Users/mpperez3/.m2/repository/org/openscience/cdk/cdk-qsarmolecular/2.3/cdk-qsarmolecular-2.3.jar!/org/openscience/cdk/qsar/descriptors/molecular


//            http://www.rsc.org/suppdata/c9/np/c9np00064j/c9np00064j1.pdf
            DescriptorEngine engine = new DescriptorEngine(Arrays.asList(
//                    AcidicGroupCountDescriptor.class.getName(),
//                    ALOGPDescriptor.class.getName(),
//                    AminoAcidCountDescriptor.class.getName(),
//                    APolDescriptor.class.getName(),
//                    AromaticAtomsCountDescriptor.class.getName(),
//                    AromaticBondsCountDescriptor.class.getName(),
//                    AtomCountDescriptor.class.getName(),
                    AutocorrelationDescriptorCharge.class.getName(),
                    AutocorrelationDescriptorMass.class.getName(),
                    AutocorrelationDescriptorPolarizability.class.getName(),
//                    BasicGroupCountDescriptor.class.getName(),
//                    BCUTDescriptor.class.getName(),
//                    BondCountDescriptor.class.getName(),
//                    BPolDescriptor.class.getName(),
//                    CarbonTypesDescriptor.class.getName(),
                    ChiChainDescriptor.class.getName(),
                    ChiClusterDescriptor.class.getName(),
                    ChiPathClusterDescriptor.class.getName(),
                    ChiPathDescriptor.class.getName(),
////                    CPSADescriptor.class.getName(),
//                    EccentricConnectivityIndexDescriptor.class.getName(),
//                    FMFDescriptor.class.getName(),
//                    FractionalPSADescriptor.class.getName(),
//                    FragmentComplexityDescriptor.class.getName(),
////                    GravitationalIndexDescriptor.class.getName(),
//                    HBondAcceptorCountDescriptor.class.getName(),
//                    HBondDonorCountDescriptor.class.getName(),
//                    HybridizationRatioDescriptor.class.getName(),
////                    IPMolecularLearningDescriptor.class.getName(),
//                    KappaShapeIndicesDescriptor.class.getName(),
//                    KierHallSmartsDescriptor.class.getName(),
//                    LargestChainDescriptor.class.getName(),
//                    LargestPiSystemDescriptor.class.getName(),
//                    LengthOverBreadthDescriptor.class.getName(),
//                    LongestAliphaticChainDescriptor.class.getName(),
//                    MannholdLogPDescriptor.class.getName(),
//                    MDEDescriptor.class.getName(),
////                    MomentOfInertiaDescriptor.class.getName(),
//                    PetitjeanNumberDescriptor.class.getName(),
//                    PetitjeanShapeIndexDescriptor.class.getName(),
//                    RotatableBondsCountDescriptor.class.getName(),
//                    RuleOfFiveDescriptor.class.getName(),
//                    SmallRingDescriptor.class.getName(),
                    TPSADescriptor.class.getName(),
////                    VABCDescriptor.class.getName(),
//                    VAdjMaDescriptor.class.getName(),
//                    WeightDescriptor.class.getName(),
                    WeightedPathDescriptor.class.getName()
//                    WHIMDescriptor.class.getName(),
//                    WienerNumbersDescriptor.class.getName(),
//                    XLogPDescriptor.class.getName(),
//                    ZagrebIndexDescriptor.class.getName(),
//
//                    CPSADescriptor.class.getName(),
////                    GravitationalIndexDescriptor.class.getName(),
////                    LengthOverBreadthDescriptor.class.getName(),
////                    MomentOfInertiaDescriptor.class.getName(),
//                    PetitjeanShapeIndexDescriptor.class.getName()
////                    WHIMDescriptor.class.getName()


            ),
                    DefaultChemObjectBuilder.getInstance());

//            DescriptorEngine engine = new DescriptorEngine(org.openscience.cdk.qsar.IMolecularDescriptor.class, DefaultChemObjectBuilder.getInstance());

            Set<String> allNames = new HashSet<>();
            engine.getDescriptorInstances().stream().forEach(
                    iDescriptor ->

                            allNames.addAll(Arrays.asList(iDescriptor.getDescriptorNames()))

            );

            List<Map<String, String>> output = Collections.synchronizedList(new ArrayList<>());
            Map<String, String> descriptionsDefault = new LinkedHashMap<>();
            descriptionsDefault.put("SMILE", "ERROR");

            allNames.stream().forEach(s -> {
                descriptionsDefault.computeIfAbsent(s, key -> "0");
            });

            String uri = "E:\\Documentos\\Google Drive\\PostDoctorado\\Riccardo\\smyles.txt";

            final String sourceFile = uri.substring(uri.lastIndexOf('\\') + 1);
            if (!Paths.get(uri).toFile().exists()) {
//                log.error("file {} not exist", uri);

            }
//            File file =Paths.get(uri).toFile();
//            insertTermAndCategory(true, defaultCategory, defaultCategory.getName(), sourceFile);

            final List<String> lines = Files.readAllLines(Paths.get(uri), Charset.forName("utf-8"));
            final AtomicInteger loader = new AtomicInteger();
            int fivePercent = lines.size() / 20;
            final AtomicDouble finished = new AtomicDouble(0d);

            Map<String, Map<String, String>> resultSmileMap = Collections.synchronizedMap(new LinkedHashMap<>());
            lines.stream().forEach(line -> {
                resultSmileMap.computeIfAbsent(line, s -> new LinkedHashMap<>());
            });


            descriptionsDefault.clear();
            descriptionsDefault.put("VP-6", "");
            descriptionsDefault.put("<VP-6>", "");
            descriptionsDefault.put("DVP-6", "");
            descriptionsDefault.put("SPC-5", "");
            descriptionsDefault.put("<SPC-5>", "");
            descriptionsDefault.put("DSPC-5", "");
            descriptionsDefault.put("<WTPT-3>", "");
            descriptionsDefault.put("<WTPT-4>", "");
            descriptionsDefault.put("DWTPT-3", "");
            descriptionsDefault.put("DWTPT-4", "");
            descriptionsDefault.put("WTPT-3", "");
            descriptionsDefault.put("WTPT-4", "");
            descriptionsDefault.put("<TopoPSA>", "");
            descriptionsDefault.put("DTopoPSA", "");
            descriptionsDefault.put("TopoPSA", "");
            descriptionsDefault.put("<ATSp1>", "");
            descriptionsDefault.put("<ATSp2>", "");
            descriptionsDefault.put("DATSp1", "");
            descriptionsDefault.put("DATSp2", "");
            descriptionsDefault.put("ATSp1", "");
            descriptionsDefault.put("ATSp2", "");
            descriptionsDefault.put("<ATSc4>", "");
            descriptionsDefault.put("DATSc4", "");
            descriptionsDefault.put("ATSc4", "");
            descriptionsDefault.put("VC-6", "");
            descriptionsDefault.put("<VC-6>", "");
            descriptionsDefault.put("DVC-6", "");
            descriptionsDefault.put("DVP-0", "");
            descriptionsDefault.put("VP-0", "");
            descriptionsDefault.put("<VP-0>", "");
            descriptionsDefault.put("DATSm4", "");
            descriptionsDefault.put("DATSm5", "");
            descriptionsDefault.put("ATSm4", "");
            descriptionsDefault.put("ATSm5", "");
            descriptionsDefault.put("<ATSm4>", "");
            descriptionsDefault.put("<ATSm5>", "");
            descriptionsDefault.put("<VPC-5>", "");
            descriptionsDefault.put("DVPC-5", "");
            descriptionsDefault.put("VPC-5", "");

            lines.stream()
                    .parallel()
                    .forEach(line -> {
                        Map<String, String> descriptionsDefaultCopy = new LinkedHashMap<>(descriptionsDefault);


                        try {


                            if (lines.size() > 20 && loader.incrementAndGet() % fivePercent == 0) {
                                finished.set(finished.get() + ((5 * (loader.get() / fivePercent))) / 100);
                                System.out.println("Parsed " + loader.get() + " ||| " + finished.get() + "% completed.");
                            }


//            for (String line : lines) {
                            IAtomContainer molecule = smilesParser.parseSmiles(line.trim());

                            engine.process(molecule);
                            descriptionsDefaultCopy.put("SMILE", line.trim());

                            for (IImplementationSpecification sp : engine.getDescriptorSpecifications()) {
                                int rcount = molecule.getProperty(sp) == null ?
                                        0 :
                                        ((DescriptorValue) (molecule.getProperty(sp))).getValue().length();
                                if (rcount > 0) {
                                    String[] results =
                                            ((DescriptorValue) (molecule.getProperty(sp))).getValue().toString().split(",", rcount);
                                    DescriptorValue moleculeProperties = ((DescriptorValue) (molecule.getProperty(sp)));

                                    for (int i = 0; i < rcount; i++) {
                                        if (descriptionsDefaultCopy.containsKey(moleculeProperties.getNames()[i]))
                                            descriptionsDefaultCopy.put(moleculeProperties.getNames()[i], results[i]);
//                        System.out.println(moleculeProperties.getNames()[i] + " : " + results[i]);
                                    }
                                }
                            }
//                    System.out.println("SMILE = " + line.trim());

                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("SMILE = " + line.trim());

                        }
                        resultSmileMap.put(line, descriptionsDefaultCopy);
//            }
                    });
//            DescriptorEngine engine = new DescriptorEngine(DescriptorEngine.MOLECULAR, null);
            NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);
            DecimalFormat df = (DecimalFormat) nf;
            System.out.println("Parsed " + lines.size() + " ||| " + 100 + "% completed.");

            lines.stream().forEach(line -> {
                output.add(resultSmileMap.get(line));
            });


            String content = toCSV(output, "\t");
            Files.write(Paths.get("E:\\Documentos\\Google Drive\\PostDoctorado\\Riccardo\\JavasmileCDKOut.tsv"), content.getBytes());


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private static String toCSV(List<Map<String, String>> list, String separator) {
        List<String> headers = list.stream().flatMap(map -> map.keySet().stream()).distinct().collect(Collectors.toList());
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < headers.size(); i++) {
            sb.append(headers.get(i));
            sb.append(i == headers.size() - 1 ? "\n" : separator);
        }
        for (Map<String, String> map : list) {
            for (int i = 0; i < headers.size(); i++) {
//                sb.append(map.get(headers.get(i)).replace(',', '.'));
                sb.append(map.get(headers.get(i)));
                sb.append(i == headers.size() - 1 ? "\n" : separator);
            }
        }
        return sb.toString();
    }
}
