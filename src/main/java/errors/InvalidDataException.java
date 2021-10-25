package errors;

//@@author RemusTeo
/**
 * Represents the InvalidDataException thrown when data is corrupted or missing during loading from file.
 */
public class InvalidDataException extends Exception {
    public InvalidDataException(String message) {
        super(message);
    }
}
