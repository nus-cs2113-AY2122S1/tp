package seedu.duke.exception;

public class ParseDateFailedException extends Exception {

    private static final String MESSAGE = "String did not match date format: %s or invalid parameter entered.";

    public ParseDateFailedException(String dateFormat) {
        super(String.format(MESSAGE, dateFormat));
    }
}
