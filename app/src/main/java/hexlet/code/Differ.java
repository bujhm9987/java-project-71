package hexlet.code;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class Differ {
    public static String generate2(String filepath1, String filepath2, String format) throws IOException {
        Map<String, String> mapFile1 = Parser.toMap(filepath1);
        Map<String, String> mapFile2 = Parser.toMap(filepath2);
        Map<String, Map<String, String>> resultDiff = toMapDiff(mapFile1, mapFile2);
        return Formatter.selectFormat(resultDiff, format);
    }

    public static Map<String, Map<String, String>> toMapDiff(Map<String, String> mapFile1,
                                                             Map<String, String> mapFile2) {
        Map<String, String> unionTreeMap = new TreeMap<>(mapFile1);
        unionTreeMap.putAll(mapFile2);
        String keyEvent = "event";
        String keyValue = "value";
        String keyOldValue = "old_value";
        String keyNewValue = "new_value";
        String valueAdd = "added";
        String valueDel = "removed";
        String valueUnchanged = "unchanged";
        String valueChange = "updated";

        Map<String, Map<String, String>> diffMap = new LinkedHashMap<>();

        for (Map.Entry<String, String> entry : unionTreeMap.entrySet()) {
            Map<String, String> mapValue = new LinkedHashMap<>();
            String key = entry.getKey();
            String value = entry.getValue();

            if (!mapFile1.containsKey(key)) {
                mapValue.put(keyEvent, valueAdd);
                mapValue.put(keyNewValue, value);
            } else if (mapFile1.containsKey(key) && !mapFile2.containsKey(key)) {
                mapValue.put(keyEvent, valueDel);
                mapValue.put(keyOldValue, value);
            } else if (mapFile1.containsKey(key) && mapFile2.containsKey(key)
                    && !mapFile1.get(key).equals(value)) {
                mapValue.put(keyEvent, valueChange);
                mapValue.put(keyOldValue, mapFile1.get(key));
                mapValue.put(keyNewValue, value);
            } else {
                mapValue.put(keyEvent, valueUnchanged);
                mapValue.put(keyValue, value);
            }
            diffMap.put(key, mapValue);
        }
        return diffMap;
    }
}
