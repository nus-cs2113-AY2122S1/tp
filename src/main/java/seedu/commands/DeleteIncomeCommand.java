package seedu.commands;

import seedu.entry.Entry;
import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

public class DeleteIncomeCommand extends Command {
    private int expenseIndex;

    public DeleteIncomeCommand(int expenseIndex) {
        this.expenseIndex = (expenseIndex) - 1;
    }

    @Override
    public void execute(FinancialTracker finances, Ui ui) {
        Entry deletedEntry = handleEntryDelete(finances);
        ui.printIncomeDeleted(deletedEntry);
    }

    private Entry handleEntryDelete(FinancialTracker financialEntry) {
        Entry deletedEntry = financialEntry.getEntry(expenseIndex);
        financialEntry.getEntries().remove(expenseIndex);
        return deletedEntry;
    }
}
