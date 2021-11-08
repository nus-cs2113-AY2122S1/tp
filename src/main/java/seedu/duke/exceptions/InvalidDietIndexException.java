package seedu.duke.exceptions;

import static seedu.duke.common.Messages.MESSAGE_ERROR_IN_DIET_INDEX;

public class InvalidDietIndexException extends InvalidInputException {
    public InvalidDietIndexException() {
        super(MESSAGE_ERROR_IN_DIET_INDEX);
    }
}
