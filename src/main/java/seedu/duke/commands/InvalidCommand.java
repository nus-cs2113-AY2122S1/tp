package seedu.duke.commands;

import seedu.duke.ui.TextUi;

public class InvalidCommand extends Command {

    @Override
    public void execute(boolean isLoadingStorage) {
        TextUi.showInvalidCommandMessage();
    }
}
