package seedu.duke.exceptions.calendar;

import seedu.duke.exceptions.ClickException;

public class IncorrectNumberOfArgumentsException extends ClickException {
    String message;

    public IncorrectNumberOfArgumentsException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
