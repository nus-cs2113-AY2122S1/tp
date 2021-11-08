package seedu.duke.exceptions;

import static seedu.duke.common.Messages.MESSAGE_STORE_FORMAT;

public class InvalidStoreToFilePathException extends InvalidInputException {
    public InvalidStoreToFilePathException() {
        super(MESSAGE_STORE_FORMAT);
    }

}
