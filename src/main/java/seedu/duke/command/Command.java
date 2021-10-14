package seedu.duke.command;

import java.util.HashMap;

/**
 * Represents a command.
 */
public abstract class Command {
    public HashMap<String, String> commandArguments;

    public Command() {

    }

    public Command(HashMap<String, String> commandArguments) {
        this.commandArguments = commandArguments;
    }

    public abstract CommandResult executeCommand() throws Exception;

}
