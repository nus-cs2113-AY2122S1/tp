package seedu.typists.exception;

public class InvalidArticleException extends Throwable {
    @Override
    public String getMessage() {
        return "Invalid article given" + super.getMessage();
    }
}
