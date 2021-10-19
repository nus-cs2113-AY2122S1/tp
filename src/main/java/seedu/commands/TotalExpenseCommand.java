package seedu.commands;

import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

public class TotalExpenseCommand extends Command {
    @Override
    public void execute(FinancialTracker finances, Ui ui) {
        double totalExpense = finances.getTotalExpense(); 
        ui.printTotalExpense(totalExpense);
    }
}
