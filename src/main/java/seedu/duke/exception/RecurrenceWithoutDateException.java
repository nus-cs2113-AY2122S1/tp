package seedu.duke.exception;

public class RecurrenceWithoutDateException extends Exception {
    private static final String MESSAGE = "Cannot have a recurring Task with no date!";

    public RecurrenceWithoutDateException() {
        super(MESSAGE);
    }
}
