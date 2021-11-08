package seedu.duke.exceptions;

import static seedu.duke.common.Messages.MESSAGE_DELETE_APPOINTMENT_FORMAT;

public class InvalidDeleteApptFormatException extends InvalidInputException {
    public InvalidDeleteApptFormatException() {
        super(MESSAGE_DELETE_APPOINTMENT_FORMAT);
    }
}
