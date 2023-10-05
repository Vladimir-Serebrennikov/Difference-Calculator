package hexlet.code;
import java.util.Map;

public final class Formatter {
    public static String format(Map<String, LineDiff> data, String format) {
        return switch (format) {
            case "stylish" -> Stylish.getFormatter(data);
            default -> throw new RuntimeException(format + " - not supported.");
        };
    }

}
