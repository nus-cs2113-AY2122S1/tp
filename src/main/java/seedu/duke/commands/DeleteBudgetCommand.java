package seedu.duke.commands;

import seedu.duke.ui.TextUi;

import java.time.LocalDate;

public class DeleteBudgetCommand extends DeleteCommand {

    public static final String MESSAGE_USAGE = "Delete a budget record.\n"
            + "Parameters: b/ m/MONTH";

    private final int month;

    public DeleteBudgetCommand(int month) {
        this.month = month;
    }

    @Override
    public void execute(boolean isLoadingStorage) {
        recordList.deleteBudget(month);
        TextUi.showBudgetDeletedMessage();
    }
}
