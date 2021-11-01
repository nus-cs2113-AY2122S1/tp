package seedu.commands.expense;

import seedu.commands.Command;
import seedu.exceptions.ExpenseOverflowException;
import seedu.utility.BudgetManager;
import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

public class TotalExpenseCommand extends Command {
    @Override
    public void execute(FinancialTracker finances, Ui ui, BudgetManager budgetManager) {
        try {
            double totalExpense;
            totalExpense = finances.getTotalExpense();
            ui.printTotalExpense(totalExpense);
        } catch (ExpenseOverflowException e) {
            ui.printError(e.getMessage());
        }
        
    }
}
