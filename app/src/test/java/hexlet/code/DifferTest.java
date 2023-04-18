package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {
    private static String testResourcesPath;
    private static String jsonTestFile1;
    private static String jsonTestFile2;
    private static String yamlTestFile1;
    private static String yamlTestFile2;

    @BeforeEach
    public void beforeEach() {
        testResourcesPath = new File("src/test/resources").getAbsolutePath();
        jsonTestFile1 = testResourcesPath + "/json/testfile1.json";
        jsonTestFile2 = testResourcesPath + "/json/testfile2.json";

        yamlTestFile1 = testResourcesPath + "/yaml/testfile1.yaml";
        yamlTestFile2 = testResourcesPath + "/yaml/testfile2.yaml";
    }
    private void compareFiles(String filePath1, String filePath2, String fileName, String format) throws IOException {
        Path pathExpectedFile = Paths.get(testResourcesPath + "/expected/" + fileName);
        String expectedResult = Files.readString(pathExpectedFile);
        String actualResult = Differ.generate(filePath1, filePath2, format);
        assertEquals(actualResult, expectedResult);
    }
    @Test
    public void testJsonStylish() throws IOException {
        String expectedFileName = "stylishjsontest";
        compareFiles(jsonTestFile1, jsonTestFile2, expectedFileName, "stylish");
    }

    @Test
    public void testYamlStylish() throws IOException {
        String expectedFileName = "stylishyamltest";
        compareFiles(yamlTestFile1, yamlTestFile2, expectedFileName, "stylish");
    }
    @Test
    public void testJsonPlain() throws IOException {
        String expectedFileName = "plainjsontest";
        compareFiles(jsonTestFile1, jsonTestFile2, expectedFileName, "plain");
    }
    /*@Test
    public void testYamlPlain() throws IOException {
        String expectedFileName = "plainyamltest";
        compareFiles(yamlTestFile1, yamlTestFile2, expectedFileName, "plain");
    }
    @Test
    public void testJsonJson() throws IOException {
        String expectedFileName = "jsonjsontest";
        compareFiles(jsonTestFile1, jsonTestFile2, expectedFileName, "json");
    }
    @Test
    public void testYamlJson() throws IOException {
        String expectedFileName = "jsonyamltest";
        compareFiles(yamlTestFile1, yamlTestFile2, expectedFileName, "json");
    }*/
}
