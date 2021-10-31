package seedu.commands.budget;

import seedu.commands.Command;
import seedu.utility.BudgetManager;
import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

public class SetThresholdCommand extends Command {
    double threshold;

    public SetThresholdCommand(double threshold) {
        this.threshold = threshold;
    }

    @Override
    public void execute(FinancialTracker finances, Ui ui, BudgetManager budgetManager) {
        budgetManager.setThreshold(threshold);
        ui.printThresholdConfirmation(threshold);
    }
}
