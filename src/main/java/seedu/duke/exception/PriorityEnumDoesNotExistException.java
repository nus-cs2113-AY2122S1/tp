package seedu.duke.exception;

public class PriorityEnumDoesNotExistException extends Exception {
    private static final String MESSAGE = "A number for that priority does not exist!";

    public PriorityEnumDoesNotExistException() {
        super(MESSAGE);
    }

}
