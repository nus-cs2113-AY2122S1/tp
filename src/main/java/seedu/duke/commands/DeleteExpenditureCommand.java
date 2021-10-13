package seedu.duke.commands;

import seedu.duke.data.records.Expenditure;
import seedu.duke.data.RecordList;
import seedu.duke.ui.TextUi;

public class DeleteExpenditureCommand extends DeleteCommand {

    public final int index;

    public DeleteExpenditureCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(boolean isLoadingStorage) {
        recordList.deleteExpenditure(index);
        TextUi.showExpenditureDeletedMessage(index, recordList.getExpenditure(index - 1));
    }
}