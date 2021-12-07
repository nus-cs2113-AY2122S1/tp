package seedu.duke.exceptions.module;

import seedu.duke.exceptions.ClickException;

import static seedu.duke.constants.ExceptionMessages.MESSAGE_ILLEGAL_MODULE_INDEX;

//@@author nvbinh15

/**
 * A class that represents exceptions thrown when the Module index is illegal.
 */
public class IllegalModuleIndexException extends ClickException {

    /**
     * Class constructor inherited from ClickException.
     */
    public IllegalModuleIndexException() {
        super(MESSAGE_ILLEGAL_MODULE_INDEX);
    }
}
