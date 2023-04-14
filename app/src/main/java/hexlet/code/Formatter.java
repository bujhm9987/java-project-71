package hexlet.code;

import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.List;

public class Formatter {
    public static String selectFormat(List<List<String>> inputList, String format) {
        switch (format) {
            case ("stylish") -> {
                return Stylish.toStylish(inputList);
            }
            case ("plain") -> {
                return Plain.toPlain(inputList);
            }
            default -> {
                return "Incorrect format";
            }
        }
    }
}
