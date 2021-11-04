package seedu.duke.exception;

/**
 * Exception to be thrown when the user's input in {@link seedu.duke.command.SortCommand}
 * did not specify any sort criteria to sort the tasklist by.
 */
public class EmptySortCriteriaException extends Exception {

    private static final String MESSAGE = "[!] Please specify a sort criteria...\n";

    @Override
    public String toString() {
        return MESSAGE;
    }

}
