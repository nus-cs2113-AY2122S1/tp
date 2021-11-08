package seedu.tp.exception;

/**
 * Exception to be thrown when User's input in
 * {@link seedu.tp.command.DeleteCommand} cannot be parsed.
 */
public class ParseTaskIndexesFailedException extends Exception {
    private static final String MESSAGE = "Command failed to parse argument '%s'.";

    public ParseTaskIndexesFailedException() {
        super(MESSAGE);
    }
}
