package seedu.command;

import seedu.module.ModList;

public abstract class Command {

    protected ModList modList;

    public Command() {

    }

    public void setData(ModList modList) {
        this.modList = modList;
    }

    public abstract void execute();

    public boolean isExit() {
        return this instanceof ExitCommand;
    }
}
