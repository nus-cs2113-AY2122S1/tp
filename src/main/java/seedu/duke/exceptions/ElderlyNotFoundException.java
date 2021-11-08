package seedu.duke.exceptions;

import static seedu.duke.common.Messages.MESSAGE_NO_SUCH_ELDERLY;

public class ElderlyNotFoundException extends DukeException {
    public ElderlyNotFoundException() {
        super(MESSAGE_NO_SUCH_ELDERLY);
    }

}
