package seedu.duke.exceptions;

import static seedu.duke.common.Messages.MESSAGE_ERROR_IN_VIEW_DIET;

public class InvalidViewDietCommandException extends InvalidInputException {
    public InvalidViewDietCommandException() {
        super(MESSAGE_ERROR_IN_VIEW_DIET);
    }
}
