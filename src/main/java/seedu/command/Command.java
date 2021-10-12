package seedu.command;

import seedu.exceptions.AddException;

public abstract class Command {

    public Command() {

    }

    public abstract void execute() throws AddException;

    public boolean isExit() {
        return this instanceof ExitCommand;
    }
}
