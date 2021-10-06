package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class DukeTest {

    @Test
    public void testSetBudget() {
        Trip trip = new Trip();
        trip.setBudget(5000.00F);
        assertEquals(5000.00F, trip.getBudget());
    }

    @Test
    public void testSetLocation() {
        Trip trip = new Trip();
        trip.setLocation("America");
        assertEquals("America", trip.getLocation());
    }

    @Test
    public void testSetName() {
        Person person = new Person("CS2113T", true);
        person.setName("Duke");
        assertEquals("Duke", person.getName());
    }

    @Test
    public void sampleTest() {
        assertTrue(true);
    }
}
