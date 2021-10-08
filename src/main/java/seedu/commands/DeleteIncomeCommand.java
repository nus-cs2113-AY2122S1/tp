package seedu.commands;

import seedu.entry.Income;
import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

public class DeleteIncomeCommand extends Command {
    private int incomeIndex;

    public DeleteIncomeCommand(int incomeIndex) {
        this.incomeIndex = incomeIndex - 1;
    }

    @Override
    public void execute(FinancialTracker finances, Ui ui) {
        finances.removeEntry(incomeIndex);
        ui.printIncomeDeleted((Income) finances.getEntry(incomeIndex));
    }
}
