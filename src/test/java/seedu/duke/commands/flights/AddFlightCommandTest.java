package seedu.duke.commands.flights;

import org.junit.jupiter.api.Test;
import seedu.duke.Ui;
import seedu.duke.data.ClientList;
import seedu.duke.data.ClientPackageList;
import seedu.duke.data.Flight;
import seedu.duke.data.FlightList;
import seedu.duke.data.TourList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddFlightCommandTest {

    public static final String TEST_FLIGHT_ID = "SQ-JPN1";
    public static final String TEST_FLIGHT_DEPART = "JPN";
    public static final String TEST_FLIGHT_RETURN = "SG";
    public static final String TEST_DEPART_TIME = "23/10/21 13:00";
    public static final String TEST_RETURN_TIME = "27/10/21 02:00";

    FlightList testFlightList = new FlightList();
    ClientList dummyClientList = new ClientList();
    TourList dummyTourList = new TourList();
    ClientPackageList dummyPackageList = new ClientPackageList();
    Ui testUi = new Ui();

    @Test
    void addFlightCommand_validData_correctlyConstructed() {
        Flight testFlight =
                new Flight(new String[]{
                        TEST_FLIGHT_ID,
                        TEST_FLIGHT_DEPART,
                        TEST_FLIGHT_RETURN,
                        TEST_DEPART_TIME,
                        TEST_RETURN_TIME});
        AddFlightCommand addFlight = new AddFlightCommand(testFlight);

        Flight retrieveFlight = addFlight.getFlight();
        assertEquals(TEST_FLIGHT_ID, retrieveFlight.getId());
        assertEquals(TEST_FLIGHT_DEPART, retrieveFlight.getDepartDestination());
        assertEquals(TEST_FLIGHT_RETURN, retrieveFlight.getReturnDestination());
        assertEquals(TEST_DEPART_TIME, retrieveFlight.getDepartDate());
        assertEquals(TEST_RETURN_TIME, retrieveFlight.getReturnDate());
    }

    @Test
    void addFlightCommand_emptyFlightList_populatedFlightList() {
        Flight testFlight =
                new Flight(new String[]{
                        TEST_FLIGHT_ID,
                        TEST_FLIGHT_DEPART,
                        TEST_FLIGHT_RETURN,
                        TEST_DEPART_TIME,
                        TEST_RETURN_TIME});
        AddFlightCommand addFlight = new AddFlightCommand(testFlight);
        addFlight.setData(dummyClientList, testFlightList, dummyTourList, dummyPackageList, testUi);
        addFlight.execute();

        Flight retrieveFlight = testFlightList.getFlightByIndex(0);
        assertEquals(TEST_FLIGHT_ID, retrieveFlight.getId());
        assertEquals(TEST_FLIGHT_DEPART, retrieveFlight.getDepartDestination());
        assertEquals(TEST_FLIGHT_RETURN, retrieveFlight.getReturnDestination());
        assertEquals(TEST_DEPART_TIME, retrieveFlight.getDepartDate());
        assertEquals(TEST_RETURN_TIME, retrieveFlight.getReturnDate());
    }

}
