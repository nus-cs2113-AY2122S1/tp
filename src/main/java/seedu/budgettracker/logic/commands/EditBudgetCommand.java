package seedu.budgettracker.logic.commands;

import seedu.budgettracker.data.records.Budget;
import seedu.budgettracker.logic.commands.exceptions.CommandException;
import seedu.budgettracker.ui.TextUi;

import static seedu.budgettracker.common.Messages.MESSAGE_AMOUNT_EXCEEDED;

public class EditBudgetCommand extends EditCommand {
    public int month;
    public double amount;

    public EditBudgetCommand(int month, double amount) {
        this.month = month;
        this.amount = amount;
    }

    public void execute() throws CommandException{
        if (amount > 1000000000) {
            throw new CommandException(MESSAGE_AMOUNT_EXCEEDED);
        }
        Budget targetBudget = allRecordList.editBudget(this.month, this.amount);
        TextUi.showBudgetEditedMessage(targetBudget);
    }
}
