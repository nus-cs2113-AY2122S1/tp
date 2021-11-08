package seedu.duke.exceptions;

import static seedu.duke.common.Messages.MESSAGE_FIND_BY_MED_FORMAT;

public class InvalidViewMedicineException extends InvalidInputException {
    public InvalidViewMedicineException() {
        super(MESSAGE_FIND_BY_MED_FORMAT);
    }
}
