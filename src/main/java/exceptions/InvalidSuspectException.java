package exceptions;

public class InvalidSuspectException extends IndexOutOfBoundsException {
    public InvalidSuspectException(String message) {
        super(message);
    }
}
