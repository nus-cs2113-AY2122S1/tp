package seedu.budgettracker.logic.commands;

import seedu.budgettracker.ui.TextUi;

public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    public void execute(boolean isLoadingStorage) {
        TextUi.showExitMessage();
    }
}
