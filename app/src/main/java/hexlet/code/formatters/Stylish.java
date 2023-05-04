package hexlet.code.formatters;

import hexlet.code.common.Keys;

import java.util.Map;

public class Stylish {
    public static String toStylish(Map<String, Map<Keys, Object>> inputDiff) {
        StringBuilder result = new StringBuilder();

        String add = "+";
        String del = "-";
        String space = " ";
        String nl = "\n";

        result.append("{\n");
        for (Map.Entry<String, Map<Keys, Object>> entry : inputDiff.entrySet()) {
            String key = entry.getKey();
            Map<Keys, Object> value = entry.getValue();
            String eventKey = value.get(Keys.EVENT).toString();
            String line = switch (eventKey) {
                case ("ADDED") -> String.format("  %s %s: %s%s", add, key, value.get(Keys.NEW_VALUE), nl);
                case ("REMOVED") -> String.format("  %s %s: %s%s", del, key, value.get(Keys.OLD_VALUE), nl);
                case ("UPDATED") -> String.format("  %s %s: %s%s", del, key, value.get(Keys.OLD_VALUE), nl)
                            + String.format("  %s %s: %s%s", add, key, value.get(Keys.NEW_VALUE), nl);
                default -> String.format("  %s %s: %s%s", space, key, value.get(Keys.VALUE), nl);
            };
            result.append(line);
        }
        result.append("}");
        return result.toString();
    }
}
