package seedu.commands;

import seedu.entry.Entry;
import seedu.entry.Expense;
import seedu.entry.Income;
import seedu.exceptions.ExpenseEntryNotFoundException;
import seedu.exceptions.IncomeEntryNotFoundException;
import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

public class DeleteIncomeCommand extends Command {
    private int incomeNumber;

    public DeleteIncomeCommand(int incomeNumber) {
        this.incomeNumber = incomeNumber;
    }

    @Override
    public void execute(FinancialTracker finances, Ui ui) {
        try {
            Entry deletedEntry = finances.removeIncomeEntry(incomeNumber);
            ui.printIncomeDeleted((Income) deletedEntry);
        } catch (IncomeEntryNotFoundException e) {
            ui.printError(e.getMessage());
        }
    }
}
