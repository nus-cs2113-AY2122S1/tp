package seedu.duke;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.ForceCancelException;
import seedu.duke.exceptions.SameNameException;
import seedu.duke.parser.Parser;
import seedu.duke.trip.Trip;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TripTest {

    private static Trip testTrip1;
    private static InputStream origIn;

    @BeforeAll
    static void setUp() throws SameNameException, ForceCancelException {
        origIn = System.in;
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

    @Test
    public void testEditLocation() throws ForceCancelException {
        testTrip1.setLocation("under the rainbow");
        assertEquals("under the rainbow", testTrip1.getLocation());
    }

    @Test
    public void testEditLocation_BlankInput() throws ForceCancelException {
        System.setIn(new ByteArrayInputStream("in our favourite rocket ship".getBytes()));
        Storage.setScanner(new Scanner(System.in));
        testTrip1.setLocation("");
        assertEquals("in our favourite rocket ship", testTrip1.getLocation());
    }

    @Test
    public void testEditLocationFull() {
        Storage.setListOfTrips(new ArrayList<>());
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
    public void testEditDateNotParsable() throws ForceCancelException {
        System.setIn(new ByteArrayInputStream("08-12-2010".getBytes()));
        Storage.setScanner(new Scanner(System.in));
        testTrip1.setDateOfTrip("something");
        assertEquals("08 Dec 2010", testTrip1.getDateOfTripString());
    }

    @Test
    public void testEditDateBeforeEpoch() throws ForceCancelException {
        System.setIn(new ByteArrayInputStream("08-12-2010".getBytes()));
        Storage.setScanner(new Scanner(System.in));
        testTrip1.setDateOfTrip("01-01-1969");
        assertEquals("08 Dec 2010", testTrip1.getDateOfTripString());
    }

    @Test
    public void testEditDateWhichDoesNotExist() throws ForceCancelException {
        String scannerInputs = "35-02-2021" + System.lineSeparator()
                + "00-11-2021" + System.lineSeparator()
                + "25-00-2021" + System.lineSeparator()
                + "16-23-2021" + System.lineSeparator()
                + "08-12-2020";
        System.setIn(new ByteArrayInputStream(scannerInputs.getBytes()));
        Storage.setScanner(new Scanner(System.in));
        testTrip1.setDateOfTrip("29-02-2021");
        assertEquals("08 Dec 2020", testTrip1.getDateOfTripString());
    }

    @Test
    public void testEditDateFull() {
        createNewTripForTest();
        String userInput = "edit last -date 09-01-1990";
        Parser.parseUserInput(userInput);
        Trip tripToCheck = Storage.getLastTrip();
        assertEquals("09 Jan 1990", tripToCheck.getDateOfTripString());
    }

    @Test
    public void testEditExrate() throws ForceCancelException {
        testTrip1.setExchangeRate("12.0");
        assertEquals(12.0, testTrip1.getExchangeRate());
    }

    @Test
    public void testEditExRateNotParsable() throws ForceCancelException {
        System.setIn(new ByteArrayInputStream("6.1".getBytes()));
        Storage.setScanner(new Scanner(System.in));
        testTrip1.setExchangeRate("something");
        assertEquals(6.1, testTrip1.getExchangeRate());
    }

    @Test
    public void testEditExRateNotParsableWithForceCancel() {
        assertThrows(ForceCancelException.class, () -> {
            System.setIn(new ByteArrayInputStream("-cancel".getBytes()));
            Storage.setScanner(new Scanner(System.in));
            testTrip1.setExchangeRate("something");
        });
    }

    @Test
    public void testEditExrateFull() {
        String userInput = "edit last -exchangerate 0.01";
        Parser.parseUserInput(userInput);
        Trip tripToCheck = Storage.getLastTrip();
        assertEquals(0.01, tripToCheck.getExchangeRate());
    }

    @Test
    public void testOpenTripByIndex() throws ForceCancelException {
        createNewTripForTest();
        Storage.setOpenTrip(0);
        assertTrue(Storage.checkOpenTrip());
        assertEquals(Storage.getOpenTrip(), Storage.getListOfTrips().get(0));
        assertEquals(Storage.getLastTrip(), Storage.getListOfTrips().get(0));
    }

    @Test
    public void testCloseTrip() throws ForceCancelException {
        Storage.setOpenTrip(0);
        Trip trip = Storage.getOpenTrip();
        Storage.closeTrip();
        assertFalse(Storage.checkOpenTrip());
        assertEquals(Storage.getLastTrip(), trip);
    }

    @Test
    public void testOpenTripFull() throws ForceCancelException {
        createNewTripForTest();
        String userInput = "open 1";
        Parser.parseUserInput(userInput);
        assertTrue(Storage.checkOpenTrip());
        assertEquals(Storage.getOpenTrip(), Storage.getListOfTrips().get(0));
        assertEquals(Storage.getLastTrip(), Storage.getListOfTrips().get(0));
    }

    @Test
    public void testCloseTripFull() throws ForceCancelException {
        Storage.setOpenTrip(0);
        Trip trip = Storage.getOpenTrip();
        String userInput = "close";
        Parser.parseUserInput(userInput);
        assertFalse(Storage.checkOpenTrip());
        assertEquals(Storage.getLastTrip(), trip);
    }

    @Test
    public void testOpenTripNull() throws ForceCancelException {
        System.setIn(new ByteArrayInputStream("1".getBytes()));
        Storage.setScanner(new Scanner(System.in));
        createNewTripForTest();
        Storage.setOpenTrip(0);
        Storage.closeTrip();
        assertFalse(Storage.checkOpenTrip());
        Storage.getOpenTrip();
        assertEquals(Storage.getOpenTrip(), Storage.getListOfTrips().get(0));
        assertEquals(Storage.getLastTrip(), Storage.getListOfTrips().get(0));
    }

    @Test
    public void testDeleteTripFull() {
        createNewTripForTest();
        Storage.closeTrip();
        String userInput = "delete 1";
        Parser.parseUserInput(userInput);
        assertEquals(0, Storage.getListOfTrips().size());
        assertNull(Storage.getLastTrip());
    }

    @Test
    public void setListOfPersons_EmptyList() throws SameNameException, ForceCancelException {
        System.setIn(new ByteArrayInputStream("me, someone".getBytes()));
        Storage.setScanner(new Scanner(System.in));
        Trip trip = new Trip();
        trip.setListOfPersons(new ArrayList<>());
        ArrayList<Person> listOfPersons = trip.getListOfPersons();
        assertEquals("me", listOfPersons.get(0).getName());
        assertEquals("someone", listOfPersons.get(1).getName());
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

    @AfterAll
    static void restore() {
        System.setIn(origIn);
    }

    private void createNewTripForTest() {
        Storage.setListOfTrips(new ArrayList<>());
        String userInput = "create /United States of America /02-02-2021 /USD /0.74 /Ben, Jerry, Tom, Harry, Dick";
        Parser.parseUserInput(userInput);
    }
}
