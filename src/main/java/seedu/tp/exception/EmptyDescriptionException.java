package seedu.tp.exception;

/**
 * Exception to be thrown when the user tries to enter an empty
 * description in {@link seedu.tp.command.EditCommand}.
 */
public class EmptyDescriptionException extends Exception {
    private static final String MESAGE = "Description cannot be empty!";

    public EmptyDescriptionException() {
        super(MESAGE);
    }
}
