package seedu.exceptions;

public class EditException extends Exception {

    String message;

    public EditException(String input) {
        this.message = input;
    }

    public void printMessage() {
        System.out.println(message);
    }

}
