package seedu.traveller;

import org.junit.jupiter.api.Test;
import seedu.traveller.items.Dining;
import seedu.traveller.items.Housing;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DayTest {
    private final Day day;

    public DayTest() {
        this.day = new Day();
    }

    @Test
    public void addItem_success() {
        Housing housingItem = new Housing("HolidayInn", "Check-in 7pm");
        Dining diningItem = new Dining("Collins", "Booked at 8pm");
        day.addItem(housingItem);
        day.addItem(diningItem);
        assertEquals(housingItem, day.getItem(0));
        assertEquals(diningItem, day.getItem(1));
    }

    @Test
    public void deleteDay_success() {
        Housing housingItem = new Housing("HolidayInn", "Check-in 7pm");
        Dining diningItem = new Dining("Collins", "Booked at 8pm");
        day.addItem(housingItem);
        day.addItem(diningItem);
        day.deleteItem(0);
        assertEquals(diningItem, day.getItem(0));
    }
}
