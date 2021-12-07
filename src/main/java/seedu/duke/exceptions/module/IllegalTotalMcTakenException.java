package seedu.duke.exceptions.module;

import seedu.duke.exceptions.ClickException;

import static seedu.duke.constants.ExceptionMessages.MESSAGE_ILLEGAL_TOTAL_MC_TAKEN;

//@@author nvbinh15

/**
 * A class that represents exceptions thrown when the total MC taken from the user is illegal.
 */
public class IllegalTotalMcTakenException extends ClickException {

    /**
     * Class constructor inherited from ClickException.
     */
    public IllegalTotalMcTakenException() {
        super(MESSAGE_ILLEGAL_TOTAL_MC_TAKEN);
    }
}
