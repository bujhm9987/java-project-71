package hexlet.code.formatters;

import hexlet.code.common.Keys;
import hexlet.code.common.Status;

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
            Object eventKey = value.get(Keys.event);
            String line;
            if (eventKey.equals(Status.added)) {
                line = String.format("  %s %s: %s%s", add, key, value.get(Keys.new_value), nl);
            } else if (eventKey.equals(Status.removed)) {
                line = String.format("  %s %s: %s%s", del, key, value.get(Keys.old_value), nl);
            } else if (eventKey.equals(Status.updated)) {
                line = String.format("  %s %s: %s%s", del, key, value.get(Keys.old_value), nl)
                        + String.format("  %s %s: %s%s", add, key, value.get(Keys.new_value), nl);
            } else {
                line = String.format("  %s %s: %s%s", space, key, value.get(Keys.value), nl);
            }
            result.append(line);
        }
        result.append("}");
        return result.toString();
    }
}
