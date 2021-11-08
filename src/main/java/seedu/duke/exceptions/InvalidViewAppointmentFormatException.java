package seedu.duke.exceptions;

import static seedu.duke.common.Messages.MESSAGE_VIEW_APPOINTMENT_FORMAT;

public class InvalidViewAppointmentFormatException extends InvalidInputException {
    public InvalidViewAppointmentFormatException() {
        super(MESSAGE_VIEW_APPOINTMENT_FORMAT);
    }
}