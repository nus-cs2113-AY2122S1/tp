//@@author EdisonZhong17

package seedu.budgettracker.logic.commands;

import seedu.budgettracker.logic.commands.exceptions.CommandException;
import seedu.budgettracker.ui.TextUi;

public class DeleteAllExpenditureCommand extends DeleteCommand {

    public static final String MESSAGE_USAGE = "Delete all expenditure records.\n"
            + "Parameters: -e m/MONTH i/INDEX\n";
    public final int month;

    public DeleteAllExpenditureCommand(int month) {
        this.month = month;
    }

    @Override
    public void execute() throws CommandException {
        int sizeBeforeDeletion = allRecordList.getExpenditureListSize(month);
        for (int i = 1; i <= sizeBeforeDeletion; i++) {
            allRecordList.deleteExpenditure(1, month);
        }
        TextUi.showAllExpenditureDeletedMessage();
    }
}
