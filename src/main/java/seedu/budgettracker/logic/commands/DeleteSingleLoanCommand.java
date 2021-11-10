//@@author EdisonZhong17

package seedu.budgettracker.logic.commands;

import seedu.budgettracker.logic.commands.exceptions.CommandException;
import seedu.budgettracker.ui.TextUi;

import static seedu.budgettracker.common.Messages.MESSAGE_INVALID_INDEX_OF_LOAN;

public class DeleteSingleLoanCommand extends DeleteCommand {

    public static final String MESSAGE_USAGE = "Delete a loan record.\n"
            + "Parameters: -l m/MONTH [i/INDEX_OF_LOAN]\n"
            + "Note:\n"
            + " * If INDEX_OF_LOAN is not specified, all the loan records of this month will be deleted.\n";
    public final int index;
    public final int month;

    public DeleteSingleLoanCommand(int index, int month) {
        this.index = index;
        this.month = month;
    }

    @Override
    public void execute() throws CommandException {
        if (index < 0 || index >= allRecordList.getLoanListSize(month)) {
            throw new CommandException(MESSAGE_INVALID_INDEX_OF_LOAN);
        }
        TextUi.showSingleLoanDeletedMessage(index + 1,
                allRecordList.getLoan(index, month));
        allRecordList.deleteLoan(index, month);
    }
}