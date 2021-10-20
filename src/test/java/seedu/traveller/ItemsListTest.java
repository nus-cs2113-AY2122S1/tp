package seedu.traveller;

import org.junit.jupiter.api.Test;

import seedu.traveller.items.Dining;
import seedu.traveller.items.Housing;
import seedu.traveller.items.Item;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;


public class ItemsListTest {
    private final ItemsList itemsList;

    public ItemsListTest() {
        this.itemsList = new ItemsList();
    }

    @Test
    public void addItem_success() {
        Item item1 = new Item("7-9pm", "Eat dinner");
        Item item2 = new Item("12am", "See northern lights");
        itemsList.addItem(item1);
        itemsList.addItem(item2);
        assertEquals(item1, itemsList.getItem(0));
        assertEquals(item2, itemsList.getItem(1));
        assertEquals(2, itemsList.getSize());
    }

    @Test
    public void deleteDay_success() {
        Item item1 = new Item("7-9pm", "Eat dinner");
        Item item2 = new Item("12am", "See northern lights");
        itemsList.addItem(item1);
        itemsList.addItem(item2);
        itemsList.deleteItem(0);
        assertEquals(item2, itemsList.getItem(0));
        assertEquals(1, itemsList.getSize());
    }

    @Test
    public void editItem_success() {
        Housing housingItem = new Housing("HolidayInn", "Check-in 7pm");
        Dining diningItem = new Dining("Collins", "Booked at 8pm");
        itemsList.addItem(housingItem);
        itemsList.addItem(diningItem);

        Housing housingItem2 = new Housing("HotelCalifornia", "Check-in 2pm");

        itemsList.editItem(0, housingItem2);
        assertEquals(housingItem2, itemsList.getItem(0));
    }

    @Test
    public void searchItem_success() {
        Housing housingItem = new Housing("HolidayInn", "Check-in 7pm");
        Housing housingItem2 = new Housing("HotelCalifornia", "Check-in 2pm");
        Dining diningItem = new Dining("Collins", "Booked at 8pm");
        Dining diningItem2 = new Dining("AnotherCollins", "Booked at 5pm");
        itemsList.addItem(housingItem);
        itemsList.addItem(housingItem2);
        itemsList.addItem(diningItem);
        itemsList.addItem(diningItem2);

        List<Item> ans = new ArrayList<>();
        ans.add(diningItem);
        ans.add(diningItem2);

        assertEquals(ans.toString(), itemsList.searchItem("Collins").toString());
    }
}
