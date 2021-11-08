package seedu.duke.exceptions;

import static seedu.duke.common.Messages.MESSAGE_ERROR_IN_SET_DIET;

public class InvalidSetDietCommandException extends InvalidInputException {
    public InvalidSetDietCommandException() {
        super(MESSAGE_ERROR_IN_SET_DIET);
    }
}
