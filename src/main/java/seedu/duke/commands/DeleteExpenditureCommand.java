package seedu.duke.commands;

import seedu.duke.data.records.Expenditure;
import seedu.duke.data.RecordList;
import seedu.duke.textfiletools.DeleteFromTextFile;
import seedu.duke.ui.TextUi;

public class DeleteExpenditureCommand extends DeleteCommand {

    public final int index;

    public static final String MESSAGE_USAGE = "Delete an expenditure record.\n"
            + "Parameters: e/INDEX_OF_EXPENDITURE";

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