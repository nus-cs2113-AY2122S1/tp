package seedu.commands;

import seedu.data.exception.FridgetException;
import seedu.data.item.Item;
import seedu.parser.Parser;
import seedu.storage.ItemList;
import seedu.storage.ShoppingList;
import seedu.ui.Ui;

import java.util.ArrayList;

//@@author BryanElmer
public class ShopListCommand extends Command {
    /**
     * Constructor for ShopListCommand.
     */
    public ShopListCommand() {
    }

    /**
     * Executes the command.
     *
     * @param ui           The ui object to interact with user.
     * @param parser       The parser object to parse user inputs.
     * @param itemList     The itemList object.
     * @param shoppingList The shoppingList object.
     * @throws FridgetException The error object thrown.
     */
    @Override
    public void execute(Ui ui, Parser parser, ItemList itemList, ShoppingList shoppingList)
            throws FridgetException {
        ArrayList<Item> listOfItems = shoppingList.getShoppingList("default");
        ui.printListMessage(listOfItems, "", true);
    }
}
