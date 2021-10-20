package seedu.traveller;

import org.junit.jupiter.api.Test;
import seedu.traveller.items.Dining;
import seedu.traveller.items.Housing;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ItemsListTest {
    private final ItemsList itemsList;

    public ItemsListTest() {
        this.itemsList = new ItemsList();
    }

    @Test
    public void addItem_success() {
        Housing housingItem = new Housing("HolidayInn", "Check-in 7pm");
        Dining diningItem = new Dining("Collins", "Booked at 8pm");
        itemsList.addItem(housingItem);
        itemsList.addItem(diningItem);
        assertEquals(housingItem, itemsList.getItem(0));
        assertEquals(2, itemsList.getSize());
    }

    @Test
    public void deleteDay_success() {
        Housing housingItem = new Housing("HolidayInn", "Check-in 7pm");
        Dining diningItem = new Dining("Collins", "Booked at 8pm");
        itemsList.addItem(housingItem);
        itemsList.addItem(diningItem);
        itemsList.deleteItem(0);
        assertEquals(diningItem, itemsList.getItem(0));
        assertEquals(1, itemsList.getSize());
    }
}
