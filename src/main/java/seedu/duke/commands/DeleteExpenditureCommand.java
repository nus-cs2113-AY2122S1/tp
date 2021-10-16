package seedu.duke.commands;

import seedu.duke.textfiletools.DeleteFromTextFile;
import seedu.duke.ui.TextUi;

import java.time.LocalDate;

public class DeleteExpenditureCommand extends DeleteCommand {

    public static final String MESSAGE_USAGE = "Delete an expenditure record.\n"
            + "Parameters: e/INDEX_OF_EXPENDITURE";
    public final int index;

    public DeleteExpenditureCommand(int index) {
        this.index = index;
    }

    /**
     * LocalDate.now().getMonthValue() is being used as a placeholder
     */
    @Override
    public void execute() {
        int sizeBeforeDeletion = recordList.getExpenditureListSize(LocalDate.now().getMonthValue());
        TextUi.showExpenditureDeletedMessage(index,
                recordList.getExpenditure(index - 1, LocalDate.now().getMonthValue()));
        recordList.deleteExpenditure(index, LocalDate.now().getMonthValue());

        DeleteFromTextFile.removeLineFromFile("./data/BudgetList1.txt", index, sizeBeforeDeletion);
    }
}