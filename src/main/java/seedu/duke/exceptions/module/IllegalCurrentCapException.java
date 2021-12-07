package seedu.duke.exceptions.module;

import seedu.duke.exceptions.ClickException;

import static seedu.duke.constants.ExceptionMessages.MESSAGE_ILLEGAL_CURRENT_CAP;

//@@author nvbinh15

/**
 * A class that represents exceptions thrown when the current CAP from the user is illegal.
 */
public class IllegalCurrentCapException extends ClickException {

    /**
     * Class constructor inherited from ClickException.
     */
    public IllegalCurrentCapException() {
        super(MESSAGE_ILLEGAL_CURRENT_CAP);
    }
}
