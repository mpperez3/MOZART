package es.uvigo.ei.sing.MOZART.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public  class Functions {


    public static String toCSV(List<Map<String, String>> list, String separator) {
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


    public static List<Map<String, String>> readCSV(File file) throws JsonProcessingException, IOException {
        List<Map<String, String>> response = new LinkedList<Map<String, String>>();
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = CsvSchema.emptySchema().withHeader().withColumnSeparator('\t');
        ;
        MappingIterator<Map<String, String>> iterator = mapper.reader(Map.class)
                .with(schema)
                .readValues(file);
        while (iterator.hasNext()) {
            Map<String, String> insensitiveMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
            insensitiveMap.putAll(iterator.next());
            response.add(insensitiveMap);
        }
        return response;
    }

    public static List<Map<String, String>> readCSV(InputStream file) throws JsonProcessingException, IOException {
        List<Map<String, String>> response = new LinkedList<Map<String, String>>();
        CsvMapper mapper = new CsvMapper()
                .enable(CsvParser.Feature.TRIM_SPACES)
                .enable(CsvParser.Feature.WRAP_AS_ARRAY);

        CsvSchema schema = CsvSchema.emptySchema().withHeader().withColumnSeparator('\t');
        ;
        MappingIterator<Map<String, String>> iterator = mapper.reader(Map.class)
                .with(schema)
                .readValues(file);
        while (iterator.hasNext()) {
            Map<String, String> insensitiveMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
            insensitiveMap.putAll(iterator.next());
            response.add(insensitiveMap);
        }
        return response;
    }

    public static String getResourceFileAsString(String fileName) {
        InputStream is = getResourceFileAsInputStream(fileName);
        if (is != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            return (String) reader.lines().collect(Collectors.joining(System.lineSeparator()));
        } else {
            throw new RuntimeException("resource not found");
        }
    }

    public static InputStream getResourceFileAsInputStream(String fileName) {
        ClassLoader classLoader = Functions.class.getClassLoader();
        return classLoader.getResourceAsStream(fileName);
    }

    public static String makeSHA1Hash(String input)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA1");
        md.reset();
        byte[] buffer = StringUtils.stripAccents(input).trim().toLowerCase().getBytes(StandardCharsets.UTF_8);
        md.update(buffer);
        byte[] digest = md.digest();

        String hexStr = "";
        for (int i = 0; i < digest.length; i++) {
            hexStr += Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1);
        }
        return hexStr;
    }


}
