package seedu.duke.model.lesson.exceptions;

import seedu.duke.DukeException;

public class EmptyLinkException extends DukeException {
    public EmptyLinkException(String message) {
        super(message);
    }
}
