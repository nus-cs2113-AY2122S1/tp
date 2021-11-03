package seedu.duke.exception;

public class BrowseFailException extends Exception {
    public static final String MESSAGE = "Cannot browse: %s";
    String msg;

    @Override
    public String getMessage() {
        return String.format(MESSAGE, msg);
    }

    public BrowseFailException(String msg) {
        super(msg);
        this.msg = msg;
    }
}
