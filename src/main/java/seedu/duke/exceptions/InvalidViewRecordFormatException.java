package seedu.duke.exceptions;

import static seedu.duke.common.Messages.MESSAGE_VIEW_RECORD_FORMAT;

public class InvalidViewRecordFormatException extends InvalidInputException {
    public InvalidViewRecordFormatException() {
        super(MESSAGE_VIEW_RECORD_FORMAT);
    }

}