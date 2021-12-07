package seedu.duke.exceptions.module;

import seedu.duke.exceptions.ClickException;

import static seedu.duke.constants.ExceptionMessages.MESSAGE_ILLEGAL_MODULE_COMMAND;

//@@author nvbinh15

/**
 * A class that represents exceptions thrown when a Module command is illegal.
 */
public class IllegalModuleCommandException extends ClickException {

    /**
     * Class constructor inherited from ClickException.
     */
    public IllegalModuleCommandException() {
        super(MESSAGE_ILLEGAL_MODULE_COMMAND);
    }
}
