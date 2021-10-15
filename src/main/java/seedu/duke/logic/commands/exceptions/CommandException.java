package seedu.duke.logic.commands.exceptions;

import seedu.duke.DukeException;

public class CommandException extends DukeException {
    public CommandException(String message) {
        super(message);
    }
}
