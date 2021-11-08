package seedu.duke.exceptions;

import static seedu.duke.common.Messages.MESSAGE_LOAD_FORMAT;

public class InvalidLoadFromFilePathException extends InvalidInputException {
    public InvalidLoadFromFilePathException() {
        super(MESSAGE_LOAD_FORMAT);
    }

}
