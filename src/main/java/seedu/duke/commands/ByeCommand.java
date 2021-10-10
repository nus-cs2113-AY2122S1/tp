package seedu.duke.commands;

import seedu.duke.Ui;

public class ByeCommand extends Command {
    public static boolean isRunning = true;

    public ByeCommand() {
    }

    public CommandResult execute() {
        isRunning = false;
        return new CommandResult(Ui.getByeMessage());
    }
}
