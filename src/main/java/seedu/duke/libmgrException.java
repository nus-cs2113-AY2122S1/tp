package seedu.duke;

/**
 * Class that encapsulates an exception in libmgr.
 */
public class libmgrException extends Exception {

    /**
     * Constructor for a libmgr Exception.
     *
     * @param message The message to be printed when the exception is thrown.
     */
    public libmgrException(String message) {
        super(message);
    }
}
