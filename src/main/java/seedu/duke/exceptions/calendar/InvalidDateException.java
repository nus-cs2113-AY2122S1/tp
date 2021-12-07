package seedu.duke.exceptions.calendar;

import seedu.duke.exceptions.ClickException;

public class InvalidDateException extends ClickException {
    private static String message = "Invalid date given!";

    public InvalidDateException() {
        super(message);
    }

    public String getMessage() {
        return message;
    }
}
