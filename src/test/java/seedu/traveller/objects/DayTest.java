package seedu.traveller.objects;

import org.junit.jupiter.api.Test;
import seedu.traveller.exceptions.ItemNotFoundException;
import seedu.traveller.exceptions.TravellerException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


//@@author Uxinnn
public class DayTest {
    private final Day day;

    public DayTest() {
        this.day = new Day();
    }

    @Test
    public void addItem_success() throws TravellerException {
        Item item1 = new Item("1900", "Eat dinner");
        Item item2 = new Item("0000", "See northern lights");
        day.addItem(item1);
        day.addItem(item2);
        assertEquals(item1, day.getItem(0));
        assertEquals(item2, day.getItem(1));
    }

    @Test
    public void deleteDay_success() throws TravellerException {
        Item item1 = new Item("1900", "Eat dinner");
        Item item2 = new Item("0000", "See northern lights");
        day.addItem(item1);
        day.addItem(item2);
        day.deleteItem(0);
        assertEquals(item2, day.getItem(0));
    }

    @Test
    public void getItem_exceptionThrown() {
        assertThrows(ItemNotFoundException.class, () -> {
            day.getItem(10);
        });
        assertThrows(ItemNotFoundException.class, () -> {
            day.getItem(-1);
        });
    }

    @Test
    public void deleteItem_exceptionThrown() {
        assertThrows(ItemNotFoundException.class, () -> {
            day.deleteItem(10);
        });
        assertThrows(ItemNotFoundException.class, () -> {
            day.deleteItem(-1);
        });
    }
}
