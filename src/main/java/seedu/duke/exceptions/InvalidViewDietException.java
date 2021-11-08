package seedu.duke.exceptions;

import static seedu.duke.common.Messages.MESSAGE_FIND_BY_DIET_FORMAT;

public class InvalidViewDietException extends InvalidInputException {
    public InvalidViewDietException() {
        super(MESSAGE_FIND_BY_DIET_FORMAT);
    }
}