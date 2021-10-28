package seedu.budgettracker.logic.commands;

import seedu.budgettracker.ui.TextUi;

public class DeleteAllExpenditureCommand extends DeleteCommand {

    public static final String MESSAGE_USAGE = "Delete all expenditure record.\n"
            + "Parameters: -e m/MONTH";
    public final int month;

    public DeleteAllExpenditureCommand(int month) {
        this.month = month;
    }

    /**
     * LocalDate.now().getMonthValue() is being used as a placeholder
     */
    @Override
    public void execute(boolean isLoadingStorage) {
        int sizeBeforeDeletion = allRecordList.getExpenditureListSize(month);
        for (int i = 1; i <= sizeBeforeDeletion; i++) {
            allRecordList.deleteExpenditure(1, month);
        }
        TextUi.showAllExpenditureDeletedMessage();
    }
}
