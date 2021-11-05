package seedu.budgettracker.logic.commands;

import seedu.budgettracker.ui.TextUi;

public class DeleteSingleExpenditureCommand extends DeleteCommand {

    public static final String MESSAGE_USAGE = "Delete an expenditure record.\n"
            + "Parameters: e/INDEX_OF_EXPENDITURE m/MONTH";
    public final int index;
    public final int month;

    public DeleteSingleExpenditureCommand(int index, int month) {
        this.index = index;
        this.month = month;
    }

    /**
     * LocalDate.now().getMonthValue() is being used as a placeholder
     */
    @Override
    public void execute() {
        TextUi.showSingleExpenditureDeletedMessage(index + 1,
                allRecordList.getExpenditure(index, month));
        allRecordList.deleteExpenditure(index + 1, month);
    }
}