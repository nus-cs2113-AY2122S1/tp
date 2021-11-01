package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DukeTest {

    @Test
    public void testNewTrip() throws ForceCancelException {
        String[] stringArray = {"Canada", "02-03-2021", "cad", "0.123", "ben,jerry,tom"};
        Trip trip = new Trip(stringArray);
        assertEquals("Canada", trip.getLocation());
        assertEquals("02 Mar 2021", trip.getDateOfTripString());
        assertEquals("2021-03-02", trip.getDateOfTrip().toString());
        assertEquals("CAD", trip.getForeignCurrency());
        assertEquals(0.123, trip.getExchangeRate());
        assertEquals(3, trip.getListOfPersons().size());
        assertEquals("ben", trip.getListOfPersons().get(0).getName());
        assertEquals("jerry", trip.getListOfPersons().get(1).getName());
        assertEquals("tom", trip.getListOfPersons().get(2).getName());
    }

    @Test
    public void testSetLocation() {
        Trip trip = new Trip();
        trip.setLocation("America");
        assertEquals("America", trip.getLocation());
    }

    @Test
    public void testSetName() {
        Person person = new Person("CS2113T");
        person.setName("Duke");
        assertEquals("Duke", person.getName());
    }

    @Test
    public void testSetCurrencyInfo() {
        Trip trip = new Trip();
        trip.setForeignCurrency("USD");
        trip.setRepaymentCurrency("SGD");
        trip.setExchangeRate("1.3");
        assertEquals("USD", trip.getForeignCurrency());
        assertEquals("SGD", trip.getRepaymentCurrency());
        assertEquals(1.3, trip.getExchangeRate());
    }

    @Test
    public void testSetDate() throws ForceCancelException {
        Trip trip = new Trip();
        trip.setDateOfTrip("23-09-2021");
        assertEquals("23 Sep 2021", trip.getDateOfTripString());
    }

    @Test
    public void sampleTest() {
        assertTrue(true);
    }
}
