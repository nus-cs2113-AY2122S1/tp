package seedu.commands;

import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

public class HelpCommand extends Command {
    @Override
    public void execute(FinancialTracker finances, Ui ui) {
        ui.printHelp();
    }
}
