package seedu.duke.exceptions;

//@@author nvbinh15

/**
 * A base class that represents checked specific exceptions thrown by Click.
 */
public class ClickException extends Exception {

    /**
     * Default constructor.
     */
    public ClickException() {

    }

    /**
     * Constructor with a specified error message.
     *
     * @param message The error message to be printed when exception thrown.
     */
    public ClickException(String message) {
        super(message);
    }

}
