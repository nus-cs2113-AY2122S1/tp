package seedu.duke.exceptions;

import static seedu.duke.common.Messages.MESSAGE_ADD_MED_FORMAT;

public class InvalidAddMedicineFormatException extends InvalidInputException {
    public InvalidAddMedicineFormatException() {
        super(MESSAGE_ADD_MED_FORMAT);
    }
}
