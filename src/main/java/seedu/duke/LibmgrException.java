package seedu.duke;

/**
 * Class that encapsulates an exception in libmgr.
 */
public class LibmgrException extends Exception {

    /**
     * Constructor for a libmgr Exception.
     *
     * @param message The message to be printed when the exception is thrown.
     */
    public LibmgrException(String message) {
        super(message);
    }
}
