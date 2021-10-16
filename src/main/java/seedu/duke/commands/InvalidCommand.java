package seedu.duke.commands;

import seedu.duke.ui.TextUi;

public class InvalidCommand extends Command {

    public final String feedbackToUser;

    public InvalidCommand(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    @Override
    public void execute() {
        TextUi.showInvalidCommandMessage(feedbackToUser);
    }
}
