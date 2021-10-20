package seedu.traveller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DayTest {
    private final Day day;

    public DayTest() {
        this.day = new Day();
    }

    @Test
    public void addItem_success() {
        Item item1 = new Item("7-9pm", "Eat dinner");
        Item item2 = new Item("12am", "See northern lights");
        day.addItem(item1);
        day.addItem(item2);
        assertEquals(item1, day.getItem(0));
        assertEquals(item2, day.getItem(1));
    }

    @Test
    public void deleteDay_success() {
        Item item1 = new Item("7-9pm", "Eat dinner");
        Item item2 = new Item("12am", "See northern lights");
        day.addItem(item1);
        day.addItem(item2);
        day.deleteItem(0);
        assertEquals(item2, day.getItem(0));
    }
}
