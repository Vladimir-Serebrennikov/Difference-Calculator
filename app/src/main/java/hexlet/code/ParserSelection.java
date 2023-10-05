package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;

public final class ParserSelection {
    public static Map<String, Object> getParser(String extension, String content) throws JsonProcessingException {
        switch (extension) {
            case "json" -> {
                return new JsonParser().parseData(content);
            }
            case "yml" -> {
                return new YmlParser().parseData(content);
            }
            default -> throw new RuntimeException(extension + " - not supported.");
        }
    }
}
