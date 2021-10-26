package seedu.duke.commands;

import seedu.duke.data.AllRecordList;

/**
 * Basic command that other commands extend off of.
 */
public abstract class Command {
    protected AllRecordList allRecordList;

    public abstract void execute(boolean isLoadingStorage);

    public void setAllRecordList(AllRecordList allRecordList) {
        this.allRecordList = allRecordList;
    }

    public boolean isExit() {
        return this instanceof ExitCommand;
    }
}
