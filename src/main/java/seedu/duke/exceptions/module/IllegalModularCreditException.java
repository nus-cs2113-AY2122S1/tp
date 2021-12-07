package seedu.duke.exceptions.module;

import seedu.duke.exceptions.ClickException;

import static seedu.duke.constants.ExceptionMessages.MESSAGE_ILLEGAL_MODULAR_CREDIT;

//@@author nvbinh15

/**
 * A class that represents exceptions thrown when the total modular credit is illegal.
 */
public class IllegalModularCreditException extends ClickException {

    /**
     * Class constructor inherited from ClickException.
     */
    public IllegalModularCreditException() {
        super(MESSAGE_ILLEGAL_MODULAR_CREDIT);
    }
}
