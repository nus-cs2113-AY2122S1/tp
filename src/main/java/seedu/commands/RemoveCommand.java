package seedu.commands;

import seedu.data.exception.FridgetException;
import seedu.data.item.Item;
import seedu.parser.Parser;
import seedu.storage.ItemList;
import seedu.storage.ShoppingList;
import seedu.ui.Ui;

import java.util.ArrayList;

public class RemoveCommand extends Command {
    /**
     * Constructor for Remove Command.
     */
    public RemoveCommand() {
    }

    //@@author alvynben

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
        String nameOfItemToBeRemoved = parser.parseSearchTerm(ui.getCurrentUserInput(), Parser.CommandType.REMOVE);
        if (nameOfItemToBeRemoved.contains(" | ") | nameOfItemToBeRemoved.contains("/")) {
            throw new FridgetException("You are not able to use '/' and ' | ' in item names.");
        }
        ArrayList<Item> matchingItems = itemList.findAllMatchingItems(nameOfItemToBeRemoved);
        handleRemovalOfItem(ui, itemList, shoppingList, nameOfItemToBeRemoved, matchingItems);
    }

    /**
     * Handles all cases when user wants to remove an item based on the name of item to be removed.
     *
     * @param ui                 The Ui which will send output and collect input from the user.
     * @param itemList           The itemList where items are stored.
     * @param shoppingList       The shoppingList to be updated if item is depleted.
     * @param nameOfItemToRemove The name of the item to be removed.
     * @param matchingItems      The list of items in itemList which match the nameOfItemToRemove.
     * @throws FridgetException if the user types an invalid index to remove from itemsList.
     */
    private void handleRemovalOfItem(Ui ui, ItemList itemList, ShoppingList shoppingList,
                                     String nameOfItemToRemove, ArrayList<Item> matchingItems) throws FridgetException {
        Item itemToBeRemoved = ui.matchItem(matchingItems, nameOfItemToRemove, Ui.CommandType.REMOVE);
        handleRemovalOfMultipleQuantity(ui, itemList, shoppingList, itemToBeRemoved);
    }

    //@@author BryanElmer
    /**
     * Handles the quantity to remove when user wants to remove an item.
     *
     * @param ui              The Ui which will send output and collect input from the user.
     * @param itemList        The itemList where items are stored.
     * @param shoppingList    The shoppingList to be updated if item is depleted.
     * @param itemToBeRemoved The item to be removed.
     * @throws FridgetException if the user types an invalid quantity to remove from itemsList.
     */
    private void handleRemovalOfMultipleQuantity(Ui ui, ItemList itemList, ShoppingList shoppingList,
                                                 Item itemToBeRemoved) throws FridgetException {
        int qty;
        qty = ui.getQuantityToBeRemoved(itemToBeRemoved);
        boolean isRemoved = itemList.removeItem(itemToBeRemoved, qty);
        ui.printReactionToRemovingItem(itemToBeRemoved, qty);

        int qtyInShop = shoppingList.searchItemNameExist(itemToBeRemoved);
        if (isRemoved) {
            updateShopList(ui, shoppingList, itemToBeRemoved, qtyInShop);
        }
    }

    /**
     * This function updates the shopping list regarding a removed item based on the user's feedback.
     *
     * @param ui            The Ui which will send output and collect input from the user.
     * @param shoppingList  The shoppingList to be updated based on user's input.
     * @param itemRemoved   The item that has been removed.
     * @param qtyInShop     The quantity of the item in the shoppingList.
     * @throws FridgetException if the user types an incorrect input when asked for quantity.
     */
    private void updateShopList(Ui ui, ShoppingList shoppingList, Item itemRemoved, int qtyInShop)
            throws FridgetException {
        int qty = ui.getShopQuantity(itemRemoved, qtyInShop);
        if (qty > 0) {
            Item addedItem = new Item(itemRemoved.getItemName(), qty);
            shoppingList.addItem(addedItem, qty);
            ui.printShopUpdateMessage(addedItem, qtyInShop);
        }
    }
}
