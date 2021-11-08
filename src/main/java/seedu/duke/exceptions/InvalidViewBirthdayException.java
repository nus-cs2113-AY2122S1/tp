package seedu.duke.exceptions;

import static seedu.duke.common.Messages.WRONG_VIEW_BIRTHDAY_INPUT;

public class InvalidViewBirthdayException extends InvalidInputException {
    public InvalidViewBirthdayException() {
        super(WRONG_VIEW_BIRTHDAY_INPUT);
    }
}
