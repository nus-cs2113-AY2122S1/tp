package seedu.duke.commands;

import seedu.duke.data.Catalogue;
import seedu.duke.ui.TextUI;

//@@author exetr
/**
 * Class encapsulating an unknown command not recognised by the application.
 */
public class UnknownCommand extends Command {
    public static final String UNKNOWN_MESSAGE = "Sorry, I don't understand what you mean";

    /**
     * Single constructor, no parameters.
     */
    public UnknownCommand() {
    }

    /**
     * Prints unknown message.
     * Overrides method from parent class.
     * @param ui Object that handles user IO
     */
    @Override
    public void execute(TextUI ui, Catalogue catalogue) {
        ui.print(UNKNOWN_MESSAGE);
    }

}
