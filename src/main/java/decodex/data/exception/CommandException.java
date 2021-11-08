package decodex.data.exception;

/**
 * This exception is raised when an error during command execution occurs.
 */
public class CommandException extends Exception {

    public CommandException(String message) {
        super(message);
    }
}
