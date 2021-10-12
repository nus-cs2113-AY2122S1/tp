package seedu.commands;

import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

public class TotalIncomeCommand extends Command {
    @Override
    public void execute(FinancialTracker finances, Ui ui) {
        double totalIncome = finances.getTotalIncome();
        ui.printTotalIncome(totalIncome);
    }
}
