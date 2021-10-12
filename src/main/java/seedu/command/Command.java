package seedu.command;

import seedu.exceptions.AddException;
import seedu.exceptions.IntegerException;

public abstract class Command {

    public Command() {

    }

    public abstract void execute() throws AddException, IntegerException;

    public boolean isExit() {
        return this instanceof ExitCommand;
    }
}
