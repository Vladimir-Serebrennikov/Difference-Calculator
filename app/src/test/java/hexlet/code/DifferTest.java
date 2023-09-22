package hexlet.code;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
public class DifferTest {
    private static String expect = "";
    @BeforeAll
    public static void init() throws IOException {
        expect = read("src/test/resources/expect.txt");
    }
    public static String read(String filepath) throws IOException {
        Path path = Paths.get(filepath);
        return Files.readString(path);
    }
    @Test
    public void expectTest() throws IOException {
        String actual = Differ.generate("filepath1.json", "filepath2.json");
        assertThat(actual).isEqualTo(expect);
    }

    @Test
    public void errorFakeFileTest() {
        Throwable throwable = assertThrows(Exception.class, () -> {
            Differ.generate("fakeFilepath1.json", "fakeFilepath2.json");
        });
        assertNotNull(throwable.getMessage());
    }
}
