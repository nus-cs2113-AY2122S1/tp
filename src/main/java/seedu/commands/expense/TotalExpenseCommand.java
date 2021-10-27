package seedu.commands.expense;

import seedu.commands.Command;
import seedu.utility.BudgetManager;
import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

public class TotalExpenseCommand extends Command {
    @Override
    public void execute(FinancialTracker finances, Ui ui, BudgetManager budgetManager) {
        double totalExpense = finances.getTotalExpense();
        ui.printTotalExpense(totalExpense);
    }
}
