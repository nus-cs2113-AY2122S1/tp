package seedu.tp.exception;

public class MissingDiaryMessageException extends Exception {
    private static final String MESSAGE = "You cannot leave the content to be added empty.";

    public String toString() {
        return MESSAGE;
    }
}
