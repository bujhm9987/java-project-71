package hexlet.code;

import hexlet.code.common.Keys;
import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.io.IOException;
import java.util.Map;

public class Formatter {
    public static String selectFormat(Map<String, Map<Keys, Object>> inputList, String format) throws IOException {
        return switch (format) {
            case ("stylish") -> Stylish.toStylish(inputList);
            case ("plain") -> Plain.toPlain(inputList);
            case ("json") -> Json.toJson(inputList);
            default -> throw new IOException("Incorrect format. Use 'stylish', 'plain' or 'json'.");
        };
    }
}
