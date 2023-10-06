package hexlet.code;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class DifferTest {
    private static String expectStylish = "";
    private static String expectPlain = "";
    private static String expectJson = "";

    @BeforeAll
    public static void initExpect() throws IOException {
        expectStylish = read("src/test/resources/stylish.txt");
        expectPlain = read("src/test/resources/plain.txt");
        expectJson = read("src/test/resources/json.txt");
    }

    public static String read(String filepath) throws IOException {
        Path path = Paths.get(filepath);
        return Files.readString(path);
    }

    @Test
    public void stylishTest() throws IOException {

        String filepath1 = "src/test/resources/file1.json";
        String filepath2 = "src/test/resources/file2.json";
        String actual = Differ.generate(filepath1, filepath2, "stylish");
        assertThat(actual).isEqualTo(expectStylish);
        actual = Differ.generate(filepath1, filepath2);
        assertThat(actual).isEqualTo(expectStylish);
    }

    @Test
    public void plainTest() throws IOException {
        String filepath1 = "src/test/resources/file1.json";
        String filepath2 = "src/test/resources/file2.json";
        String actual = Differ.generate(filepath1, filepath2, "plain");
        assertThat(actual).isEqualTo(expectPlain);
    }

    @Test
    public void jsonTest() throws IOException {
        String filepath1 = "src/test/resources/file1.json";
        String filepath2 = "src/test/resources/file2.json";
        String actual = Differ.generate(filepath1, filepath2, "json");
        assertThat(actual).isEqualTo(expectJson);
    }

    @Test
    public void testPath() {
        String filepath1 = "hexlet/test/resources";
        String filepath2 = "hexlet/test/resources";
        Throwable throwable = assertThrows(Exception.class, () -> {
            Differ.generate(filepath1, filepath2, "json");
        });
        assertNotNull(throwable.getMessage());
    }

    @Test
    public void testFormat() {
        String filepath1 = "src/test/resources/file1.json";
        String filepath2 = "src/test/resources/file2.json";
        Exception exception = assertThrows(RuntimeException.class, () -> {
            Differ.generate(filepath1, filepath2, "test");
        });
        String expectedMessage = "Unknown format test";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
