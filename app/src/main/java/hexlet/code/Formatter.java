package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.List;
import java.util.Map;

public final class Formatter {
    public static String format(List<Map<String, Object>> data, String format) {
        return switch (format) {
            case "stylish" -> Stylish.getFormatter(data);
            case "plain" -> Plain.getFormatter(data);
            case "json" -> Json.getFormatter(data);
            default -> throw new RuntimeException("Unknown format " + format);
        };
    }

}
