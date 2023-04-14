package hexlet.code.formatters;

import java.util.List;

public class Stylish {
    public static String toStylish(List<List<String>> inputDiff) {
        StringBuilder result = new StringBuilder();

        String add = "+";
        String del = "-";
        String space = " ";
        String nl = "\n";
        boolean changeCheck = false;

        result.append("{\n");
        for (List<String> entryList : inputDiff) {
            String action = entryList.get(0);
            String key = entryList.get(1);
            String value = entryList.get(2);

            switch (action) {
                case ("added") -> {
                    String line = String.format("  %s %s: %s%s", add, key, value, nl);
                    result.append(line);
                }
                case ("removed") -> {
                    String line = String.format("  %s %s: %s%s", del, key, value, nl);
                    result.append(line);
                }
                case ("updated") -> {
                    if (changeCheck) {
                        String line = String.format("  %s %s: %s%s", add, key, value, nl);
                        result.append(line);
                        changeCheck = false;
                    } else {
                        String line = String.format("  %s %s: %s%s", del, key, value, nl);
                        result.append(line);
                        changeCheck = true;
                    }
                }
                default -> {
                    String line = String.format("  %s %s: %s%s", space, key, value, nl);
                    result.append(line);
                }
            }
        }
        result.append("}");
        return result.toString();
    }
}
