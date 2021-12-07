package seedu.duke.exceptions.food;

import seedu.duke.constants.Messages;
import seedu.duke.exceptions.ClickException;

/**
 * Thrown when the user does not issue a date when
 * finding a food record.
 */
public class MissingDateException extends ClickException {
    public MissingDateException() {
        super(Messages.PRINT_DATE_NOT_FOUND);
    }
}
