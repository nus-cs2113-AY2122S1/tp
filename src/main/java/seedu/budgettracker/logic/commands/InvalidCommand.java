package seedu.budgettracker.logic.commands;

import seedu.budgettracker.ui.TextUi;

public class InvalidCommand extends Command {

    public final String feedbackToUser;

    public InvalidCommand(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    @Override
    public void execute(boolean isLoadingStorage) {
        TextUi.showInvalidCommandMessage(feedbackToUser);
    }
}
