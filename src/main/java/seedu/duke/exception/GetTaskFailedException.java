package seedu.duke.exception;

public class GetTaskFailedException extends Exception {
    private static String MESSAGE = "getTask() method in %sFactory failed.";

    public GetTaskFailedException(String taskName) {
        super(String.format(MESSAGE, taskName));
    }
}
