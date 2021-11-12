package seedu.duke.exceptions;

public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
        System.out.println(errorMessage);
    }
}