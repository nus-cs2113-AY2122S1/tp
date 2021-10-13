package seedu.duke.commands;

import seedu.duke.data.Catalogue;
import seedu.duke.data.Item;
import seedu.duke.ui.TextUI;

import static seedu.duke.common.Messages.LIST_ALL_MESSAGE;

/**
 * Class encapsulating an exit command.
 */
public class ListAllCommand extends Command {

    /**
     * Single constructor, no parameters.
     */
    public ListAllCommand() {
    }

    /**
     * Prints exit message.
     * Overrides method from parent class.
     * @param ui Object that handles user IO
     */
    @Override
    public void execute(TextUI ui, Catalogue catalogue) {
        ui.print(LIST_ALL_MESSAGE);
        for (Item temp : catalogue.getAllItems()) {
            System.out.println(temp);
        }
    }
}