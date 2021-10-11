package seedu.commands;

import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

public class ListIncomeCommand extends Command {
    @Override
    public void execute(FinancialTracker finances, Ui ui) {
        ui.listIncome(finances.getEntries());
    }
}
