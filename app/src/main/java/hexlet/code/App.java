package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.List;

@Command(
        name = "gendiff",
        description = "Compares two configuration files and shows a difference."
)
public class App implements Runnable {

    @Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this help message and exit.")
    private boolean helpRequested;

    @Option(names = {"-V", "--version"}, versionHelp = true, description = "Print version information and exit.")
    private boolean versionRequested;

    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    private String format = "stylish";

    @CommandLine.Parameters(index = "0", description = "path to first file")
    private String filepath1;

    @CommandLine.Parameters(index = "1", description = "path to second file")
    private String filepath2;

    public static void main(String[] args) {
        CommandLine.run(new App(), args);
    }

    @Override
    public void run() {
        if (helpRequested) {
            CommandLine.usage(this, System.out);
        } else if (versionRequested) {
            System.out.println("Version 1.0"); // Замените на вашу версию
        } else {
            // Добавьте логику вашей команды здесь
            System.out.println("Command logic goes here.");
            System.out.println("Format: " + format);
            System.out.println("Filepath 1: " + filepath1);
            System.out.println("Filepath 2: " + filepath2);
        }
    }
}


