package seedu.commands;

import seedu.entry.Entry;
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
        try {
            Entry deletedEntry = finances.removeExpenseEntry(expenseNumber);
            ui.printExpenseDeleted((Expense) deletedEntry);
        } catch (ExpenseEntryNotFoundException e) {
            ui.printError(e.getMessage());
        }
    }
}
