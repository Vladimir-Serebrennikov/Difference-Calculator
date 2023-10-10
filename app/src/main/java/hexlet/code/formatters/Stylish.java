package hexlet.code.formatters;

import hexlet.code.DifferBuild;

import java.util.List;
import java.util.Map;

public final class Stylish {
    public static String getFormatter(List<Map<String, Object>> data) {
        StringBuilder result = new StringBuilder("{\n");
        for (Map<String, Object> item : data) {
            var status = item.get(DifferBuild.KEY_STATUS).toString();
            var value1 = item.get(DifferBuild.KEY_VALUE1);
            var value2 = item.get(DifferBuild.KEY_VALUE2);
            var key = item.get(DifferBuild.KEY_NAME);
            switch (status) {
                case "changed" -> {
                    result.append("  - ");
                    result.append(key);
                    result.append(": ");
                    result.append(value1);
                    result.append("\n");
                    result.append("  + ");
                    result.append(key);
                    result.append(": ");
                    result.append(value2);
                    result.append("\n");
                }
                case "added" -> {
                    result.append("  + ");
                    result.append(key);
                    result.append(": ");
                    result.append(value2);
                    result.append("\n");
                }
                case "remove" -> {
                    result.append("  - ");
                    result.append(key);
                    result.append(": ");
                    result.append(value1);
                    result.append("\n");
                }
                case "nothing" -> {
                    result.append("    ");
                    result.append(key);
                    result.append(": ");
                    result.append(value1);
                    result.append("\n");
                }
                default -> throw new RuntimeException(status + " - status not supported.");
            }
        }
        return result.append("}").toString();
    }
}
