package seedu.duke;

import seedu.duke.commons.core.Message;

//@@author richwill28
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        String message = getLocalizedMessage();
        return (message != null) ? message : Message.ERROR_UNKNOWN;
    }
}

