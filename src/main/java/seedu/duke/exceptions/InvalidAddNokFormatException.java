package seedu.duke.exceptions;

import static seedu.duke.common.Messages.MESSAGE_ADD_NOK_FORMAT;

public class InvalidAddNokFormatException extends InvalidInputException {
    public InvalidAddNokFormatException() {
        super(MESSAGE_ADD_NOK_FORMAT);
    }

}
