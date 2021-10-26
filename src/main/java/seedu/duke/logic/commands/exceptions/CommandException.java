package seedu.duke.logic.commands.exceptions;

import seedu.duke.DukeException;

//@@author richwill28
public class CommandException extends DukeException {
    public CommandException(String message) {
        super(message);
    }
}
