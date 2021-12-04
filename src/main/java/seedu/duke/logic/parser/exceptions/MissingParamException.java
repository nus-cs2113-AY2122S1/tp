package seedu.duke.logic.parser.exceptions;

/**
 * Represents an error where a parameter required for the command is not specified.
 */
public class MissingParamException extends Exception {
    private static String errorMessage;

    /**
     * General constructor without error message.
     */
    public MissingParamException() {
    }

    /**
     * Constructor with specific error message.
     *
     * @param errorMessage Error message to be shown when exception is caught and printed
     */
    public MissingParamException(String errorMessage) {
        super(errorMessage);
        MissingParamException.errorMessage = errorMessage;
    }
}
