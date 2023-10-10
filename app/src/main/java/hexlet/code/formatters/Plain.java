package hexlet.code.formatters;

import hexlet.code.DifferBuild;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public final class Plain {
    public static String getFormatter(List<Map<String, Object>> data) {

        StringJoiner result = new StringJoiner("\n");

        for (Map<String, Object> map: data) {

            var status = map.get(DifferBuild.KEY_STATUS).toString();
            var key = map.get(DifferBuild.KEY_NAME);
            var value1 = map.get(DifferBuild.KEY_VALUE1);
            var value2 = map.get(DifferBuild.KEY_VALUE2);

            if (status.equals(DifferBuild.STATUS_NOT_CHANGED)) {
                continue;
            }

            switch (status) {
                case DifferBuild.STATUS_CHANGED -> {
                    var plainValue1 = getValue(value1);
                    var plainValue2 = getValue(value2);
                    result.add("Property '" + key + "' was updated. From " + plainValue1 + " to " + plainValue2);
                }
                case DifferBuild.STATUS_DELETED -> result.add("Property '" + key + "' was removed");
                case DifferBuild.STATUS_ADDED -> {
                    var value = getValue(value1);
                    result.add("Property '" + key + "' was added with value: " + value);
                }
                default -> {
                }
            }
        }

        return result.toString();
    }

    public static String getValue(Object value) {

        if (value == null) {
            return null;
        } else if (value instanceof String) {
            return "'" + value + "'";
        }

        var stringValue = value.toString();
        var isComplexValue = (stringValue.startsWith("[") && stringValue.endsWith("]"))
                || (stringValue.startsWith("{") && stringValue.endsWith("}"));

        if (isComplexValue) {
            stringValue = "[complex value]";
        }

        return stringValue;
    }
}
