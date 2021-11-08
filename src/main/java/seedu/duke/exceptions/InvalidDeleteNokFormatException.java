package seedu.duke.exceptions;

import static seedu.duke.common.Messages.MESSAGE_DELETE_NOK_FORMAT;

public class InvalidDeleteNokFormatException extends InvalidInputException {
    public InvalidDeleteNokFormatException() {
        super(MESSAGE_DELETE_NOK_FORMAT);
    }

}
