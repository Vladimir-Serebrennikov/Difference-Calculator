package hexlet.code.formatters;

import hexlet.code.DifferBuild;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public final class Stylish {
    public static String getFormatter(List<Map<String, Object>> data) {
        StringJoiner result = new StringJoiner("\n  ", "{\n  ", "\n}");

        for (Map<String, Object> map: data) {
            var status = map.get(DifferBuild.KEY_STATUS).toString();
            var key = map.get(DifferBuild.KEY_NAME);
            var value1 = map.get(DifferBuild.KEY_VALUE1);
            var value2 = map.get(DifferBuild.KEY_VALUE2);

            switch (status) {
                case DifferBuild.STATUS_NOT_CHANGED -> result.add(" " + " " + key + ": " + value1);
                case DifferBuild.STATUS_CHANGED -> {
                    result.add("-" + " " + key + ": " + value1);
                    result.add("+" + " " + key + ": " + value2);
                }
                case DifferBuild.STATUS_DELETED -> result.add("-" + " " + key + ": " + value1);
                case DifferBuild.STATUS_ADDED -> result.add("+" + " " + key + ": " + value1);
                default -> throw new RuntimeException(status + " - status not supported.");
            }
        }

        return result.toString();
    }
}
