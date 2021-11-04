package seedu.duke.exception;

/**
 * General Exception that is thrown when creating Tasks in {@link seedu.duke.task.factory.TaskFactory}.
 * Catches specific exceptions and passes the Message of those exceptions to whatever called the
 * method.
 */
public class GetTaskFailedException extends Exception {

    public GetTaskFailedException(String message) {
        super(message);
    }
}
