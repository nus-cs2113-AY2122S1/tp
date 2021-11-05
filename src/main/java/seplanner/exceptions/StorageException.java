package seplanner.exceptions;

/**
 * Exception superclass to handle errors which mainly occurs while loading data stored in the files into the program.
 */
public class StorageException extends Exception {
    protected String message;

    public StorageException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
