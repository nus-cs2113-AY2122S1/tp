package seedu.tp.exception;

public class MissingDiaryFieldException  extends Exception {
    private static final String MESSAGE = "You cannot leave all fields empty.";

    public String toString() {
        return MESSAGE;
    }
}
