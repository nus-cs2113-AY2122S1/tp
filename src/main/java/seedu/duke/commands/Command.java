package seedu.duke.commands;

import seedu.duke.data.BudgetList;

public abstract class Command {
    protected BudgetList budgetList;

    public abstract void execute();

    public void setBudgetList(BudgetList budgetList) {
        this.budgetList = budgetList;
    }

    public boolean isExit() {
        return this instanceof ExitCommand;
    }
}
