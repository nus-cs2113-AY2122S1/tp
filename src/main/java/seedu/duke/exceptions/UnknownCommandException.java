package seedu.duke.exceptions;

public class UnknownCommandException extends Exception {

    @Override
    public String getMessage() {
        return "Unknown Command Entered. Please Try Again";
    }
}
