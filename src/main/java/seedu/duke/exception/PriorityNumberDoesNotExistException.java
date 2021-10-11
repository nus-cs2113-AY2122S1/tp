package seedu.duke.exception;

public class PriorityNumberDoesNotExistException extends Exception {
    private static final String MESSAGE = "A number for that priority does not exist!";

    public PriorityNumberDoesNotExistException() {
        super(MESSAGE);
    }

}
