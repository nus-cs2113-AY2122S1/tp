package seedu.exceptions;

public class EventException extends Exception {

    String message;

    public EventException(String input) {
        this.message = input;
    }

    public void printMessage() {
        System.out.println(message);
    }
}
