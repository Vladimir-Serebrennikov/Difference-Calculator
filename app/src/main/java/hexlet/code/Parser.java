package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.Map;

public class Parser {
    public static Map<String, Object> parseData(String extension, String content) throws JsonProcessingException {
        ObjectMapper mapper;

        switch (extension) {
            case "json":
                mapper = new JsonMapper();
                break;
            case "yml":
                mapper = new YAMLMapper();
                break;
            default:
                throw new RuntimeException(extension + " - not supported.");
        }

        return mapper.readValue(content, new TypeReference<>() { });
    }
}
