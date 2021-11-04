package seedu.duke.exception;

/**
 * Exception to be thrown when the user enters an invalid {@link seedu.duke.task.TypeEnum}.
 */
public class InvalidTaskTypeException extends Exception {
    private static final String MESSAGE = "Invalid parameter for task type inserted, '%s'.";

    private static final String INVALID_TASK_TYPE_MESSAGE = "[!] The task type you entered is not valid...\n"
            + "The following are examples of valid task types: {Todo, Deadline, Event, Lesson}";

    @Override
    public String toString() {
        return INVALID_TASK_TYPE_MESSAGE;
    }

    public InvalidTaskTypeException(String taskType) {
        super(String.format(MESSAGE, taskType));
    }
}
