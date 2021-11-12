package seedu.storage;

import org.junit.jupiter.api.Test;
import seedu.data.exception.FridgetException;
import seedu.data.item.Item;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class ItemListTest {
    //@@author uosjapuelks
    private final LocalDate today = LocalDate.now();
    private final Item itemAExpireTomorrow = new Item("aaa", today.plusDays(1));
    private final Item itemBExpireToday = new Item("bbb", today);
    private final Item itemCExpireYesterday = new Item("ccc", today.minusDays(1));
    private final ItemList testList = new ItemList();
    private final ArrayList<Item> expectedList = new ArrayList<Item>();
    private final ItemList itemList = new ItemList();

    private void fillExpectedList(Item a, Item b, Item c) {
        expectedList.add(a);
        expectedList.add(b);
        expectedList.add(c);
    }

    private ItemList filledTestList() {
        try {
            testList.addItem(itemCExpireYesterday);
            testList.addItem(itemAExpireTomorrow);
            testList.addItem(itemBExpireToday);
        } catch (Exception e) {
            fail();
        }

        return testList;
    }

    @Test
    void containsItem_aInTestList_expectedResultTrue() {
        try {
            testList.addItem(itemAExpireTomorrow);
            assertTrue(testList.containsItem(itemAExpireTomorrow));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void containsItem_aNotInTestList_expectedResultTrue() {
        try {
            testList.addItem(itemCExpireYesterday);
            assertFalse(testList.containsItem(itemAExpireTomorrow));
        } catch (Exception e) {
            fail();
        }
    }
    //@@author

    @Test
    void searchItemNameExist_itemInTestList_expectedResultTrue() {
        try {
            Item itemA = new Item("burger", today.plusDays(1));
            Item itemB = new Item("Burger", today.minusDays(1));
            itemList.addItem(itemA);
            assertTrue(itemList.searchItemNameExist(itemB));
        } catch (Exception e) {
            fail();
        }
    }

    //@@author uosjapuelks
    @Test
    void sortItem_sortByDate_expectCToBToA() {
        fillExpectedList(itemCExpireYesterday, itemBExpireToday, itemAExpireTomorrow);
        try {
            assertEquals(expectedList, filledTestList().sortItem(true));
        } catch (FridgetException e) {
            fail();
        }
    }

    @Test
    void sortItem_sortByName_expectAToBToC() {
        fillExpectedList(itemAExpireTomorrow, itemBExpireToday, itemCExpireYesterday);
        try {
            assertEquals(expectedList, filledTestList().sortItem(false));
        } catch (FridgetException e) {
            fail();
        }
    }

    @Test
    void getItem_CtoAtoBInput_expectCToAToBOutput() {
        fillExpectedList(itemCExpireYesterday, itemAExpireTomorrow, itemBExpireToday);
        try {
            assertEquals(expectedList, filledTestList().getItemList("r"));
        } catch (FridgetException e) {
            fail();
        }
    }

    //@@author zonglun99
    @Test
    void findAllMatchingItems_aaaItemNameInput_exceptionThrown() {
        String searchTerm = "aaa";
        assertThrows(FridgetException.class, () -> {
            itemList.findAllMatchingItems(searchTerm);
        });
    }

    @Test
    void findAllMatchingItems_aaaItemNameInput_expectSizeOfMatchingListEqualsOne() throws FridgetException {
        String searchTerm = "aaa";
        itemList.addItem(itemAExpireTomorrow);
        ArrayList<Item> matchingItems = itemList.findAllMatchingItems(searchTerm);
        assertEquals(1, matchingItems.size());
    }

    //@@uosjapuelks
    @Test
    void updateQuantity_aaaItemNameTo800_expectQuantityOfItemToBecome() throws FridgetException {
        int toQuantity = 800;
        Item toUpdateItem = itemAExpireTomorrow;
        itemList.addItem(toUpdateItem);
        itemList.updateQuantity(toUpdateItem, toQuantity);
        assertEquals(800, toUpdateItem.getQuantity());
    }
}
