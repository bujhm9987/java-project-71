package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {
    private static final String TESTS_ABS_PATH = new File("src/test/resources").getAbsolutePath();
    private static String expectedJson;
    private static String expectedPlain;
    private static String expectedStylish;

    @BeforeEach
    public void beforeEach() throws IOException {
        expectedJson = getFile("expectedjson");
        expectedPlain = getFile("expectedplain");
        expectedStylish = getFile("expectedstylish");
    }
    private String getFile(String filePath) throws IOException {
        Path pathFile = Paths.get(TESTS_ABS_PATH + "/" + filePath);
        return Files.readString(pathFile);
    }
    @ParameterizedTest(name = "Input {0} files - output json format")
    @ValueSource (strings = {"json", "yaml"})
    public void testJson(String format) throws IOException {
        String testFile1 = TESTS_ABS_PATH + "/testfile1." + format;
        String testFile2 = TESTS_ABS_PATH + "/testfile2." + format;
        String actualResult = Differ.generate(testFile1, testFile2, "json");
        assertEquals(actualResult, expectedJson);
    }
    @ParameterizedTest(name = "Input {0} files - output plain format")
    @ValueSource (strings = {"json", "yaml"})
    public void testPlain(String format) throws IOException {
        String testFile1 = TESTS_ABS_PATH + "/testfile1." + format;
        String testFile2 = TESTS_ABS_PATH + "/testfile2." + format;
        String actualResult = Differ.generate(testFile1, testFile2, "plain");
        assertEquals(actualResult, expectedPlain);
    }
    @ParameterizedTest(name = "Input {0} files - output stylish format")
    @ValueSource (strings = {"json", "yaml"})
    public void testStylish(String format) throws IOException {
        String testFile1 = TESTS_ABS_PATH + "/testfile1." + format;
        String testFile2 = TESTS_ABS_PATH + "/testfile2." + format;
        String actualResult = Differ.generate(testFile1, testFile2, "stylish");
        assertEquals(actualResult, expectedStylish);
    }
    @ParameterizedTest(name = "Input {0} files - output default format")
    @ValueSource (strings = {"json", "yaml"})
    public void testDefault(String format) throws IOException {
        String testFile1 = TESTS_ABS_PATH + "/testfile1." + format;
        String testFile2 = TESTS_ABS_PATH + "/testfile2." + format;
        String actualResult = Differ.generate(testFile1, testFile2);
        assertEquals(actualResult, expectedStylish);
    }
}
