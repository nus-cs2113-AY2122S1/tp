package seedu.commands;

import seedu.entry.Expense;
import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

public class AddExpenseCommand extends Command {
    private Expense expense;

    public AddExpenseCommand(Expense expense) {
        this.expense = expense;
    }

    @Override
    public void execute(FinancialTracker finances, Ui ui) {
        ui.printExpenseAdded((Expense) finances.addEntry(expense));
    }
}
