package seedu.commands;

import seedu.entry.Expense;
import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

import java.util.ArrayList;

public class ListExpenseCommand extends Command {
    @Override
    public void execute(FinancialTracker finances, Ui ui) {
        ArrayList<Expense> expenses = finances.listExpenses(); 
        ui.listExpense(expenses);
    }
}
