package seedu.duke.exceptions.module;

//@@author nvbinh15

import seedu.duke.exceptions.ClickException;

import static seedu.duke.constants.ExceptionMessages.MESSAGE_MISSING_MODULE_CODE;

/**
 * A class that represents exceptions thrown when the module code is missing.
 */
public class MissingModuleCodeException extends ClickException {

    /**
     * Class constructor inherited from ClickException.
     */
    public MissingModuleCodeException() {
        super(MESSAGE_MISSING_MODULE_CODE);
    }
}
