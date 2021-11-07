package terminus.exception;

/**
 * Invalid Command Exception class which handles exception when user command is not found.
 */
public class InvalidCommandException extends Exception {
    
    public InvalidCommandException(String message) {
        super(message);
    }
}
