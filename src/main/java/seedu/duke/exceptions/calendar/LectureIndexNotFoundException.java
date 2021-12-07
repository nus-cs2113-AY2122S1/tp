package seedu.duke.exceptions.calendar;

import seedu.duke.constants.ExceptionMessages;
import seedu.duke.exceptions.ClickException;

public class LectureIndexNotFoundException extends ClickException {
    public LectureIndexNotFoundException() {
        super(ExceptionMessages.MESSAGE_ILLEGAL_LECTURE_INDEX);
    }
}
