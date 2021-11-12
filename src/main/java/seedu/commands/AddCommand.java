package seedu.commands;

import seedu.data.exception.FridgetException;
import seedu.data.item.Item;
import seedu.parser.Parser;
import seedu.storage.ItemList;
import seedu.storage.ShoppingList;
import seedu.ui.Ui;

import java.util.ArrayList;

public class AddCommand extends Command {
    /**
     * Constructor for AddCommand.
     */
    public AddCommand() {
    }

    //@@author alvynben
    /**
     * Executes the "add" command.
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

        // If the user input contains ;, the user wants to add multiple items at once
        if (ui.getCurrentUserInput().contains(";")) {
            ArrayList<Item> newItems = parser.parseMultipleItemsForAdding(ui.getCurrentUserInput());
            addMultipleItemsToItemList(ui, itemList, shoppingList, newItems);
        } else {
            // Otherwise, add only one item
            Item newItem = parser.parseItemForAdding(ui.getCurrentUserInput());
            addItemToItemList(ui, itemList, newItem, shoppingList);
        }
    }

    /**
     * Adds multiple items to the Item List.
     *
     * @param ui           The ui object to interact with user.
     * @param itemList     The itemList object.
     * @param shoppingList The shoppingList object.
     * @param newItems     The ArrayList of Items to be added.
     * @throws FridgetException if adding an Item causes an error.
     */
    private void addMultipleItemsToItemList(Ui ui, ItemList itemList, ShoppingList shoppingList,
                                            ArrayList<Item> newItems) throws FridgetException {
        int indexOfItemInList = 0;
        int maxIndex = newItems.size() - 1;

        for (Item newItem : newItems) {
            addItemToItemList(ui, itemList, newItem, shoppingList);
            if (indexOfItemInList < maxIndex) {
                ui.printSeparatorLine();
            }
            indexOfItemInList++;
        }
    }

    private void addItemToItemList(Ui ui, ItemList itemList, Item newItem,
                                   ShoppingList shoppingList) throws FridgetException {
        int qtyToBeAdded = ui.getQuantityToBeAdded(newItem);
        newItem.setQuantity(qtyToBeAdded);
        int finalQty = itemList.addItem(newItem);

        int originalQty = finalQty - qtyToBeAdded;
        if (originalQty > 0) {
            ui.printReactionToAddingExistingItem(newItem, finalQty, originalQty);
        } else {
            ui.printReactionToAddingItem(newItem);
        }
        shoppingList.removeItem(newItem, newItem.getQuantity());
    }
}

