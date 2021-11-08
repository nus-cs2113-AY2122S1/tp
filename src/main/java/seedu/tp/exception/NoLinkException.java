package seedu.tp.exception;

public class NoLinkException extends Exception {
    public static final String MESSAGE = "There is no link associated with the requested task.";

    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
