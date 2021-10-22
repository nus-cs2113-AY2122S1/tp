package seedu.duke.exception;

public class InvalidTaskIndexException extends Exception {
    private static final String MESSAGE = "Task index '%d' does not correspond to any task!";

    public InvalidTaskIndexException(int index) {
        super(String.format(MESSAGE, index));
    }
}
