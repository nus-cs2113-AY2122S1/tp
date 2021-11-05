package seedu.budgettracker.logic.commands;

import seedu.budgettracker.ui.TextUi;

public class DeleteMultipleExpenditureCommand extends DeleteCommand {

    public static final String MESSAGE_USAGE = "Delete multiple expenditure record.\n"
            + "Parameters: -e m/MONTH i/INDEX_OF_EXPENDITURE-INDEX_OF_EXPENDITURE\n"
            + "Note:\n"
            + " * If INDEX is not specified, all the expenditure records of this month will be deleted.\n";
    public final int startIndex;
    public final int endIndex;
    public final int month;

    public DeleteMultipleExpenditureCommand(int startIndex, int endIndex, int month) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.month = month;
    }


    @Override
    public void execute() {
        for (int i = startIndex; i <= endIndex; i++) {
            TextUi.showMultipleExpenditureDeletedMessage(i,
                    allRecordList.getExpenditure(startIndex - 1, month), allRecordList);
            allRecordList.deleteExpenditure(startIndex, month);
        }
    }
}
