package seedu.duke.exceptions;

import static seedu.duke.common.Messages.MESSAGE_VIEW_MEDICAL_HISTORY_FORMAT;

public class InvalidViewMedicalHistoryException extends InvalidInputException {
    public InvalidViewMedicalHistoryException() {
        super(MESSAGE_VIEW_MEDICAL_HISTORY_FORMAT);
    }
}
