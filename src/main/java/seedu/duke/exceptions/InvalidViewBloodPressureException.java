package seedu.duke.exceptions;

import static seedu.duke.common.Messages.MESSAGE_VIEW_BLOOD_PRESSURE_FORMAT;

public class InvalidViewBloodPressureException extends InvalidInputException {
    public InvalidViewBloodPressureException() {
        super(MESSAGE_VIEW_BLOOD_PRESSURE_FORMAT);
    }
}
