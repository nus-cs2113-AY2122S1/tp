package seedu.duke.commands;

import seedu.duke.data.RecordList;

public abstract class Command {
    protected RecordList budgetList;

    public abstract void execute();

    public void setBudgetList(RecordList budgetList) {
        this.budgetList = budgetList;
    }

    public boolean isExit() {
        return this instanceof ExitCommand;
    }
}
