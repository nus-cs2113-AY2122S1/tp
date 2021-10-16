package seedu.duke.commands;

import seedu.duke.data.AllRecordList;

/**
 * Basic command that other commands extend off of.
 */
public abstract class Command {
    protected AllRecordList recordList;

    public abstract void execute(boolean isLoadingStorage);

    public void setRecordList(AllRecordList recordList) {
        this.recordList = recordList;
    }

    public boolean isExit() {
        return this instanceof ExitCommand;
    }
}
