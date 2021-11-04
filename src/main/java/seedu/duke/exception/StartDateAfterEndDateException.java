package seedu.duke.exception;

/**
 * Exception to be thrown when User attempts to set the {@link seedu.duke.task.type.Event}
 * {@link seedu.duke.task.type.Event#startDate} after {@link seedu.duke.task.type.Event#endDate}.
 */
public class StartDateAfterEndDateException extends Exception {
    private static String MESSAGE = "Start date provided cannot be after End date!";

    public StartDateAfterEndDateException() {
        super(MESSAGE);
    }
}
