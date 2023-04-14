package hexlet.code;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        Map<String, String> mapFile1 = Parser.toMap(filepath1);
        Map<String, String> mapFile2 = Parser.toMap(filepath2);
        List<List<String>> resultDiff = toDiff(mapFile1, mapFile2);
        return Formatter.selectFormat(resultDiff, format);
    }

    public static List<List<String>> toDiff(Map<String, String> mapFile1, Map<String, String> mapFile2) {
        Map<String, String> unionTreeMap = new TreeMap<>(mapFile1);
        unionTreeMap.putAll(mapFile2);
        String valueAdd = "added";
        String valueDel = "removed";
        String valueStay = "stay";
        String valueChange = "updated";

        List<List<String>> list = new LinkedList<>();

        for (Map.Entry<String, String> entry : unionTreeMap.entrySet()) {
            List<String> lines = new LinkedList<>();
            String key = entry.getKey();
            String value = entry.getValue();

            if (!mapFile1.containsKey(key)) {
                lines.add(valueAdd);
                lines.add(key);
                lines.add(value);
            } else if (mapFile1.containsKey(key) && !mapFile2.containsKey(key)) {
                lines.add(valueDel);
                lines.add(key);
                lines.add(value);
            } else if (mapFile1.containsKey(key) && mapFile2.containsKey(key)
                    && !mapFile1.get(key).equals(value)) {
                List<String> lineDel = new LinkedList<>();
                lineDel.add(valueChange);
                lineDel.add(key);
                lineDel.add(mapFile1.get(key));
                list.add(lineDel);
                lines.add(valueChange);
                lines.add(key);
                lines.add(value);
            } else {
                lines.add(valueStay);
                lines.add(key);
                lines.add(value);
            }
            list.add(lines);
        }
        return list;
    }
}
