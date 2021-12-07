package seedu.duke.exceptions.module;

import seedu.duke.exceptions.ClickException;

import static seedu.duke.constants.ExceptionMessages.MESSAGE_DUPLICATE_MODULE;

//@@author nvbinh15

/**
 * A class that represents exceptions thrown when the module to be added already exists.
 */
public class DuplicateModuleException extends ClickException {

    /**
     * Class constructor inherited from ClickException.
     */
    public DuplicateModuleException() {
        super(MESSAGE_DUPLICATE_MODULE);
    }
}
