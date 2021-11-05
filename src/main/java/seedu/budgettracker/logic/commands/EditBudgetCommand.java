package seedu.budgettracker.logic.commands;

import seedu.budgettracker.data.records.Budget;
import seedu.budgettracker.ui.TextUi;

public class EditBudgetCommand extends EditCommand {
    public int month;
    public double amount;

    public EditBudgetCommand(int month, double amount) {
        this.month = month;
        this.amount = amount;
    }

    public void execute() {
        Budget targetBudget = allRecordList.editBudget(this.month, this.amount);
        TextUi.showBudgetEditedMessage(targetBudget);
    }
}
