package seedu.storage;

import org.junit.jupiter.api.Test;
import seedu.data.exception.FridgetException;
import seedu.data.item.Item;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

//@@author BryanElmer
public class ShoppingListTest {

    private final ShoppingList shoppingList = new ShoppingList();
    private final Item testItem = new Item("burger", 1);
    private final Item testItem1 = new Item("burger1", 1);
    private final Item testItem2 = new Item("burger2", 1);

    private void fillShoppingList(Item item1, Item item2, Item item3) {
        shoppingList.addItem(item1, 1);
        shoppingList.addItem(item2, 1);
        shoppingList.addItem(item3, 1);
    }

    @Test
    void addItem_addTwoSameItems_expectQuantityEqualTwo() {
        shoppingList.addItem(testItem, 1);
        shoppingList.addItem(testItem, 1);

        try {
            ArrayList<Item> list = shoppingList.getShoppingList("r");
            assertEquals(list.get(0).getQuantity(), 2);
        } catch (FridgetException e) {
            fail();
        }
    }

    @Test
    void addItem_addTwoItems_expectQuantityNotEqualTwo() {
        shoppingList.addItem(testItem1, 1);
        shoppingList.addItem(testItem2, 1);

        try {
            ArrayList<Item> list = shoppingList.getShoppingList("r");
            assertNotEquals(list.get(0).getQuantity(), 2);
        } catch (FridgetException e) {
            fail();
        }
    }

    @Test
    void searchItemNameExist_twoItemInList_expectQuantityEqualTwo() {
        fillShoppingList(testItem1, testItem1, testItem2);
        int qtyInList = shoppingList.searchItemNameExist(testItem1);
        assertEquals(qtyInList, 2);
    }

    @Test
    void searchItemNameExist_itemNotInList_expectQuantityEqualZero() {
        fillShoppingList(testItem1, testItem1, testItem2);
        int qtyInList = shoppingList.searchItemNameExist(testItem);
        assertEquals(qtyInList, 0);
    }

    @Test
    void sortItem_sortShoppingList_expectTestItemToTestItem1ToTestItem3() {
        fillShoppingList(testItem, testItem2, testItem1);
        ArrayList<Item> expectedList = new ArrayList<>();
        expectedList.add(testItem);
        expectedList.add(testItem1);
        expectedList.add(testItem2);

        try {
            assertEquals(shoppingList.sortItem(), expectedList);
        } catch (FridgetException e) {
            fail();
        }
    }

    @Test
    void removeItem_removeOneItemInList_expectItemToBeDeleted() {
        fillShoppingList(testItem1, testItem1, testItem2);
        shoppingList.removeItem(testItem2, 1);
        int qtyInList = shoppingList.searchItemNameExist(testItem2);
        assertEquals(qtyInList, 0);
    }

    @Test
    void removeItem_removeOneItemInList_expectItemStillInList() {
        fillShoppingList(testItem1, testItem1, testItem2);
        shoppingList.removeItem(testItem1, 1);
        int qtyInList = shoppingList.searchItemNameExist(testItem1);
        assertEquals(qtyInList, 1);
    }

    //@@author zonglun99
    @Test
    void getShoppingList_randomSortType_exceptionThrown() {
        String sortType = "random";
        assertThrows(FridgetException.class, () -> {
            shoppingList.getShoppingList(sortType);
        });
    }

    @Test
    void getShoppingList_rSortType_expectTestItemToTestItem2ToTestItem1() {
        String sortType = "r";
        fillShoppingList(testItem, testItem2, testItem1);
        ArrayList<Item> expectedList = new ArrayList<>();
        expectedList.add(testItem);
        expectedList.add(testItem2);
        expectedList.add(testItem1);
        try {
            assertEquals(shoppingList.getShoppingList(sortType), expectedList);
        } catch (FridgetException e) {
            fail();
        }
    }
}
