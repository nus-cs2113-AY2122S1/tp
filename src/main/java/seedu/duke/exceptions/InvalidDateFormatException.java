package seedu.duke.exceptions;

import static seedu.duke.common.Messages.WRONG_BIRTHDAY_FORMAT_INPUT;

public class InvalidDateFormatException extends InvalidInputException {
    public InvalidDateFormatException() {
        super(WRONG_BIRTHDAY_FORMAT_INPUT);
    }
}
