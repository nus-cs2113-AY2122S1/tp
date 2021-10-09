package seedu.commands;

import seedu.entry.Expense;
import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

public class DeleteExpenseCommand extends Command {
    private int expenseIndex;

    public DeleteExpenseCommand(int expenseIndex) {
        this.expenseIndex = expenseIndex - 1;
    }

    @Override
    public void execute(FinancialTracker finances, Ui ui) {
        finances.removeEntry(expenseIndex);
        ui.printExpenseDeleted((Expense) finances.getEntry(expenseIndex));
    }
}
