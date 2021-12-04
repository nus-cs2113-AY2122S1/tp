package seedu.duke.logic.commands;

/**
 * Represents any command that cannot be executed due to invalid format.
 * Contains a String of message that describes the error.
 */
public class InvalidCommand extends Command {
    public final String errorMessage;

    public InvalidCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(this.errorMessage);
    }
}
