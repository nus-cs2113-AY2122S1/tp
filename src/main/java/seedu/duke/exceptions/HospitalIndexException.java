package seedu.duke.exceptions;

import static seedu.duke.common.Messages.MESSAGE_INVALID_HOSPITAL_INDEX;

public class HospitalIndexException extends DukeException {
    public HospitalIndexException() {
        super(MESSAGE_INVALID_HOSPITAL_INDEX);
    }

}
