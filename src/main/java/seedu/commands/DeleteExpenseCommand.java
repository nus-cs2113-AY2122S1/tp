package seedu.commands;

import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

public class DeleteExpenseCommand extends Command {
    private int expenseIndex;

    public DeleteExpenseCommand(int expenseIndex) {
        this.expenseIndex = expenseIndex;
    }

    @Override
    public void execute(FinancialTracker finances, Ui ui) {
        //TODO
    }
}
