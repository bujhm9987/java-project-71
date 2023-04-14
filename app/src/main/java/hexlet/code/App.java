package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.IOException;
import java.util.concurrent.Callable;

@Command(name = "gendiff",
        version = "Help v1",
        description = "Compares two configuration files and shows a difference.",
        mixinStandardHelpOptions = true)

class App implements Callable<Integer> {

    @Option(names = {"-f", "--format"}, defaultValue = "stylish",
            description = "output format [default: stylish]")
    String format;

    @Parameters(paramLabel = "filepath1", /*defaultValue = "Hello, picocli",*/
            description = "path to first file")
    String filepath1;

    @Parameters(paramLabel = "filepath2", /*defaultValue = "Hello, picocli",*/
            description = "path to second file")
    String filepath2;

    @Override
    public Integer call() throws IOException {
        System.out.println(Differ.generate(filepath1, filepath2, format));
        return 0;
    }

    public static void main(String... args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}

