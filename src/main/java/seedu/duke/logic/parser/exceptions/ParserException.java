package seedu.duke.logic.parser.exceptions;

/**
 * Represents a general parsing exception, to be thrown from ParserUtils to the Parser classes.
 */
public class ParserException extends Exception {
    private static String errorMessage;

    /**
     * General constructor without error message.
     */
    public ParserException() {
    }

    public ParserException(String errorMessage) {
        super(errorMessage);
    }
}
