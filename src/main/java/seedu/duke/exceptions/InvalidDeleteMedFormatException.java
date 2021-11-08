package seedu.duke.exceptions;

import static seedu.duke.common.Messages.MESSAGE_DELETE_MEDICINE_FORMAT;

public class InvalidDeleteMedFormatException extends InvalidInputException {
    public InvalidDeleteMedFormatException() {
        super(MESSAGE_DELETE_MEDICINE_FORMAT);
    }
}
