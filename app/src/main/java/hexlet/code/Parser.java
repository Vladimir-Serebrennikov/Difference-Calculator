package hexlet.code;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.TreeMap;

public class Parser {
    public static String generate(String filePath1, String filePath2) throws IOException {
        Yaml yaml = new Yaml();

        String yamlContent1 = Files.readString(Path.of(filePath1));
        String yamlContent2 = Files.readString(Path.of(filePath2));

        Map<String, Object> data1 = yaml.load(yamlContent1);
        Map<String, Object> data2 = yaml.load(yamlContent2);

        Map<String, Object> mixed = new TreeMap<>();
        mixed.putAll(data1);
        mixed.putAll(data2);

        StringBuilder result = new StringBuilder();
        result.append("{\n");
        mixed.keySet()
                .forEach(key -> {
                    if (!data2.containsKey(key)) {
                        result.append(" - " + key + ": " + data1.get(key) + "\n");
                    } else if (data2.containsKey(key) && data1.containsKey(key)) {
                        if (data2.get(key).equals(data1.get(key))) {
                            result.append("   " + key + ": " + data2.get(key) + "\n");
                        } else {
                            result.append(" - " + key + ": " + data1.get(key) + "\n");
                            result.append(" + " + key + ": " + data2.get(key) + "\n");
                        }
                    } else if (!data1.containsKey(key)) {
                        result.append(" + " + key + ": " + data2.get(key) + "\n");
                    }
                });
        result.append("}\n");

        return result.toString();
    }
}
