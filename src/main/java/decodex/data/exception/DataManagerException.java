package decodex.data.exception;

/**
 * This exception is raised when an error during access to the DataManager occurs.
 */
public class DataManagerException extends Exception {

    public DataManagerException(String message) {
        super(message);
    }
}
