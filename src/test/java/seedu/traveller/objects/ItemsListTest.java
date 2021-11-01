package seedu.traveller.objects;

import org.junit.jupiter.api.Test;

import seedu.traveller.exceptions.ItemNotFoundException;
import seedu.traveller.exceptions.TravellerException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ItemsListTest {
    private final ItemsList itemsList;

    public ItemsListTest() {
        this.itemsList = new ItemsList();
    }

    @Test
    public void addItem_success() throws TravellerException {
        Item item1 = new Item("1900", "Eat dinner");
        Item item2 = new Item("0000", "See northern lights");
        itemsList.addItem(item1);
        itemsList.addItem(item2);
        assertEquals(item1, itemsList.getItem(0));
        assertEquals(item2, itemsList.getItem(1));
        assertEquals(2, itemsList.getSize());
    }

    @Test
    public void deleteDay_success() throws TravellerException {
        Item item1 = new Item("1900", "Eat dinner");
        Item item2 = new Item("0000", "See northern lights");
        itemsList.addItem(item1);
        itemsList.addItem(item2);
        itemsList.deleteItem(0);
        assertEquals(item2, itemsList.getItem(0));
        assertEquals(1, itemsList.getSize());
    }

    @Test
    public void editItem_success() throws TravellerException {
        Item item1 = new Item("1900", "Check-in HolidayInn");
        Item item2 = new Item("2000", "Dinner at Collins");
        itemsList.addItem(item1);
        itemsList.addItem(item2);

        Item item3 = new Item("1400", "Check-in HotelCalifornia");

        itemsList.editItem(0, item3);
        assertEquals(item3, itemsList.getItem(0));
    }

    @Test
    public void searchItem_success() {
        Item item1 = new Item("1900", "Check-in HolidayInn");
        Item item2 = new Item("2000", "Dinner at Collins");
        Item item3 = new Item("1400", "Check-in HotelCalifornia");
        Item item4 = new Item("1700", "Dinner at AnotherCollins");
        itemsList.addItem(item1);
        itemsList.addItem(item2);
        itemsList.addItem(item3);
        itemsList.addItem(item4);

        ArrayList<Item> ans = new ArrayList<>();
        ans.add(item2);
        ans.add(item4);

        assertEquals(ans.toString(), itemsList.searchItem("Collins").toString());
    }

    @Test
    public void getItem_exceptionThrown() {
        assertThrows(ItemNotFoundException.class, () -> {
            itemsList.getItem(10);
        });
        assertThrows(ItemNotFoundException.class, () -> {
            itemsList.getItem(-1);
        });
    }

    @Test
    public void deleteItem_exceptionThrown() {
        assertThrows(ItemNotFoundException.class, () -> {
            itemsList.deleteItem(10);
        });
        assertThrows(ItemNotFoundException.class, () -> {
            itemsList.deleteItem(-1);
        });
    }
}