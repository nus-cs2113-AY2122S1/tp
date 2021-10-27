package seedu.duke.commands;

import seedu.duke.data.Catalogue;
import seedu.duke.ui.TextUI;
import static seedu.duke.common.Messages.EXIT_MESSAGE;

//@@author exetr
/**
 * Class encapsulating an exit command.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "exit";

    /**
     * Single constructor, no parameters.
     */
    public ExitCommand() {
    }

    /**
     * Prints exit message.
     * Overrides method from parent class.
     * @param ui Object that handles user IO
     */
    @Override
    public void execute(TextUI ui, Catalogue catalogue) {
        ui.print(EXIT_MESSAGE);
    }
}
