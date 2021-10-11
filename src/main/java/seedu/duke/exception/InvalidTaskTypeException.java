package seedu.duke.exception;

public class InvalidTaskTypeException extends Exception {
    private static final String MESSAGE = "Invalid parameter for task type inserted, '%s'.";

    public InvalidTaskTypeException(String taskType) {
        super(String.format(MESSAGE, taskType));
    }
}
