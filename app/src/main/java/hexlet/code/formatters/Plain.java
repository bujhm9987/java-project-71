package hexlet.code.formatters;

import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class Plain {
    public static String toPlain(List<List<String>> inputDiff) {
        StringBuilder result = new StringBuilder();
        String strAdd = "Property %s was %s with value: %s%s";
        String strRem = "Property %s was %s%s";
        String strUpd = "Property %s was %s. From %s to %s%s";

        boolean changeCheck = false;
        String valueFirst = "";

        String nl = "\n";

        for (List<String> entryList : inputDiff) {
            String action = entryList.get(0);
            String key = toStyleString(entryList.get(1));
            String value = toStyleString(entryList.get(2));

            switch (action) {
                case ("added") -> {
                    String line = String.format(strAdd, key, action, value, nl);
                    result.append(line);
                }
                case ("removed") -> {
                    String line = String.format(strRem, key, action, nl);
                    result.append(line);
                }
                case ("updated") -> {
                    if (changeCheck) {
                        String line = String.format(strUpd, key, action, valueFirst, value, nl);
                        changeCheck = false;
                        result.append(line);
                    } else {
                        valueFirst = value;
                        changeCheck = true;
                    }
                }
                default -> {
                }
            }
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
