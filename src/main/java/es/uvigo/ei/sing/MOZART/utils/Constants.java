package es.uvigo.ei.sing.MOZART.utils;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Constants {


    public static Map<String, Map<String, Double>> constantDescriptors = new HashMap<>();

    @Value("${predictor.maxSmiles}")
    public static int maxSmiles=100;

    static {
        try {
            ClassPathResource classPathResource = new ClassPathResource(
                    "/AVG_values.tsv");
            InputStream inputStream = classPathResource.getInputStream();


            List<Map<String, String>> csvMap = Functions.readCSV(inputStream);
            csvMap.stream().filter(stringStringMap -> stringStringMap.containsKey("EC")).forEach(stringStringMap -> {
                Map<String, Double> innerMap = new HashMap<>();

                innerMap.putAll(stringStringMap.entrySet().stream()
                        .collect(Collectors.toMap(e -> e.getKey(), e -> {
                            if (NumberUtils.isCreatable(e.getValue()))
                                return Double.parseDouble(e.getValue());
                            else return 0d;
                        })));

                constantDescriptors.put(stringStringMap.get("EC"), innerMap);


                constantDescriptors = constantDescriptors.entrySet().stream()
                        .sorted(Map.Entry.<String, Map<String, Double>>comparingByKey()
//                                .reversed()
                        )
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

            });
            constantDescriptors.size();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
