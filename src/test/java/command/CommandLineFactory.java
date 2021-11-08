package command;

import picocli.CommandLine;

import java.io.PrintWriter;
import java.io.StringWriter;

public class CommandLineFactory {

    public static CommandLine getCmd() {
        CommandLine cmd = new CommandLine(new MainCommand());
        StringWriter sw = new StringWriter();
        cmd.setOut(new PrintWriter(sw));

        return cmd;
    }
}
