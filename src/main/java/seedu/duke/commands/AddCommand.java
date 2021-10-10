package seedu.duke.commands;

import seedu.duke.data.item.Catalogue;
import seedu.duke.data.item.Item;
import seedu.duke.ui.TextUI;

import static seedu.duke.common.Messages.ADD_MESSAGE;

/**
 * Class encapsulating an add command.
 */
public class AddCommand extends Command {
    protected String args; // Format: t/TITLE i/ID
    protected String title;
    protected String id;

    /**
     * Sole Constructor.
     *
     * @param args Additional arguments supplied by user after command word "add"
     */
    public AddCommand(String args) {
        this.args = args;
    }

    /**
     * Executes add command.
     * Overrides method from parent class.
     *
     * @param ui Object that handles user IO
     * @param catalogue Object that encapsulates the library catalogue
     */
    @Override
    public void execute(TextUI ui, Catalogue catalogue) {
        String[] argList = args.split(" ");
        String[] titleDetails = argList[0].split("/");
        String[] idDetails = argList[1].split("/");
        title = titleDetails[1];
        id = idDetails[1];
        Item newItem = new Item(title, id, "Available");
        catalogue.add(newItem);
        ui.print(ADD_MESSAGE);
        ui.print("[" + newItem.getStatus() + "] " + newItem.getTitle());
    }
}
