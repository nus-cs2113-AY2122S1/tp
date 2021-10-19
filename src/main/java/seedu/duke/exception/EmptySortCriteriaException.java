package seedu.duke.exception;

public class EmptySortCriteriaException extends Exception {

    private static final String MESSAGE = "[!] Please specify a sort criteria...\n";

    @Override
    public String toString() {
        return MESSAGE;
    }

}
