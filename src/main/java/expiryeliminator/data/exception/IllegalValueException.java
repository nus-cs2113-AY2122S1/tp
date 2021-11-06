package expiryeliminator.data.exception;

//@@author bernardboey-reused
// Reused from
// https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/data/exception/IllegalValueException.java
// with minor modifications

/**
 * Signals that some given data does not fulfill some constraints.
 */
public class IllegalValueException extends Exception {
    /**
     * Initialises exception without any error message.
     */
    public IllegalValueException() {
        super();
    }

    /**
     * Initialises exception and stores error message.
     *
     * @param message Error message.
     */
    public IllegalValueException(String message) {
        super(message);
    }
}
//@@author
