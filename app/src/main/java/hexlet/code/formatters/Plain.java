package hexlet.code.formatters;

import hexlet.code.DifferBuild;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class Plain {
    public static String getFormatter(List<Map<String, Object>> data) {
        StringBuilder result = new StringBuilder();
        for (Map<String, Object> item : data) {
            var status = item.get(DifferBuild.KEY_STATUS).toString();
            var value1 = item.get(DifferBuild.KEY_VALUE1);
            var value2 = item.get(DifferBuild.KEY_VALUE2);
            var key = item.get(DifferBuild.KEY_NAME);
            switch (status) {
                case "changed" -> result.append("Property ")
                        .append(getValue(key))
                        .append(" was updated. From ")
                        .append(getValue(value1))
                        .append(" to ")
                        .append(getValue(value2))
                        .append("\n");
                case "added" -> result.append("Property ")
                        .append(getValue(key))
                        .append(" was added with value: ")
                        .append(getValue(value2))
                        .append("\n");
                case "remove" -> result.append("Property ")
                        .append(getValue(key))
                        .append(" was removed")
                        .append("\n");
                default -> {
                }
            }
        }
        return result.toString().trim();
    }

    private static <T> String getValue(T value) {
        if (value instanceof List || value instanceof Map) {
            return "[complex value]";
        } else if (value instanceof String) {
            return "'" + value + "'";
        } else {
            return Objects.toString(value);
        }
    }
}
