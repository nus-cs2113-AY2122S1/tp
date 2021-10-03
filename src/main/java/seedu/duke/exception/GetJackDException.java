package seedu.duke.exception;

/**
 * Prints the current error message provided during an exception
 */
public class GetJackDException extends Exception {
    public GetJackDException(String errorMessage) {
        super(errorMessage);
    }
}
