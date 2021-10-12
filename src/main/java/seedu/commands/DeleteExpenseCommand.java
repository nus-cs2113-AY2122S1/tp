package seedu.commands;

import seedu.entry.Expense;
import seedu.exceptions.ExpenseEntryNotFoundException;
import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

public class DeleteExpenseCommand extends Command {
    private int expenseNumber;

    public DeleteExpenseCommand(int expenseNumber) {
        this.expenseNumber = expenseNumber;
    }

    @Override
    public void execute(FinancialTracker finances, Ui ui) {
        Expense deletedExpense = finances.removeExpense(expenseNumber);
        ui.printExpenseDeleted(deletedExpense);
    }
}
