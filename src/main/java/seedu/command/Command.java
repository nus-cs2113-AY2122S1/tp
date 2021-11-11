package seedu.command;


import seedu.exceptions.AddException;
import seedu.exceptions.EditException;
import seedu.exceptions.IntegerException;
import seedu.exceptions.ModuleExistException;
import seedu.exceptions.FetchException;

public abstract class Command {

    public static final String commandSyntax = null;
    public static final String commandAction = null;

    public Command() {

    }

    public abstract void execute() throws IntegerException, ModuleExistException,
            FetchException, AddException, EditException;

    public boolean isExit() {
        return this instanceof ExitCommand;
    }


}
