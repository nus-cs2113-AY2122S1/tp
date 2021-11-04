package seedu.duke.exception;

/**
 * Exception to be thrown when User's input in {@link seedu.duke.command.DeleteCommand}
 * does not have any indexes specified.
 */
public class NoTasksSpecifiedException extends Exception {
    private static final String MESSAGE = "No tasks were specified in the argument '%s'";

    public NoTasksSpecifiedException(String argument) {
        super(String.format(MESSAGE, argument));
    }
}
