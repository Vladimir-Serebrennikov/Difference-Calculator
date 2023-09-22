package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;




public class Differ {
    public static String generate(String filepath1, String filepath2) throws IOException {
        TypeReference<HashMap<String, Object>> type = new TypeReference<>() { };
        Path file1 = Paths.get(filepath1).toAbsolutePath();
        Path file2 = Paths.get(filepath2).toAbsolutePath();

        ObjectMapper mapper = new ObjectMapper();
        final Map<String, Object> before = mapper.readValue(Files.readString(file1), type);
        final Map<String, Object> after = mapper.readValue(Files.readString(file2), type);

        Map<String, Object> mixed = new TreeMap<>();
        mixed.putAll(before);
        mixed.putAll(after);

        StringBuilder result = new StringBuilder();
        result.append("{ \n");
        mixed.keySet()
                .forEach(i -> {
                    if (!after.containsKey(i)) {
                        result.append(" - " + i + ": " + before.get(i) + "\n");
                    } else if (after.containsKey(i) && before.containsKey(i)) {
                        if (after.get(i).equals(before.get(i))) {
                            result.append("   " + i + ": " + after.get(i) + "\n");
                        } else {
                            result.append(" - " + i + ": " + before.get(i) + "\n");
                            result.append(" + " + i + ": " + after.get(i) + "\n");
                        }
                    } else if (!before.containsKey(i)) {
                        result.append(" + " + i + ": " + after.get(i) + "\n");
                    }
                });
        result.append("}\n");

        return result.toString();
    }
}
