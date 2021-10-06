package seedu.commands;

import seedu.entry.Entry;
import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

public class DeleteExpenseCommand extends Command {
    private int expenseIndex;

    public DeleteExpenseCommand(int expenseIndex) {
        this.expenseIndex = expenseIndex;
    }

    @Override
    public void execute(FinancialTracker finances, Ui ui) {
        Entry deletedEntry = handleEntryDelete(finances);
        ui.printExpenseDeleted(deletedEntry);
    }

    private Entry handleEntryDelete(FinancialTracker financialEntry) {
        Entry deletedEntry = financialEntry.getEntry(expenseIndex);
        financialEntry.getEntries().remove(expenseIndex);
        return deletedEntry;
    }
}
