package seedu.command;

import seedu.ui.TextUi;

public class HelpCommand extends Command {
    public void execute() {
        TextUi.helpMessage();
    }
}
