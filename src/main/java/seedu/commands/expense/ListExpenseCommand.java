package seedu.commands.expense;

import seedu.commands.Command;
import seedu.entry.Expense;
import seedu.utility.BudgetManager;
import seedu.utility.CurrencyManager;
import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

import java.util.ArrayList;

public class ListExpenseCommand extends Command {
    @Override
    public void execute(FinancialTracker finances, Ui ui, BudgetManager budgetManager,
                        CurrencyManager currencyManager) {
        ArrayList<Expense> expenses = finances.getExpenses(); 
        ui.listExpense(expenses);
    }
}
