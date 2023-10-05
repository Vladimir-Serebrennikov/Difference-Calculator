package hexlet.code;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public final class DifferBuild {
    public static Map<String, DiffClass> getDiffer(Map<String, Object> data1, Map<String, Object> data2) {
        Map<String, DiffClass> diff = new TreeMap<>();
        Set<String> keys = new TreeSet<>(data1.keySet());
        keys.addAll(data2.keySet());
        for (var key : keys) {
            if (!data1.containsKey(key) && data2.containsKey(key)) {
                diff.put(key, new DiffClass("added", key, data1.get(key), data2.get(key)));
            } else if (data1.containsKey(key) && !data2.containsKey(key)) {
                diff.put(key, new DiffClass("remove", key, data1.get(key), data2.get(key)));
            } else if (data1.containsKey(key) && Objects.equals(data1.get(key), data2.get(key))) {
                diff.put(key, new DiffClass("nothing", key, data1.get(key), data2.get(key)));
            } else {
                diff.put(key, new DiffClass("changed", key, data1.get(key), data2.get(key)));
            }
        }
        return diff;
    }

}

