package seedu.duke.exception;

/**
 * Exception to be thrown when user's input in {@link seedu.duke.command.ListCommand}
 * specified a task id of a task that is non-recurring when trying to print the recurrence of the task.
 */
public class TaskIsNonRecurringException extends Exception {

    private static final String TASK_IS_NON_RECURRING_MESSAGE = "[!] The task indicated is non-recurring, "
            + "please choose another task id...\n";

    @Override
    public String toString() {
        return TASK_IS_NON_RECURRING_MESSAGE;
    }

}
