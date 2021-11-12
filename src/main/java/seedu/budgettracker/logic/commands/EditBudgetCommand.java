//@@author YEOWEIHNGWHYELAB

package seedu.budgettracker.logic.commands;

import seedu.budgettracker.data.records.Budget;
import seedu.budgettracker.data.records.exceptions.EmptyRecordException;
import seedu.budgettracker.logic.commands.exceptions.CommandException;
import seedu.budgettracker.ui.TextUi;

import static seedu.budgettracker.common.Messages.MESSAGE_AMOUNT_EXCEEDED;
import static seedu.budgettracker.common.Messages.MESSAGE_AMOUNT_ZERO_OR_NEGATIVE;

/**
 * Command that edits a target budget in the record list.
 */
public class EditBudgetCommand extends EditCommand {
    public int month;
    public double amount;
    public static final String MESSAGE_USAGE = ("Edits a budget record.\n"
            + "Parameters: -b m/MONTH a/AMOUNT\n"
            + "Note:\n"
            + " * MONTH must be strictly within the range of 1 to 12. \n");

    public EditBudgetCommand(int month, double amount) {
        this.month = month;
        this.amount = amount;
    }

    /**
     * Sets the new amount for the budget of the given month.
     *
     * @throws CommandException if amount provided is out of range
     */
    public void execute() throws CommandException {
        if (amount > 1000000000) {
            throw new CommandException(MESSAGE_AMOUNT_EXCEEDED);
        }
        if (amount <= 0) {
            throw new CommandException(MESSAGE_AMOUNT_ZERO_OR_NEGATIVE);
        }
        try {
            Budget targetBudget = allRecordList.editBudget(this.month, this.amount);
            TextUi.showBudgetEditedMessage(targetBudget);
        } catch (EmptyRecordException e) {
            TextUi.showUser(e.getMessage());
        }
    }
}
