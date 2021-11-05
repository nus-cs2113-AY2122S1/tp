package seedu.budgettracker.logic.commands;

import seedu.budgettracker.data.AllRecordList;
import seedu.budgettracker.ui.TextUi;

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
    public void execute() {
        TextUi.showSingleExpenditureDeletedMessage(index + 1,
                allRecordList.getExpenditure(index, month), allRecordList);
        allRecordList.deleteExpenditure(index + 1, month);
    }
}