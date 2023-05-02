package hexlet.code.formatters;

import java.util.Map;

import hexlet.code.common.Keys;
import hexlet.code.common.Status;

public class Plain {
    public static String toPlain(Map<String, Map<Keys, Object>> inputDiff) {
        StringBuilder result = new StringBuilder();
        String strAdd = "Property %s was %s with value: %s%s";
        String strRem = "Property %s was %s%s";
        String strUpd = "Property %s was %s. From %s to %s%s";

        String nl = "\n";
        for (Map.Entry<String, Map<Keys, Object>> entry : inputDiff.entrySet()) {
            Object key = toStyleString(entry.getKey());
            Map<Keys, Object> value = entry.getValue();
            Object eventKey = value.get(Keys.event);

            String line = "";

            if (eventKey.equals(Status.added)) {
                line = String.format(strAdd, key, eventKey, toStyleString(value.get(Keys.new_value)), nl);
            } else if (eventKey.equals(Status.removed)) {
                line = String.format(strRem, key, eventKey, nl);
            } else if (eventKey.equals(Status.updated)) {
                line = String.format(strUpd, key, eventKey, toStyleString(value.get(Keys.old_value)),
                        toStyleString(value.get(Keys.new_value)), nl);
            }
            result.append(line);
        }
        return result.substring(0, result.length() - 1);
    }

    private static Object toStyleString(Object value) {
        if (value instanceof Number || value instanceof Boolean || value == null) {
            return value;
        } else if (value instanceof String) {
            return "'" + value + "'";
        } else {
            return "[complex value]";
        }
    }
}
