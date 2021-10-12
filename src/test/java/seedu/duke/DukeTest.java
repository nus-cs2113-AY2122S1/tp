package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class DukeTest {

    @Test
    public void testSetBudget() {
        Trip trip = new Trip();
        trip.setBudget("5000.00");
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
    public void testSetCurrencyInfo() {
        Trip trip = new Trip();
        trip.setForeignCurrency("USD");
        trip.setRepaymentCurrency("SGD");
        trip.setExchangeRate(1.3);
        assertEquals("USD", trip.getForeignCurrency());
        assertEquals("SGD", trip.getRepaymentCurrency());
        assertEquals(1.3, trip.getExchangeRate());
    }

    @Test
    public void testSetDate() {
        Trip trip = new Trip();
        trip.setDateOfTrip("23-09-2021");
        assertEquals("23 Sep 2021", trip.getDateOfTripString());
    }

    @Test
    public void sampleTest() {
        assertTrue(true);
    }
}
