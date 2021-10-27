package seedu.duke.data.item;

import org.junit.jupiter.api.Test;
import seedu.duke.common.Status;
import seedu.duke.data.Item;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemTest {
    Item item = new Item("Junglebook", "1", Status.AVAILABLE);

    @Test
    void testGetTitle() {
        assertEquals("Junglebook", item.getTitle());
    }

    @Test
    void testSetTitle() {
        item.setTitle("notJunglebook");
        assertEquals("notJunglebook", item.getTitle());
    }

    @Test
    void testGetID() {
        assertEquals("1", item.getID());
    }

    @Test
    void testSetID() {
        item.setID("2");
        assertEquals("2", item.getID());
    }

    @Test
    void testGetStatus() {
        assertEquals(Status.AVAILABLE, item.getStatus());
    }

    @Test
    void testSetStatus() {
        item.setStatus(Status.LOANED);
        assertEquals(Status.LOANED, item.getStatus());
    }
}