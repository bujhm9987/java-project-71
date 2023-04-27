package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Parser {

    public static Map<String, String> toMap(String fileData, String fileExtension) throws IOException {
        Map<String, Object> parseFile;
        switch (fileExtension) {
            case ("json") -> {
                ObjectMapper objectMapper = new ObjectMapper();
                parseFile = objectMapper.readValue(fileData, new TypeReference<>() { });
            }
            case ("yaml"), ("yml") -> {
                ObjectMapper objectMapper = new YAMLMapper();
                parseFile = objectMapper.readValue(fileData, new TypeReference<>() { });
            }
            default -> {
                throw new IOException("File extension not supported");
            }
        }
        return convertToMapStrings(parseFile);
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
