package seedu.duke.exceptions;

import static seedu.duke.common.Messages.MESSAGE_DELETE_ELDERLY_FORMAT;

public class InvalidDeleteElderlyException extends InvalidInputException {
    public InvalidDeleteElderlyException() {
        super(MESSAGE_DELETE_ELDERLY_FORMAT);
    }
}
