package seedu.command;

import seedu.ui.TextUi;

public class InvalidCommand extends Command {

    public void execute() {
        TextUi.printInvalidCommandMessage();
    }

}
