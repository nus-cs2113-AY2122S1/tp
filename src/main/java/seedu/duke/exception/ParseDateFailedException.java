package seedu.duke.exception;

public class ParseDateFailedException extends Exception {

    private static final String MESSAGE = "String did not match date format: %s";

    public ParseDateFailedException(String dateFormat) {
        super(String.format(MESSAGE, dateFormat));
    }
}
