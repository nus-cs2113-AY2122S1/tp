package terminus.exception;

/**
 * Invalid Time Format Exception class which handles exception when time format from user is invalid.
 */
public class InvalidTimeFormatException extends Exception {

    public InvalidTimeFormatException(String message) {
        super(message);
    }
}
