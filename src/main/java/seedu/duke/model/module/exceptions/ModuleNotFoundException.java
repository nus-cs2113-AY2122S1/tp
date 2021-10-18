package seedu.duke.model.module.exceptions;

import seedu.duke.DukeException;

public class ModuleNotFoundException extends DukeException {
    public ModuleNotFoundException(String message) {
        super(message);
    }
}
