package seedu.duke.commands;

import seedu.duke.ui.TextUI;

import static seedu.duke.common.Messages.EXIT_MESSAGE;

public class ExitCommand extends Command {
    /**
     * Single constructor, no parameters
     */
    public ExitCommand() {}

    /**
     * Prints exit message
     * Overrides method from parent class
     * @param ui Object that handles user IO
     */
    @Override
    public void execute(TextUI ui) {
        ui.print(EXIT_MESSAGE);
    }
}
