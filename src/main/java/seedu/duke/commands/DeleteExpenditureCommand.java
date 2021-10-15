package seedu.duke.commands;

import seedu.duke.textfiletools.DeleteFromTextFile;
import seedu.duke.ui.TextUi;

public class DeleteExpenditureCommand extends DeleteCommand {

    public static final String MESSAGE_USAGE = "Delete an expenditure record.\n"
            + "Parameters: e/INDEX_OF_EXPENDITURE";
    public final int index;

    public DeleteExpenditureCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(boolean isLoadingStorage) {
        int sizeBeforeDeletion = recordList.getSize();
        TextUi.showExpenditureDeletedMessage(index, recordList.getExpenditure(index - 1));
        recordList.deleteExpenditure(index);

        DeleteFromTextFile.removeLineFromFile("./data/BudgetList1.txt", index, sizeBeforeDeletion);
    }
}