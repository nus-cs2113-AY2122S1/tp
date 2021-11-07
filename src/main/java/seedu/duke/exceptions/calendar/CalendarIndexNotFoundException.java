package seedu.duke.exceptions.calendar;

import seedu.duke.constants.ExceptionMessages;
import seedu.duke.exceptions.ClickException;

/**
 * Represents exception thrown when index of task
 * to delete is not found.
 */
public class CalendarIndexNotFoundException extends ClickException {
    public CalendarIndexNotFoundException() {
        super(ExceptionMessages.MESSAGE_ILLEGAL_CALENDAR_INDEX);
    }
}
