package seedu.duke.exceptions;

import static seedu.duke.common.Messages.MESSAGE_ADD_RECORD_FORMAT;

public class InvalidAddRecordFormatException extends InvalidInputException {
    public InvalidAddRecordFormatException() {
        super(MESSAGE_ADD_RECORD_FORMAT);
    }

}
