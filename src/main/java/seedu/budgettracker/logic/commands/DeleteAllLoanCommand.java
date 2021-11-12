//@@author EdisonZhong17

package seedu.budgettracker.logic.commands;

import seedu.budgettracker.logic.commands.exceptions.CommandException;
import seedu.budgettracker.ui.TextUi;

//@@author EdisonZhong17
public class DeleteAllLoanCommand extends DeleteCommand {

    public static final String MESSAGE_USAGE = "Delete all loan records.\n"
            + "Parameters: -l m/MONTH\n";
    public final int month;

    public DeleteAllLoanCommand(int month) {
        this.month = month;
    }

    @Override
    public void execute() throws CommandException {
        int sizeBeforeDeletion = allRecordList.getLoanListSize(month);
        for (int i = 1; i <= sizeBeforeDeletion; i++) {
            allRecordList.deleteLoan(1, month);
        }
        TextUi.showAllLoanDeletedMessage();
    }
}
