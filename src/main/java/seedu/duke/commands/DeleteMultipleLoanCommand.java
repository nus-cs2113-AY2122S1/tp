package seedu.duke.commands;

import seedu.duke.ui.TextUi;

public class DeleteMultipleLoanCommand extends DeleteCommand {

    public static final String MESSAGE_USAGE = "Delete multiple loan record.\n"
            + "Parameters: l/INDEX_OF_EXPENDITURE-INDEX_OF_EXPENDITURE m/MONTH";
    public final int startIndex;
    public final int endIndex;
    public final int month;

    public DeleteMultipleLoanCommand(int startIndex, int endIndex, int month) {
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
            TextUi.showMultipleLoanDeletedMessage(i, endIndex, allRecordList.getLoan(startIndex - 1, month));
            allRecordList.deleteLoan(startIndex, month);
        }
    }
}
