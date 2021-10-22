package seedu.duke.exception;

public class InvalidTimePeriodException extends Exception {
    private static String MESSAGE = "Invalid time period entered: '%s'";

    public InvalidTimePeriodException(String argument) {
        super(String.format(MESSAGE, argument));
    }
}
