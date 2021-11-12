package seedu.budgettracker.logic.commands;

import seedu.budgettracker.data.AllRecordList;
import seedu.budgettracker.data.records.exceptions.DuplicateBudgetException;
import seedu.budgettracker.logic.commands.exceptions.CommandException;

/**
 * Basic command that other commands extend off of.
 */
public abstract class Command {
    protected AllRecordList allRecordList;

    public abstract void execute() throws CommandException;

    public void setAllRecordList(AllRecordList allRecordList) {
        this.allRecordList = allRecordList;
    }

    public boolean isExit() {
        return this instanceof ExitCommand;
    }


}
