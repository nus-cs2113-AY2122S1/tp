package seedu.duke.exceptions.syntax;

import seedu.duke.exceptions.ClickException;

import static seedu.duke.constants.ExceptionMessages.MESSAGE_ILLEGAL_COMMAND;

//@@author nvbinh15

public class IllegalCommandException extends ClickException {

    public IllegalCommandException() {
        super(MESSAGE_ILLEGAL_COMMAND);
    }
}
