package seedu.storage;

import seedu.data.exception.FridgetException;
import seedu.data.item.Item;

import java.util.ArrayList;

//@@author BryanElmer
public class ShoppingList {
    private ArrayList<Item> shoppingList;

    /**
     * Constructor for ShoppingList.
     */
    public ShoppingList() {
        this.shoppingList = new ArrayList<>();
    }

    /**
     * Adds an item into shoppingList.
     *
     * @param item The item to be added.
     * @param quantity The quantity to be added to the item.
     */
    public void addItem(Item item, int quantity) {
        assert item != null : "Item must not be null!";
        for (Item itemInList : shoppingList) {
            boolean isItemInListAndItemSame = itemInList.hasSameNameAs(item);

            if (isItemInListAndItemSame) {
                itemInList.addQuantity(quantity);
                return;
            }
        }

        shoppingList.add(item);
    }

    /**
     * Removes an item from the shopping list.
     *
     * @param item     Item to be removed.
     * @param quantity Quantity to be removed.
     */
    public void removeItem(Item item, int quantity) {
        assert item != null : "Item must not be null!";
        assert quantity != 0 : "Quantity removed must no be 0!";

        for (Item itemInList : shoppingList) {
            boolean isItemInListAndItemSame = itemInList.hasSameNameAs(item);

            if (isItemInListAndItemSame) {
                if (quantity >= itemInList.getQuantity()) {
                    shoppingList.remove(itemInList);
                    return;
                }
                itemInList.removeQuantity(quantity);
            }
        }
    }

    /**
     * Checks if an item with the same name exists in the shopping list and returns its quantity.
     *
     * @param item Item to be searched.
     * @return Quantity of item in the shoppingList if exist.
     */
    public int searchItemNameExist(Item item) {
        for (Item itemInList : shoppingList) {
            boolean isItemInListAndItemSame = itemInList.hasSameNameAs(item);

            if (isItemInListAndItemSame) {
                return itemInList.getQuantity();
            }
        }
        return 0;
    }

    /**
     * Sorts the item in shoppingList by description.
     *
     * @return sorted item ArrayList.
     * @throws FridgetException Error thrown when there are no items in the shoppingList.
     */
    protected ArrayList<Item> sortItem() throws FridgetException {
        if (shoppingList.isEmpty()) {
            String emptyListMessage = "You currently have nothing in your shopping list.\n"
                    + "Input \"help\" to get started!";
            throw new FridgetException(emptyListMessage);
        }
        ArrayList<Item> sortedList = new ArrayList<>(shoppingList);
        sortedList.sort(Item.ItemNameComparator);
        return sortedList;
    }

    /**
     * Returns the current shopping list.
     *
     * @param sortType The parameter to determine how the list should be sorted.
     * @return List of items in the shopping list.
     * @throws FridgetException if the parameter after shoplist is unexpected.
     */
    public ArrayList<Item> getShoppingList(String sortType) throws FridgetException {
        assert sortType != null : "Sort type must not be null!";
        switch (sortType.toLowerCase()) {
        case "r":
            return shoppingList;
        case "default":
            return sortItem();
        default:
            throw new FridgetException("Unrecognisable shoplist command. Try: <shoplist>");
        }
    }

    /**
     * Resets the shopping list.
     */
    public void resetList() {
        shoppingList = new ArrayList<>();
    }
}
