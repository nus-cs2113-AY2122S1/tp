package seedu.duke.exception;

/**
 * Exception to be thrown when User does not provide a required argument when
 * creating a Task in superclasses of {@link seedu.duke.task.factory.TaskFactory}.
 */
public class RequiredArgmentNotProvidedException extends Exception {
    private static String MESSAGE = "Required argument '%s' was not provided when creating new %s.";

    public RequiredArgmentNotProvidedException(String argument, String task) {
        super(String.format(MESSAGE, argument, task));
    }
}
