package seedu.duke.exception;

public class MissingFilterArgumentException extends Exception {

    private static final String MESSAGE = "[!] One of your filter's is missing an argument...\n"
            + "Please follow the format: list or list --[type, priority, recur] <argument>";

    @Override
    public String toString() {
        return MESSAGE;
    }

}
