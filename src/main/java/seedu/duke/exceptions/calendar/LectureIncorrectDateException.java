package seedu.duke.exceptions.calendar;

import seedu.duke.exceptions.ClickException;
import static seedu.duke.constants.ExceptionMessages.START_AFTER_END_DATE_ILLEGAL;

public class LectureIncorrectDateException extends ClickException {
    public LectureIncorrectDateException() {
        super(START_AFTER_END_DATE_ILLEGAL);
    }
}
