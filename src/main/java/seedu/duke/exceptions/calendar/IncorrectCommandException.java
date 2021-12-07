package seedu.duke.exceptions.calendar;

import seedu.duke.exceptions.ClickException;

public class IncorrectCommandException extends ClickException {
    public IncorrectCommandException(String message) {
        super(message);
    }
}
