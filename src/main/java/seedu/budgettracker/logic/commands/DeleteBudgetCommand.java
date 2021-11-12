//@@author EdisonZhong17

package seedu.budgettracker.logic.commands;

import seedu.budgettracker.logic.commands.exceptions.CommandException;
import seedu.budgettracker.ui.TextUi;

public class DeleteBudgetCommand extends DeleteCommand {

    public static final String MESSAGE_USAGE = "Delete a budget record.\n"
            + "Parameters: -b m/MONTH\n";

    private final int month;

    public DeleteBudgetCommand(int month) {
        this.month = month;
    }

    @Override
    public void execute() throws CommandException {
        allRecordList.deleteBudget(month);
        TextUi.showBudgetDeletedMessage();
    }
}
