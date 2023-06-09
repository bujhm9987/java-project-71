package hexlet.code;

import hexlet.code.common.Keys;
import hexlet.code.common.Status;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Difference {

    public static Map<String, Map<Keys, Object>> toMapDiff(Map<String, Object> mapFile1,
                                                           Map<String, Object> mapFile2) {
        Set<String> mapsKeySet = new TreeSet<>(mapFile1.keySet());
        mapsKeySet.addAll(mapFile2.keySet());

        Map<String, Map<Keys, Object>> diffMap = new LinkedHashMap<>();

        for (String key : mapsKeySet) {
            Map<Keys, Object> mapValue = new TreeMap<>();

            if (!mapFile1.containsKey(key)) {
                mapValue.put(Keys.EVENT, Status.ADDED);
                mapValue.put(Keys.NEW_VALUE, mapFile2.get(key));
            } else if (!mapFile2.containsKey(key)) {
                mapValue.put(Keys.EVENT, Status.REMOVED);
                mapValue.put(Keys.OLD_VALUE, mapFile1.get(key));
            } else if (!Objects.equals(mapFile1.get(key), mapFile2.get(key))) {
                mapValue.put(Keys.EVENT, Status.UPDATED);
                mapValue.put(Keys.OLD_VALUE, mapFile1.get(key));
                mapValue.put(Keys.NEW_VALUE, mapFile2.get(key));
            } else {
                mapValue.put(Keys.EVENT, Status.UNCHANGED);
                mapValue.put(Keys.VALUE, mapFile2.get(key));
            }
            diffMap.put(key, mapValue);
        }
        return diffMap;
    }
}
