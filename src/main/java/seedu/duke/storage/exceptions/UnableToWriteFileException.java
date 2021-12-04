package seedu.duke.storage.exceptions;

/**
 * An error that indicates if there is an environment error with the file it is trying to write to.
 */
public class UnableToWriteFileException extends Exception {
    private static final String ERROR_MESSAGE = "Unable to write to file, something went wrong while saving! "
            + "Please restart Fitbot and try again! :(";

    public UnableToWriteFileException() {
        super(ERROR_MESSAGE);
    }
}
