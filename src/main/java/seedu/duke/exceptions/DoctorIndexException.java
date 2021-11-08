package seedu.duke.exceptions;

import static seedu.duke.common.Messages.MESSAGE_INVALID_DOCTOR_INDEX;

public class DoctorIndexException extends DukeException {
    public DoctorIndexException() {
        super(MESSAGE_INVALID_DOCTOR_INDEX);
    }

}
