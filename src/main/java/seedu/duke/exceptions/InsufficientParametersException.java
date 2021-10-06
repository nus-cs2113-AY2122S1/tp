package seedu.duke.exceptions;

public class InsufficientParametersException extends Exception {

    @Override
    public String getMessage() {
        return "Insufficient parameters entered. Please try again";
    }
}
