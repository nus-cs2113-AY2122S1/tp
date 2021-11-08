package seedu.duke.exceptions;

import static seedu.duke.common.Messages.MESSAGE_ADD_APPOINTMENT_FORMAT;

public class InvalidAddAppointmentFormatException extends InvalidInputException {
    public InvalidAddAppointmentFormatException() {
        super(MESSAGE_ADD_APPOINTMENT_FORMAT);
    }
}
