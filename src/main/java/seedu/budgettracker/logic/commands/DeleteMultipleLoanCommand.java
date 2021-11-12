//@@author EdisonZhong17

package seedu.budgettracker.logic.commands;

import seedu.budgettracker.logic.commands.exceptions.CommandException;
import seedu.budgettracker.ui.TextUi;

import static seedu.budgettracker.common.Messages.MESSAGE_INVALID_INDEX_OF_LOAN;

public class DeleteMultipleLoanCommand extends DeleteCommand {

    public static final String MESSAGE_USAGE = "Delete multiple loan records.\n"
            + "Parameters: -l m/MONTH i/START_INDEX - END_INDEX\n"
            + "Note:\n"
            + " * If INDEX is not specified, all the loan records of this month will be deleted.";
    public final int startIndex;
    public final int endIndex;
    public final int month;

    public DeleteMultipleLoanCommand(int startIndex, int endIndex, int month) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.month = month;
    }

    @Override
    public void execute() throws CommandException {
        if (startIndex < 0 || endIndex >= allRecordList.getLoanListSize(month)) {
            throw new CommandException(MESSAGE_INVALID_INDEX_OF_LOAN);
        }
        if (startIndex >= endIndex) {
            throw new CommandException(MESSAGE_INVALID_INDEX_OF_LOAN);
        }
        for (int i = startIndex; i <= endIndex; i++) {
            TextUi.showMultipleLoanDeletedMessage(i + 1, allRecordList.getLoan(startIndex, month));
            allRecordList.deleteLoan(startIndex, month);
        }
    }
}
