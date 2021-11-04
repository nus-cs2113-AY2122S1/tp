package seedu.duke.exception;

/**
 * Exception to be thrown when user's input in {@link seedu.duke.command.ListCommand}
 * did not specify a filter argument for the filter when filtering the tasklist.
 */
public class MissingFilterArgumentException extends Exception {

    private static final String MISSING_FILTER_ARGUMENT_MESSAGE = "[!] One of your filter's is missing an argument...\n"
            + "Please follow the format: list or list --[type, priority, recur] <argument>";

    @Override
    public String toString() {
        return MISSING_FILTER_ARGUMENT_MESSAGE;
    }

}
