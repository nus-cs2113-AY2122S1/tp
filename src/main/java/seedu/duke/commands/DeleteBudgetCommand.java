package seedu.duke.commands;

import seedu.duke.ui.TextUi;

public class DeleteBudgetCommand extends DeleteCommand {

    public final String commandParams;

    public DeleteBudgetCommand(String commandParams) {
        this.commandParams = commandParams;
    }

    public void execute() {
        recordList.deleteBudget();
        TextUi.showBudgetDeletedMessage();
    }
}
