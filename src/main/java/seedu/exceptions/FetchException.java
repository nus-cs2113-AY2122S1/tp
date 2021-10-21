package seedu.exceptions;

public class FetchException extends Exception {

    String message;

    public FetchException(String input) {
        this.message = input;
    }

    public void printMessage() {
        System.out.println(message);
    }
}
