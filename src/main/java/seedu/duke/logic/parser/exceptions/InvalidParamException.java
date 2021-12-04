package seedu.duke.logic.parser.exceptions;

/**
 * Represents an error where the parameter specified is invalid for the command.
 */
public class InvalidParamException extends Exception {
    private static String errorMessage;

    /**
     * General constructor without error message.
     */
    public InvalidParamException() {
    }

    public InvalidParamException(String errorMessage) {
        super(errorMessage);
    }
}
