package seedu.duke.exceptions;

import static seedu.duke.common.Messages.MESSAGE_FIND_BY_NAME_FORMAT;

public class InvalidViewByNameException extends InvalidInputException {
    public InvalidViewByNameException() {
        super(MESSAGE_FIND_BY_NAME_FORMAT);
    }
}
