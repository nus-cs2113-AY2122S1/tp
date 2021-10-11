package seedu.commands;

import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

public class ListExpenseCommand extends Command {
    @Override
    public void execute(FinancialTracker finances, Ui ui) {
        ui.listExpense(finances.getEntries());
    }
}
