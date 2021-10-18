package seedu.duke.command;

import java.util.HashMap;

/**
 * Represents a command.
 */
public abstract class Command {
    public static final String MAIN_ARGUMENT = "mainArgument";

    private static final String USAGE_REGEX = "Usage: %s";

    private final CommandEnum command;
    protected final HashMap<String, String> commandArguments;

    public Command(CommandEnum command) {
        this.command = command;
        commandArguments = null;
    }

    public Command(CommandEnum command, HashMap<String, String> commandArguments) {
        this.command = command;
        this.commandArguments = commandArguments;
    }

    protected String getMainArgument() {
        return commandArguments.get(MAIN_ARGUMENT);
    }

    protected String getUsage() {
        return String.format(USAGE_REGEX, command.getUsage());
    }

    public abstract CommandResult executeCommand() throws Exception;

}
