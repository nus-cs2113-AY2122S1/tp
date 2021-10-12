package seedu.duke.exception;

public class StartDateAfterEndDateException extends Exception {
    private static String MESSAGE = "Start date provided cannot be after End date!";

    public StartDateAfterEndDateException() {
        super(MESSAGE);
    }
}
