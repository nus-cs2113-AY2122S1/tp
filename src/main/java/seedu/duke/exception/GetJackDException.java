package seedu.duke.exception;

/**
 * GetJackDException is a custom-made exception used throughout the app where the developer can add in their own error
 * messages by calling the constructor like so: GetJackDException("Error Message").
 */
public class GetJackDException extends Exception {
    public GetJackDException(String errorMessage) {
        super(errorMessage);
    }
}
