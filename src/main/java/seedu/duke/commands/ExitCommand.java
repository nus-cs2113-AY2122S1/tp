package seedu.duke.commands;

import seedu.duke.ui.TextUi;

public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    public void execute() {
        TextUi.showExitMessage();
    }
}
