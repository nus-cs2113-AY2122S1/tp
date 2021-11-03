package seedu.duke.exception;

public class NoTasksSpecifiedException extends Exception {
    private static final String MESSAGE = "No tasks were specified in the argument '%s'";

    public NoTasksSpecifiedException(String argument) {
        super(String.format(MESSAGE, argument));
    }
}
