package seedu.command;

import seedu.exceptions.AddException;
import seedu.exceptions.FetchException;
import seedu.exceptions.IntegerException;
import seedu.exceptions.ModuleExistException;

public abstract class Command {

    public Command() {

    }

    public abstract void execute() throws IntegerException, ModuleExistException, FetchException, AddException;

    public boolean isExit() {
        return this instanceof ExitCommand;
    }
}
