package seedu.command;

import seedu.ui.TextUi;

public class ExitCommand extends Command {

    public static final String commandSyntax = "exit";
    public static final String commandAction =
            "Saves the current state and exits UniMods safely";

    public ExitCommand() {

    }

    public void execute() {
        TextUi.printExitMessage();
    }
}
