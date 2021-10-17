package seedu.commands;

import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

public class BalanceCommand extends Command {
    public void execute(FinancialTracker finances, Ui ui) {
        ui.printBalance(finances.getBalance());
    }
}
