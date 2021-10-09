package seedu.duke.data.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemTest {
    Item item = new Item("Junglebook", "1", "available");

    @Test
    void getTitle() {
        assertEquals("Junglebook", item.getTitle());
    }

    @Test
    void setTitle() {
        item.setTitle("notJunglebook");
        assertEquals("notJunglebook", item.getTitle());
    }

    @Test
    void getID() {
        assertEquals("1", item.getID());
    }

    @Test
    void setID() {
        item.setID("2");
        assertEquals("2", item.getID());
    }

    @Test
    void getStatus() {
        assertEquals("available", item.getStatus());
    }

    @Test
    void setStatus() {
        item.setStatus("notAvailable");
        assertEquals("notAvailable", item.getStatus());
    }
}