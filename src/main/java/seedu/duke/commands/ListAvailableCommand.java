package seedu.duke.commands;

import seedu.duke.data.Catalogue;
import seedu.duke.data.Item;
import seedu.duke.ui.TextUI;

import static seedu.duke.common.Messages.LIST_AVAILABLE_MESSAGE;

/**
 * Class encapsulating an exit command.
 */
public class ListAvailableCommand extends Command {

    /**
     * Single constructor, no parameters.
     */
    public ListAvailableCommand() {
    }

    /**
     * Prints exit message.
     * Overrides method from parent class.
     * @param ui Object that handles user IO
     */
    @Override
    public void execute(TextUI ui, Catalogue catalogue) {
        ui.print(LIST_AVAILABLE_MESSAGE);
        for (Item temp : catalogue.getAllItems()) {
            if (temp.getStatus().equals("Available")) {
                System.out.println(temp.getID() + " " + temp.getTitle() + " " + temp.getStatus());
            }
        }
    }
}