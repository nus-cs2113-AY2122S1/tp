package seedu.duke.exceptions;

import static seedu.duke.common.Messages.MESSAGE_DELETE_MEDICAL_HISTORY_FORMAT;

public class InvalidDeleteMedicalHistoryException extends InvalidInputException {
    public InvalidDeleteMedicalHistoryException() {
        super(MESSAGE_DELETE_MEDICAL_HISTORY_FORMAT);
    }

}