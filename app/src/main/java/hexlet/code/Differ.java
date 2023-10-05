package hexlet.code;



import java.io.IOException;

public final class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        var data1 = FilesPath.getPath(filepath1);
        var data2 = FilesPath.getPath(filepath2);

        var differ = DifferBuild.getDiffer(data1, data2);

        return Formatter.format(differ, format);
    }

    public static String generate(String filepath1, String filepath2) throws IOException {
        return generate(filepath1, filepath2, "stylish");
    }
}
