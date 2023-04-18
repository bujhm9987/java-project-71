package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class Parser {

    public static Map<String, String> toMap(String filepath) throws IOException {
        String nameFile = new File(filepath).getName();
        int indexDot = nameFile.indexOf(".");
        String fileExtension = nameFile.substring(indexDot + 1).toLowerCase();
        switch (fileExtension) {
            case ("json") -> {
                return jsonToMap(filepath);
            }
            case ("yaml") -> {
                return yamlToMap(filepath);
            }
            default -> {
                System.out.println("ERROR: File extension not supported");
                System.exit(1);
                return null;
            }
        }
    }

    private static Map<String, String> jsonToMap(String filepath) throws IOException {
        File file = new File(filepath);
        String fileData = Files.readString(file.toPath());
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> jsonParseFile = objectMapper.readValue(fileData, new TypeReference<>() { });
        return convertToMapStrings(jsonParseFile);
    }
    private static Map<String, String> yamlToMap(String filepath) throws IOException {
        File file = new File(filepath);
        String fileData = Files.readString(file.toPath());
        ObjectMapper objectMapper = new YAMLMapper();
        Map<String, Object> yamlParseFile = objectMapper.readValue(fileData, new TypeReference<>() { });
        return convertToMapStrings(yamlParseFile);
    }

    private static Map<String, String> convertToMapStrings(Map<String, Object> inputMap) {
        Map<String, String> outputMap = new HashMap<>();
        for (String key : inputMap.keySet()) {
            Object value = inputMap.get(key);
            outputMap.put(key, String.valueOf(value));
        }
        return outputMap;
    }
}
