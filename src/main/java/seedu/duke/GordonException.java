package seedu.duke;

public class GordonException extends Exception {
    public static final String INDEX_OOB = "Index outside range.";
    public static final String INDEX_INVALID = "Index is not a valid integer.";

    public static final String COMMAND_INVALID = "Command given cannot be recognized.";

    public static final String NO_RESULT_FOUND = "Search returns no result.";

    public GordonException(String errorMessage) {
        super(errorMessage);
    }
}
