package hexlet.code;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        String fileData1 = readFile(filepath1);
        String fileData2 = readFile(filepath2);
        String fileExtension1 = getExtension(filepath1);
        String fileExtension2 = getExtension(filepath2);
        Map<String, String> mapFile1 = convertToMapStrings(Parser.toMap(fileData1, fileExtension1));
        Map<String, String> mapFile2 = convertToMapStrings(Parser.toMap(fileData2, fileExtension2));

        Map<String, Map<String, String>> resultDiff = Difference.toMapDiff(mapFile1, mapFile2);
        return Formatter.selectFormat(resultDiff, format);
    }

    public static String generate(String filepath1, String filepath2) throws IOException {
        return Differ.generate(filepath1, filepath2, "stylish");
    }

    private static String getExtension(String filepath) {
        String nameFile = new File(filepath).getName();
        int indexDot = nameFile.indexOf(".");
        return nameFile.substring(indexDot + 1).toLowerCase();
    }

    private static String readFile(String filepath) throws IOException {
        File file = new File(filepath);
        return Files.readString(file.toPath());
    }

    private static Map<String, String> convertToMapStrings(Map<String, Object> inputMap) {
        Map<String, String> outputMap = new HashMap<>();
        for (String key : inputMap.keySet()) {
            Object value = inputMap.get(key);
            outputMap.put(key, String.valueOf(value));
        }
        return outputMap;
    }
}
