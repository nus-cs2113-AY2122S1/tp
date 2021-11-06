package seedu.duke;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.ForceCancelException;
import seedu.duke.exceptions.SameNameException;
import seedu.duke.parser.Parser;
import seedu.duke.trip.Trip;

import java.util.ArrayList;
import java.util.SortedMap;

import static org.junit.jupiter.api.Assertions.*;

class TripTest {

    private static Trip testTrip1;

    @BeforeAll
    static void setUp() throws SameNameException, ForceCancelException {
        String[] stringArray = {"", "Canada", "02-03-2021", "cad", "0.123", "ben,jerry,tom"};
        testTrip1 = new Trip(stringArray);
    }

    @Test
    public void testNewTrip() throws ForceCancelException, SameNameException {
        String[] stringArray = {"", "Canada", "02-03-2021", "cad", "0.123", "ben,jerry,tom"};
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

    //@author yeezao
    @Test
    public void testNewTripUsingUserInput() {
        ArrayList<Trip> newListOfTrips = new ArrayList<>();
        Storage.setListOfTrips(newListOfTrips);
        createNewTripForTest();
        Trip createdTrip = Storage.getListOfTrips().get(0);
        assertNotNull(createdTrip);
        assertEquals("United States of America", createdTrip.getLocation());
        assertEquals("02 Feb 2021", createdTrip.getDateOfTripString());
        assertEquals("USD", createdTrip.getForeignCurrency());
        assertEquals("$", createdTrip.getForeignCurrencySymbol());
        assertEquals("SGD", createdTrip.getRepaymentCurrency());
        assertEquals(0.74, createdTrip.getExchangeRate());
        ArrayList<Person> personArrayList = createdTrip.getListOfPersons();
        assertNotNull(personArrayList);
        assertEquals(5, personArrayList.size());
        assertEquals("Ben", personArrayList.get(0).getName());
        assertEquals("Jerry", personArrayList.get(1).getName());
        assertEquals("Tom", personArrayList.get(2).getName());
        assertEquals("Harry", personArrayList.get(3).getName());
        assertEquals("Dick", personArrayList.get(4).getName());
    }
    //@@author

    @Test
    public void testEditLocation() throws ForceCancelException {
        testTrip1.setLocation("under the rainbow");
        assertEquals("under the rainbow", testTrip1.getLocation());
    }


    @Test
    public void testEditLocationFromPerAttribute() {
        //String parsedString = "-location over the rainbow";
    }

    @Test
    public void testEditLocationFull() {
        createNewTripForTest();
        String userInput = "edit last -location going on a trip";
        Parser.parseUserInput(userInput);
        Trip tripToCheck = Storage.getLastTrip();
        assertEquals("going on a trip", tripToCheck.getLocation());
    }

    @Test
    public void testEditDate() throws ForceCancelException {
        testTrip1.setDateOfTrip("08-08-2020");
        assertEquals("08 Aug 2020", testTrip1.getDateOfTripString());
    }

    @Test
    public void testEditDateFull() {
        String userInput = "edit last -date 09-01-1990";
        Parser.parseUserInput(userInput);
        Trip tripToCheck = Storage.getLastTrip();
        assertEquals("09 Jan 1990", tripToCheck.getDateOfTripString());
    }

    @Test
    public void testSetLocation() throws ForceCancelException {
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
    public void testSetCurrencyInfo() throws ForceCancelException {
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

    private void createNewTripForTest() {
        String userInput = "create /United States of America /02-02-2021 /USD /0.74 /Ben, Jerry, Tom, Harry, Dick";
        Parser.parseUserInput(userInput);
    }
}
