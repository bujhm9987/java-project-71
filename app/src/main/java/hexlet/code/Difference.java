package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Difference {
    private enum Keys {
        EVENT("event"),
        NEW_VALUE("new_value"),
        OLD_VALUE("old_value"),
        VALUE("value");
        private final String value;
        Keys(String value) {
            this.value = value;
        }
    }

    private enum Status {
        ADD("added"),
        DEL("removed"),
        UNCHANGED("unchanged"),
        CHANGED("updated");
        private final String value;
        Status(String value) {
            this.value = value;
        }
    }
    public static Map<String, Map<String, String>> toMapDiff(Map<String, String> mapFile1,
                                                             Map<String, String> mapFile2) {
        Set<String> mapsKeySet = new TreeSet<>(mapFile1.keySet());
        mapsKeySet.addAll(mapFile2.keySet());

        Map<String, Map<String, String>> diffMap = new LinkedHashMap<>();
        //Использовал Map, т.к. при выводе результата в формате JSON с использованием Map в качесве
        //входного элемента objectMapper создает более правильный и читаемый результат.

        for (String key : mapsKeySet) {
            Map<String, String> mapValue = new TreeMap<>();

            if (!mapFile1.containsKey(key)) {
                mapValue.put(Keys.EVENT.value, Status.ADD.value);
                mapValue.put(Keys.NEW_VALUE.value, mapFile2.get(key));
            } else if (!mapFile2.containsKey(key)) {
                mapValue.put(Keys.EVENT.value, Status.DEL.value);
                mapValue.put(Keys.OLD_VALUE.value, mapFile1.get(key));
            } else if (!Objects.equals(mapFile1.get(key), mapFile2.get(key))) {
                mapValue.put(Keys.EVENT.value, Status.CHANGED.value);
                mapValue.put(Keys.OLD_VALUE.value, mapFile1.get(key));
                mapValue.put(Keys.NEW_VALUE.value, mapFile2.get(key));
            } else {
                mapValue.put(Keys.EVENT.value, Status.UNCHANGED.value);
                mapValue.put(Keys.VALUE.value, mapFile2.get(key));
            }
            diffMap.put(key, mapValue);
        }
        return diffMap;
    }
}
