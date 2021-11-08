package exceptions;

public class InvalidClueException extends IndexOutOfBoundsException {
    public InvalidClueException(String message) {
        super(message);
    }
}
