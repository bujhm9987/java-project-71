package hexlet.code;

import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;
import hexlet.code.formatters.Json;

import java.io.IOException;
import java.util.Map;

public class Formatter {
    public static String selectFormat(Map<String, Map<String, String>> inputList, String format) throws IOException {
        switch (format) {
            case ("stylish") -> {
                return Stylish.toStylish(inputList);
            }
            case ("plain") -> {
                return Plain.toPlain(inputList);
            }
            case ("json") -> {
                return Json.toJson(inputList);
            }
            default -> {
                throw new IOException("Incorrect format. Use 'stylish', 'plain' or 'json'.");
            }
        }
    }
}
