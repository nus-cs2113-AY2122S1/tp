package seedu.duke.exception;

public class InvalidRecurrenceException extends Exception {
    private static final String MESSAGE = "Invalid parameter for recurrence inserted, '%s'.";

    public InvalidRecurrenceException(String recurrence) {
        super(String.format(MESSAGE, recurrence));
    }
}
