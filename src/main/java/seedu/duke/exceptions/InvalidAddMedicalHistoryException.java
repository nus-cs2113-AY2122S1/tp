package seedu.duke.exceptions;

import static seedu.duke.common.Messages.MESSAGE_ADD_MEDICAL_HISTORY_FORMAT;

public class InvalidAddMedicalHistoryException extends InvalidInputException {
    public InvalidAddMedicalHistoryException() {
        super(MESSAGE_ADD_MEDICAL_HISTORY_FORMAT);
    }
}
