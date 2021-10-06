package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class DukeTest {

    @Test
    public void testSetNameOfPerson () {
        Trip trip = new Trip();
        trip.setLocation("America");
        assertEquals("America", trip.getLocation());
    }
    
    @Test
    public void sampleTest() {
       assertTrue(true);
    }
}
