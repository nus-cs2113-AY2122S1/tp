package seedu.traveller;

import org.junit.jupiter.api.Test;

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
}
