package seedu.duke.exceptions.module;

import seedu.duke.exceptions.ClickException;

import static seedu.duke.constants.ExceptionMessages.MESSAGE_MODULE_STORAGE;

//@@author nvbinh15

/**
 * A class that represents exceptions thrown when there is something wrong with the Module storage file.
 */
public class StorageModuleException extends ClickException {

    /**
     * Class constructor inherited from ClickException.
     */
    public StorageModuleException() {
        super(MESSAGE_MODULE_STORAGE);
    }
}
