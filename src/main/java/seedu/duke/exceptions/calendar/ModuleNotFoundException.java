package seedu.duke.exceptions.calendar;

import seedu.duke.constants.ExceptionMessages;

public class ModuleNotFoundException extends Exception {
    public ModuleNotFoundException() {
        super(ExceptionMessages.MESSAGE_MODULE_NOT_FOUND);
    }
}
