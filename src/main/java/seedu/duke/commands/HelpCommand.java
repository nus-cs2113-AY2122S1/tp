package seedu.duke.commands;

import seedu.duke.data.Catalogue;
import seedu.duke.ui.TextUI;

import static seedu.duke.common.Messages.HELP_MESSAGE;
import static seedu.duke.common.Messages.UNKNOWN_MESSAGE;

/**
 * Class encapsulating a Help Command
 */
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";

    /**
     * Single constructor, no parameters.
     */
    public HelpCommand() {
    }

    /**
     * Prints a help guide with a list of commands for the user.
     * Overrides method from parent class.
     * @param ui Object that handles user IO
     */
    @Override
    public void execute(TextUI ui, Catalogue catalogue) {
        ui.print(HELP_MESSAGE);
    }

}
