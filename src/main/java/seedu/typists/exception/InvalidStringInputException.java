package seedu.typists.exception;

public class InvalidStringInputException extends Throwable {
    @Override
    public String getMessage() {
        return "Invalid input given" + super.getMessage();
    }
}
