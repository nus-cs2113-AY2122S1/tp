package seedu.command;

import seedu.ui.TextUi;

public class ExitCommand extends Command {
    public void execute() {
        TextUi.exitMessage();
    }
}
