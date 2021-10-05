package seedu.duke.exception;

import seedu.duke.ui.Message;

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
