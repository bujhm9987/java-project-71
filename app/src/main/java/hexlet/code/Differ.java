package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Differ {
    public static String generate(String filepath1, String filepath2) {
        Map<String, Object> mapFile1 = toMap(filepath1);
        Map<String, Object> mapFile2 = toMap(filepath2);

        return toResult(mapFile1, mapFile2);
    }

    public static Map<String, Object> toMap(String filepath) {
        File file = new File(filepath);

        try (BufferedReader fileReader = new BufferedReader(new FileReader(file))) {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(fileReader, new TypeReference<>() { });
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        return null;
    }

    public static String toResult(Map<String, Object> mapFile1, Map<String, Object> mapFile2) {

        Map<String, Object> unionMap = new TreeMap<>(mapFile1);
        unionMap.putAll(mapFile2);

        String strAdd = "  + ";
        String strDel = "  - ";
        String strSpace = "    ";
        StringBuilder result = new StringBuilder();
        result.append("{\n");

        for (Map.Entry<String, Object> entry : unionMap.entrySet()) {
            String entryMapKey = entry.getKey();
            Object entryMapValue = entry.getValue();
            String entryKeyValue = entry.getKey() + ": " + entry.getValue() + "\n";

            if (!mapFile1.containsKey(entryMapKey)) {
                result.append(strAdd).append(entryKeyValue);
            } else if (mapFile1.containsKey(entryMapKey) && !mapFile2.containsKey(entryMapKey)) {
                result.append(strDel).append(entryKeyValue);
            } else if (mapFile1.containsKey(entryMapKey) && mapFile2.containsKey(entryMapKey)
                    && !mapFile1.get(entryMapKey).equals(entryMapValue)) {
                result.append(strDel).append(entryMapKey).append(": ").append(mapFile1.get(entryMapKey)).append("\n");
                result.append(strAdd).append(entryKeyValue);
            } else {
                result.append(strSpace).append(entryKeyValue);
            }
        }
        result.append("}");

        return result.toString();
    }

}
