package seedu.duke.exception;

public class ParseTaskFailedException extends Exception {
    private static String MESSAGE = "Parsing %s arguments failed.";

    public ParseTaskFailedException(String task) {
        super(String.format(MESSAGE, task));
    }
}
