package seedu.duke.exceptions;

import static seedu.duke.common.Messages.WRONG_SET_BIRTHDAY_INPUT;

public class InvalidSetBirthdayException extends InvalidInputException {
    public InvalidSetBirthdayException() {
        super(WRONG_SET_BIRTHDAY_INPUT);
    }
}
