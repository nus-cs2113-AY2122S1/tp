package seedu.duke.commands;

import seedu.duke.ui.TextUi;

public class DeleteMultipleExpenditureCommand extends DeleteCommand {

    public static final String MESSAGE_USAGE = "Delete multiple expenditure record.\n"
            + "Parameters: e/INDEX_OF_EXPENDITURE-INDEX_OF_EXPENDITURE m/MONTH";
    public final int startIndex;
    public final int endIndex;
    public final int month;

    public DeleteMultipleExpenditureCommand(int startIndex, int endIndex, int month) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.month = month;
    }

    /**
     * LocalDate.now().getMonthValue() is being used as a placeholder
     */
    @Override
    public void execute(boolean isLoadingStorage) {
        for (int i = startIndex; i <= endIndex; i++) {
            TextUi.showMultipleExpenditureDeletedMessage(i, endIndex, allRecordList.getExpenditure(startIndex - 1, month));
            allRecordList.deleteExpenditure(startIndex, month);
        }
    }
}
