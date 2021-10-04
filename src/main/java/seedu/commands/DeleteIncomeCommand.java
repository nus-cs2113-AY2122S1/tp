package seedu.commands;

import seedu.commands.Command;
import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

public class DeleteIncomeCommand extends Command {
    private int expenseIndex;

    public DeleteIncomeCommand(int expenseIndex) {
        this.expenseIndex = expenseIndex;
    }

    @Override
    public void execute(FinancialTracker finances, Ui ui) {
        //TODO
    }
}
