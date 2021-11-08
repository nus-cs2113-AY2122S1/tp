package seedu.duke.exceptions;

/**
 * This is the abstract exception which are raised when there is invalid input of user command.
 * The error message prompts the user the correct input of their command.
 */
public abstract class InvalidInputException extends DukeException {
    public InvalidInputException(String correctInputMessage) {
        super("Correct input format: " + correctInputMessage);
    }
}
