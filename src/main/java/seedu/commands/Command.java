package seedu.commands;

import seedu.utility.BudgetManager;
import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

public abstract class Command {
    public abstract void execute(FinancialTracker finances, Ui ui, BudgetManager budgetManager);

    public boolean isExit() {
        return false;
    }
}
