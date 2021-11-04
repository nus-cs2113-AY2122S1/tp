package seedu.duke.command;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private static final String MESSAGE_NOT_NULL_ASSERTION = "Command Result message cannot be null!";

    private boolean isExited;
    private String message;

    public CommandResult(String message, boolean isExited) {
        this.isExited = isExited;
        this.message = message;
    }

    public void setExited(boolean exited) {
        isExited = exited;
    }

    public void setMessage(String message) {
        assert message != null : MESSAGE_NOT_NULL_ASSERTION;
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public boolean getIsExited() {
        return this.isExited;
    }

}
