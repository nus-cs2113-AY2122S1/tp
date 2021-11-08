package seedu.duke.exceptions;

import static seedu.duke.common.Messages.MESSAGE_DUPLICATE_ELDERLY;

public class DuplicateElderlyException extends DukeException {
    public DuplicateElderlyException() {
        super(MESSAGE_DUPLICATE_ELDERLY);
    }

}
