package hexlet.code;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public final class DifferBuild {
    public static Map<String, LineDiff> getDiffer(Map<String, Object> data1, Map<String, Object> data2) {
        Map<String, LineDiff> diff = new TreeMap<>();
        Set<String> keys = new TreeSet<>(data1.keySet());
        keys.addAll(data2.keySet());
        for (var key : keys) {
            Object value1 = data1.get(key);
            Object value2 = data2.get(key);

            if (value1 == null && value2 == null) {
                diff.put(key, new LineDiff("nothing", key, null, null));
            } else if (value1 == null) {
                diff.put(key, new LineDiff("added", key, null, value2));
            } else if (value2 == null) {
                diff.put(key, new LineDiff("remove", key, value1, null));
            } else if (Objects.equals(value1, value2)) {
                diff.put(key, new LineDiff("nothing", key, value1, value2));
            } else {
                diff.put(key, new LineDiff("changed", key, value1, value2));
            }
        }
        return diff;
    }

}

