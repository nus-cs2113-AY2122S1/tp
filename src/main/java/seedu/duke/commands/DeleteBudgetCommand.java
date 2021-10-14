package seedu.duke.commands;

import seedu.duke.ui.TextUi;

public class DeleteBudgetCommand extends DeleteCommand {

    public final String commandParams;

    public static final String MESSAGE_USAGE = "Delete a budget record.\n"
            + "Parameters: b/";

    public DeleteBudgetCommand(String commandParams) {
        this.commandParams = commandParams;
    }

    @Override
    public void execute(boolean isLoadingStorage) {
        recordList.deleteBudget();
        TextUi.showBudgetDeletedMessage();
    }
}
