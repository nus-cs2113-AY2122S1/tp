package seedu.commands;

import seedu.data.exception.FridgetException;
import seedu.data.item.Item;
import seedu.parser.Parser;
import seedu.storage.ItemList;
import seedu.storage.ShoppingList;
import seedu.ui.Ui;

import java.util.ArrayList;

//@@author uosjapuelks
public class UpdateCommand extends Command {
    /**
     * Constructor for QuantityCommand.
     */
    public UpdateCommand() {
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
    public void execute(Ui ui, Parser parser, ItemList itemList, ShoppingList shoppingList)
            throws FridgetException {
        String targetItem = parser.parseSearchTerm(ui.getCurrentUserInput(), Parser.CommandType.UPDATE);
        ArrayList<Item> matchingItems = itemList.findAllMatchingItems(targetItem);

        Item itemToUpdate = ui.matchItem(matchingItems, targetItem, Ui.CommandType.UPDATE);
        int newQty = ui.getUpdate(itemToUpdate);
        //Checks if quantity is zero, zero should require removal of the item.
        boolean requireRemoval = parser.parseQuantity(newQty);

        handleUpdate(ui, parser, itemList, shoppingList, itemToUpdate, newQty, requireRemoval);
    }

    /**
     * Handles changing the quantity of the item.
     *
     * @param ui             The ui object to interact with user.
     * @param parser         The parser object to parse user inputs.
     * @param itemList       The itemList object.
     * @param shoppingList   The shoppingList object.
     * @param itemToUpdate   The item to update.
     * @param newQty         The quantity to update the item to.
     * @param requireRemoval True if item should be removed from the item list.
     * @throws FridgetException The error object thrown.
     */
    private void handleUpdate(Ui ui, Parser parser, ItemList itemList, ShoppingList shoppingList, Item itemToUpdate,
                              int newQty, boolean requireRemoval) throws FridgetException {
        int qtyDiff = newQty - itemToUpdate.getQuantity();
        if (requireRemoval) {
            boolean reply = ui.suggestRemove(itemToUpdate);
            //Shutdown command if reply is 'n' thus reply is false.
            parser.parseSuggestion(reply);

            //removes item if reply is 'y' thus true.
            boolean isRemoved = itemList.removeItem(itemToUpdate, itemToUpdate.getQuantity());
            ui.printReactionToRemovingItem(itemToUpdate, itemToUpdate.getQuantity());
            if (isRemoved) {
                updateShopList(ui, shoppingList, itemToUpdate, qtyDiff, true);
            }
        } else {
            //Update does not require removal of item thus quantity in shoplist and itemlist will be updated.
            itemList.updateQuantity(itemToUpdate, newQty);
            updateShopList(ui, shoppingList, itemToUpdate, qtyDiff, false);
            ui.acknowledgeUpdate(itemToUpdate);
        }
    }

    //@@author BryanElmer
    /**
     * Updates the shopping list.
     *
     * @param ui           The ui object.
     * @param shoppingList The shoppingList object.
     * @param updatedItem  The item updated.
     * @param qty          The difference in quantity of the update.
     * @param isRemoved    True if the item is completely removed.
     */
    private void updateShopList(Ui ui, ShoppingList shoppingList, Item updatedItem, int qty, boolean isRemoved)
            throws FridgetException {
        if (qty > 0) { //qty of item has been increased
            shoppingList.removeItem(updatedItem, qty);
        } else if (isRemoved) {
            int qtyInShop = shoppingList.searchItemNameExist(updatedItem);
            int qtyToShop = ui.getShopQuantity(updatedItem, qtyInShop);
            Item addedItem = new Item(updatedItem.getItemName(), qtyToShop);
            shoppingList.addItem(addedItem, qtyToShop);
            ui.printShopUpdateMessage(addedItem, qtyInShop);
        }
    }
}
