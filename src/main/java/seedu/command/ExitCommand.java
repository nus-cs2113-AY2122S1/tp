package seedu.command;

import seedu.ui.TextUi;

public class ExitCommand extends Command {

    public ExitCommand() {

    }

    public void execute() {
        TextUi.printExitMessage();
    }
}
