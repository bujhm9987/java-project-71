package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.common.Keys;

import java.util.Map;

public class Json {
    public static String toJson(Map<String, Map<Keys, Object>> inputDiff) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(inputDiff);
    }
}
