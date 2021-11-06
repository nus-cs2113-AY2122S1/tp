package expiryeliminator.data.exception;

//@@author bernardboey-reused
// Reused from
// https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/data/exception/DuplicateDataException.java
// with minor modifications

/**
 * Signals an error caused by duplicate data where there should be none.
 */
public class DuplicateDataException extends Exception {
    /**
     * Initialises exception without any error message.
     */
    public DuplicateDataException() {
        super();
    }

    /**
     * Initialises exception and stores error message.
     *
     * @param message Error message.
     */
    public DuplicateDataException(String message) {
        super(message);
    }
}
//@@author
