package seedu.command;

import seedu.ui.TextUi;

public class HelpCommand extends Command {

    public HelpCommand() {

    }

    public void execute() {
        TextUi.printHelpMessage();
    }
}

