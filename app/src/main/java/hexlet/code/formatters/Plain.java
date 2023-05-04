package hexlet.code.formatters;

import java.util.Map;

import hexlet.code.common.Keys;

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
            String eventKey = value.get(Keys.EVENT).toString().toLowerCase();
            String line = "";
            if (eventKey.equals("added")) {
                line = String.format(strAdd, key, eventKey, toStyleString(value.get(Keys.NEW_VALUE)), nl);
            } else if (eventKey.equals("removed")) {
                line = String.format(strRem, key, eventKey, nl);
            } else if (eventKey.equals("updated")) {
                line = String.format(strUpd, key, eventKey,
                        toStyleString(value.get(Keys.OLD_VALUE)), toStyleString(value.get(Keys.NEW_VALUE)), nl);
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
