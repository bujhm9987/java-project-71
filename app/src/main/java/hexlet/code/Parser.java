package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.util.Map;

public class Parser {

    public static Map<String, Object> toMap(String fileData, String fileExtension) throws IOException {
        ObjectMapper mapper = switch (fileExtension) {
            case ("json") -> new ObjectMapper();
            case ("yaml"), ("yml") -> new YAMLMapper();
            default -> throw new IOException("File extension not supported");
        };
        return mapper.readValue(fileData, new TypeReference<>() { });
    }
}
