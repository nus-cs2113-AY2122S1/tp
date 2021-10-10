package seedu.duke.commands;

import seedu.duke.Ui;

public class HelpCommand extends Command {
    public HelpCommand() {
    }

    public CommandResult execute() {
        Ui.displayUserGuide();
        return new CommandResult("HelpCommand");
    }
}
