package seedu.duke.exception;

public class InvalidPriorityException extends Exception {
    private static final String MESSAGE = "Invalid parameter for priority inserted, '%s'.";

    public InvalidPriorityException(String priority) {
        super(String.format(MESSAGE, priority));
    }
}
