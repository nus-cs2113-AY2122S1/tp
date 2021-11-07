package seedu.tp.command;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private static final String MESSAGE_NOT_NULL_ASSERTION = "Command Result message cannot be null!";

    private final boolean isExited;
    private final String message;

    public CommandResult(String message, boolean isExited) {
        assert message != null : MESSAGE_NOT_NULL_ASSERTION;
        this.isExited = isExited;
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public boolean getIsExited() {
        return this.isExited;
    }

}
