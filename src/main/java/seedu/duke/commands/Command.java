package seedu.duke.commands;

import seedu.duke.data.RecordList;

/**
 * Basic command that other commands extend off of.
 */
public abstract class Command {
    protected RecordList recordList;

    public abstract void execute(boolean isLoadingStorage);

    public void setRecordList(RecordList recordList) {
        this.recordList = recordList;
    }

    public boolean isExit() {
        return this instanceof ExitCommand;
    }
}
