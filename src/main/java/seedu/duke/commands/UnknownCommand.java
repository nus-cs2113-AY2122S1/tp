package seedu.duke.commands;

import seedu.duke.data.Catalogue;
import seedu.duke.ui.TextUI;

import static seedu.duke.common.Messages.UNKNOWN_MESSAGE;

//@@author avellinwong01
/**
 * Class encapsulating an unknown command not recognised by the application.
 */
public class UnknownCommand extends Command {

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
//@@author avellinwong01
