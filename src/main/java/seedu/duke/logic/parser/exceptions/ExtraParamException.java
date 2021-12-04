package seedu.duke.logic.parser.exceptions;

/**
 * Represents an error where there are extra parameters specified for the command.
 */
public class ExtraParamException extends Exception {
    private static final String ERROR_MESSAGE =  "Error! There were unnecessary parameters detected. "
            + "Please follow the command format and try again!";

    /**
     * General constructor that constructs an exception with a standard error message for extra parameters.
     */
    public ExtraParamException() {
        super(ERROR_MESSAGE);
    }
}
