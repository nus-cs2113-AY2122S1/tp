package expiryeliminator.parser.exception;

/**
 * Signals an error caused by an invalid argument format.
 */
public class InvalidArgFormatException extends Exception {
    /**
     * Initialises exception and stores error message.
     *
     * @param message Error message.
     */
    public InvalidArgFormatException(String message) {
        super(message);
    }
}
