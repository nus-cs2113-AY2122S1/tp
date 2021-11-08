package seedu.duke.exceptions;

import static seedu.duke.common.Messages.MESSAGE_VIEW_MED_FORMAT;

public class InvalidViewMedicineFormatException extends InvalidInputException {
    public InvalidViewMedicineFormatException() {
        super(MESSAGE_VIEW_MED_FORMAT);
    }
}
