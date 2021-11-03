package seedu.duke.exception;

public class NoIndexException extends Exception {
    private static final String MESSAGE = "Command failed to parse argument '%s'.";

    public NoIndexException() {
        super(MESSAGE);
    }
}
