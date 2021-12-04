package seedu.duke.storage.exceptions;

/**
 * Exception that is thrown when the data has been modified and become unreadable.
 */
public class InvalidDataException extends Exception {
    private static final String LS = System.lineSeparator();
    private static final String DIVIDER = "............................................................................"
             + "..............................";
    private static final String ERROR_MESSAGE = DIVIDER + LS
            + "There is an invalid line in %1$s. " + LS
            + "The values are either out of accepted range or we are unable to read it." + LS
            + "\"%2$s\" will not be loaded into the bot." + LS + DIVIDER;

    public InvalidDataException(String file, String inputData) {
        super(String.format(ERROR_MESSAGE, file, inputData));
    }

}
