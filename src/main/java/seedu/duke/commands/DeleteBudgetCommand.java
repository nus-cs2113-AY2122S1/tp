package seedu.duke.commands;

import seedu.duke.ui.TextUi;

import java.time.LocalDate;

public class DeleteBudgetCommand extends DeleteCommand {

    public static final String MESSAGE_USAGE = "Delete a budget record.\n"
            + "Parameters: b/";
    public final String commandParams;

    public DeleteBudgetCommand(String commandParams) {
        this.commandParams = commandParams;
    }

    @Override
    public void execute() {
        recordList.deleteBudget(LocalDate.now().getMonthValue());
        TextUi.showBudgetDeletedMessage();
    }
}
