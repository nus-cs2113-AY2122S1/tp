package seedu.duke.exceptions;

import static seedu.duke.common.Messages.MESSAGE_INVALID_SET_BLOOD_PRESSURE;

public class InvalidSetBloodPressureException extends DukeException {
    public InvalidSetBloodPressureException() {
        super(MESSAGE_INVALID_SET_BLOOD_PRESSURE);
    }

}
