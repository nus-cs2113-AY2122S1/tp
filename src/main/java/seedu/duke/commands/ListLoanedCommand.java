package seedu.duke.commands;

import seedu.duke.data.Catalogue;
import seedu.duke.data.Item;
import seedu.duke.ui.TextUI;

import static seedu.duke.common.Messages.DIVIDER;
import static seedu.duke.common.Messages.LIST_LOANED_MESSAGE;

/**
 * Class encapsulating an exit command.
 */
public class ListLoanedCommand extends Command {
    public static final String COMMAND_WORD = "list loaned";

    /**
     * Single constructor, no parameters.
     */
    public ListLoanedCommand() {
    }

    /**
     * Prints exit message.
     * Overrides method from parent class.
     * @param ui Object that handles user IO
     */
    @Override
    public void execute(TextUI ui, Catalogue catalogue) {
        ui.print(LIST_LOANED_MESSAGE);
        ui.print(DIVIDER);
        for (Item temp : catalogue.getAllItems()) {
            if (temp.getStatus().equals("Loaned")) {
                ui.print(temp);
            }
        }
        ui.print(DIVIDER);
    }
}