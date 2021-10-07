package seedu.commands;

import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

public class ExitCommand extends Command {

    @Override
    public void execute(FinancialTracker finances, Ui ui) {
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
