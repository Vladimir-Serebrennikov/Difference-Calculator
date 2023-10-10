package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public final class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        var data1 = readAndParseFile(filepath1);
        var data2 = readAndParseFile(filepath2);

        var differ = DifferBuild.getDiffer(data1, data2);

        return Formatter.format(differ, format);
    }

    public static String generate(String filepath1, String filepath2) throws IOException {
        return generate(filepath1, filepath2, "stylish");
    }

    public static Map<String, Object> readAndParseFile(String filepath) throws IOException {
        String extension = getExtension(filepath);
        String content = readFile(filepath);
        return Parser.parseData(extension, content);
    }

    public static String getExtension(String filepath) {
        return filepath.substring(filepath.lastIndexOf('.') + 1);
    }

    public static String readFile(String filepath) throws IOException {
        Path path = Paths.get(filepath).toAbsolutePath().normalize();
        return Files.readString(path);
    }
}
