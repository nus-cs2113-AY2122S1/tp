package seedu.duke.exceptions;

import static seedu.duke.common.Messages.MESSAGE_ADD_ELDERLY_FORMAT;

public class InvalidElderlyFormatException extends InvalidInputException {
    public InvalidElderlyFormatException() {
        super(MESSAGE_ADD_ELDERLY_FORMAT);
    }
}
