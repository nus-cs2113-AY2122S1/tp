package seedu.duke.exceptions.module;

import seedu.duke.exceptions.ClickException;
import static seedu.duke.constants.ExceptionMessages.MESSAGE_DUPLICATE_MODULE_PARAMETERS;

//@@author nvbinh15

/**
 * A class that represents exceptions thrown when there are duplicate parameters in the add module command.
 */
public class DuplicateModuleParamException extends ClickException {

    /**
     * Class constructor inherited from ClickException.
     */
    public DuplicateModuleParamException() {
        super(MESSAGE_DUPLICATE_MODULE_PARAMETERS);
    }
}
