package seedu.typists.common.exception;

public class InvalidStringInputException extends Exception {
    @Override
    public String getMessage() {
        return "Invalid input given" + super.getMessage();
    }
}
