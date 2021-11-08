package seedu.duke.exceptions;

import static seedu.duke.common.Messages.MESSAGE_VIEW_NOK_FORMAT;

public class InvalidViewNokFormatException extends InvalidInputException {
    public InvalidViewNokFormatException() {
        super(MESSAGE_VIEW_NOK_FORMAT);
    }

}