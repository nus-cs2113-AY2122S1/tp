package seedu.exceptions;

public class ModuleExistException extends Exception {
    String message;

    public ModuleExistException(String input) {
        this.message = input;
    }

    public void printMessage() {
        System.out.println(message);
    }
}
