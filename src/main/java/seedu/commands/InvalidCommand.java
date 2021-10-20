package seedu.commands;

import seedu.utility.BudgetManager;
import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

public class InvalidCommand extends Command {
    
    private final String message;
    
    public InvalidCommand(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public void execute(FinancialTracker finances, Ui ui, BudgetManager budgetManager) {
        ui.printError(message);
    }
}
