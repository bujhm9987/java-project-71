package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.util.Map;

public class Parser {

    public static Map<String, Object> toMap(String fileData, String fileExtension) throws IOException {
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
        return parseFile;
    }
}
