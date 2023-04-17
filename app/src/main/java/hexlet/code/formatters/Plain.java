package hexlet.code.formatters;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class Plain {
    public static String toPlain(Map<String, Map<String, String>> inputDiff) {
        StringBuilder result = new StringBuilder();
        String strAdd = "Property %s was %s with value: %s%s";
        String strRem = "Property %s was %s%s";
        String strUpd = "Property %s was %s. From %s to %s%s";

        String nl = "\n";
        for (Map.Entry<String, Map<String, String>> entry : inputDiff.entrySet()) {
            String key = toStyleString(entry.getKey());
            Map<String, String> value = entry.getValue();
            String eventKey = value.get("event");
            String line = "";

            switch (eventKey) {
                case ("added") -> {
                    line = String.format(strAdd, key, eventKey, toStyleString(value.get("new_value")), nl);
                }
                case ("removed") -> {
                    line = String.format(strRem, key, eventKey, nl);
                }
                case ("updated") -> {
                    line = String.format(strUpd, key, eventKey, toStyleString(value.get("old_value")),
                            toStyleString(value.get("new_value")), nl);
                }
                default -> {
                }
            }
            result.append(line);
        }
        return result.substring(0, result.length() - 1);
    }

    private static String toStyleString(String string) {
        if (StringUtils.isNumeric(string)) {
            return string;
        } else if (string.startsWith("{") || string.startsWith("[")) {
            return "[complex value]";
        } else if (string.equals("true") || string.equals("false") || string.equals("null")) {
            return string;
        } else {
            return "'" + string + "'";
        }
    }
}
