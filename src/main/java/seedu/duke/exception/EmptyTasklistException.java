package seedu.duke.exception;

/**
 * Exception to be thrown when {@link seedu.duke.task.taskmanager.TaskManager#isEmpty()}.
 */
public class EmptyTasklistException extends Exception {

    private static final String MESSAGE = "[!] There are currently no tasks in your tasklist...\n";

    public EmptyTasklistException() {
        super(MESSAGE);
    }

    @Override
    public String toString() {
        return MESSAGE;
    }

}
