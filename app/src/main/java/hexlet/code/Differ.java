package hexlet.code;

import hexlet.code.common.Keys;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        Map<String, Object> mapFile1 = readFile(filepath1);
        Map<String, Object> mapFile2 = readFile(filepath2);

        Map<String, Map<Keys, Object>> resultDiff = Difference.toMapDiff(mapFile1, mapFile2);
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

    private static Map<String, Object>  readFile(String filepath) throws IOException {
        File file = new File(filepath);
        String fileData = Files.readString(file.toPath());
        String fileExtension = getExtension(filepath);
        return Parser.toMap(fileData, fileExtension);
    }
}
