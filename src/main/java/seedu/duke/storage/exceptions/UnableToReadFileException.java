package seedu.duke.storage.exceptions;

/**
 * An error that is thrown when the bot is unable to access the file or create the file.
 */
public class UnableToReadFileException extends Exception {
    private static final String ERROR_MESSAGE = " file is inaccessible due to an environment error."
            + System.lineSeparator() + "Please restart Fitbot and try again! :(";

    public UnableToReadFileException(String fileName) {
        super(fileName + ERROR_MESSAGE);
    }
}
