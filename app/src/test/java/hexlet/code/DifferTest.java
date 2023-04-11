package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {
    private final Map<String, Object> testMap1 = new HashMap<>();
    private final Map<String, Object> testMap2 = new HashMap<>();

    @BeforeEach
    public void beforeEach() {
        testMap1.put("host", "hexlet.io");
        testMap1.put("timeout", 50);
        testMap1.put("proxy", "123.234.53.22");
        testMap1.put("follow", false);

        testMap2.put("timeout", 20);
        testMap2.put("verbose", true);
        testMap2.put("host", "hexlet.io");
    }

    @Test
    public void testToResult1() {
        String actualResult = Differ.toResult(testMap1, testMap2);
        String expectedResult = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testToResult2() {
        String actualResult = Differ.toResult(testMap2, testMap1);
        String expectedResult = """
                {
                  + follow: false
                    host: hexlet.io
                  + proxy: 123.234.53.22
                  - timeout: 20
                  + timeout: 50
                  - verbose: true
                }""";
        assertEquals(actualResult, expectedResult);
    }
    @Test
    public void testToResultEmpty() {
        Map<String, Object> testEmptyMap1 = new HashMap<>();
        Map<String, Object> testEmptyMap2 = new HashMap<>();
        String actualResult = Differ.toResult(testEmptyMap1, testEmptyMap2);
        String expectedResult = """
                {
                }""";
        assertEquals(actualResult, expectedResult);
    }
}
