package seedu.exceptions;

public class UniModsException extends Exception {
    public UniModsException(String errorMessage) {
        super(errorMessage);
    }

    public void printMessage() {
        System.out.println(getMessage());
    }
}
