package seedu.commands;

import seedu.parser.Parser;
import seedu.storage.ItemList;
import seedu.storage.ShoppingList;
import seedu.ui.Ui;

//@@author uosjapuelks
public class ExitCommand extends Command {

    /**
     * Constructor for CloseCommand.
     */
    public ExitCommand() {
        this.isExit = true;
    }

    /**
     * Executes the CloseCommand.
     *
     * @param ui           The ui object to interact with user.
     * @param parser       The parser object to parse user inputs.
     * @param itemList     The itemList object.
     * @param shoppingList The shoppingList object.
     */
    @Override
    public void execute(Ui ui, Parser parser, ItemList itemList, ShoppingList shoppingList) {
        ui.printExitMessage();
    }
}
