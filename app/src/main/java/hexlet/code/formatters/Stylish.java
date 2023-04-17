package hexlet.code.formatters;

import java.util.Map;

public class Stylish {
    public static String toStylish(Map<String, Map<String, String>> inputDiff) {
        StringBuilder result = new StringBuilder();

        String add = "+";
        String del = "-";
        String space = " ";
        String nl = "\n";

        result.append("{\n");
        for (Map.Entry<String, Map<String, String>> entry : inputDiff.entrySet()) {
            String key = entry.getKey();
            Map<String, String> value = entry.getValue();
            String eventKey = value.get("event");
            String line;
            switch (eventKey) {
                case ("added") -> {
                    line = String.format("  %s %s: %s%s", add, key, value.get("new_value"), nl);
                }
                case ("removed") -> {
                    line = String.format("  %s %s: %s%s", del, key, value.get("old_value"), nl);
                }
                case ("updated") -> {
                    line = String.format("  %s %s: %s%s", del, key, value.get("old_value"), nl)
                            + String.format("  %s %s: %s%s", add, key, value.get("new_value"), nl);
                }
                default -> {
                    line = String.format("  %s %s: %s%s", space, key, value.get("value"), nl);
                }
            }
            result.append(line);
        }
        result.append("}");
        return result.toString();
    }
}
