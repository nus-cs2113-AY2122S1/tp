package seedu.command;

import seedu.exceptions.*;

public abstract class Command {

    public Command() {

    }

    public abstract void execute() throws IntegerException, ModuleExistException, FetchException, AddException, EditException;

    public boolean isExit() {
        return this instanceof ExitCommand;
    }
}
