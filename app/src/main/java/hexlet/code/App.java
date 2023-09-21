package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.IOException;
import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")

public class App implements Callable<Integer> {
    @Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
    private static String filepath1;
    @Parameters(index = "1", paramLabel = "filepath2", description = "path to second file")
    private static String filepath2;
    @Option(names = {"-f", "--format"},
            description = "output format [default: stylish]",
            paramLabel = "format")
    private String format;
    @Option(names = {"-h", "--help"}, usageHelp = true,
            description = "Show this help message and exit.")
    private boolean helpRequested;
    @Option(names = {"V", "--version"}, versionHelp = true,
            description = "Print version information and exit.")
    private boolean versionRequested;
    @Override
    public Integer call() throws Exception {
        try {
            System.out.println(Differ.generate(filepath1, filepath2));
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }
    public static void main(String[] args) throws IOException {
        App app = CommandLine.populateCommand(new App(), args);
        if (app.helpRequested) {
            CommandLine.usage(new App(), System.out);
        } else if (filepath1 != null && filepath2 != null) {
            int exitCode = new CommandLine(new App()).execute(args);
            System.exit(exitCode);
        }


    }

}


