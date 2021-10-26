package seedu.duke.exception;

public class ReminderNotRequiredException extends Exception{
    private static final String MESSAGE = "This task do not need reminder, hence cannot customize";

    public String toString() {
        return MESSAGE;
    }

}
