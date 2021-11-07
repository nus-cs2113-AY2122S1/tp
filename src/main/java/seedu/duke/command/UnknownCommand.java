package seedu.duke.command;

import seedu.duke.Ui;

/**
 * The command class in the command
 * is unknown.
 */
public class UnknownCommand extends Command {

    @Override
    public void execute() {
        Ui.printWithBorder("Unknown command. Please try again.");
    }
}
