package seedu.commands;

import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

/**
 * Clears all expense and income entries. This is so that users can start afresh.
 * Also allows us developers to have easier time testing data saving capabilities.
 */
public class ClearAllEntriesCommand extends Command {

    @Override
    public void execute(FinancialTracker finances, Ui ui) {
        finances.clearAllEntries();
        ui.printAllEntriesCleared();
    }
}
