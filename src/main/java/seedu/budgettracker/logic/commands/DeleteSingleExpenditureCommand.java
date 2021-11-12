//@@author EdisonZhong17

package seedu.budgettracker.logic.commands;

import seedu.budgettracker.data.AllRecordList;
import seedu.budgettracker.logic.commands.exceptions.CommandException;
import seedu.budgettracker.ui.TextUi;

import static seedu.budgettracker.common.Messages.MESSAGE_INVALID_INDEX_OF_EXPENDITURE;

public class DeleteSingleExpenditureCommand extends DeleteCommand {

    public static final String MESSAGE_USAGE = "Delete an expenditure record.\n"
            + "Parameters: -e i/INDEX_OF_EXPENDITURE m/MONTH\n"
            + "Note:\n"
            + " * If INDEX is not specified, all the expenditure records of this month will be deleted.\n";
    public final int index;
    public final int month;

    public DeleteSingleExpenditureCommand(int index, int month) {
        this.index = index;
        this.month = month;
    }

    @Override
    public void execute() throws CommandException {
        if (index < 0 || index >= allRecordList.getExpenditureListSize(month)) {
            throw new CommandException(MESSAGE_INVALID_INDEX_OF_EXPENDITURE);
        }
        TextUi.showSingleExpenditureDeletedMessage(index + 1,
                allRecordList.getExpenditure(index, month), allRecordList);
        allRecordList.deleteExpenditure(index, month);
    }
}