package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddFlightCommandTest {
    @Test
    void addFlightCommand_validData_correctlyConstructed() {
        Flight testFlight = new Flight(new String[]{"SQ-JPN1", "JPN", "SG", "23/10/21 1300", "27/10/21 0200"});
        Command addFlight = new AddFlightCommand(testFlight);

        Flight retrieveFlight = ((AddFlightCommand) addFlight).getFlight();
        assertEquals("SQ-JPN1", retrieveFlight.getId());
        assertEquals("JPN", retrieveFlight.getToDestination());
        assertEquals("SG", retrieveFlight.getFromDestination());
        assertEquals("23/10/21 1300", retrieveFlight.getToDate());
        assertEquals("27/10/21 0200", retrieveFlight.getFromDate());
    }

    @Test
    void addFlightCommand_emptyFlightList_populatedFlightList() {
        FlightList testFlightList = new FlightList();
        ClientList dummyClientList = new ClientList();
        TourList dummyTourList = new TourList();
        ClientPackageList dummyPackageList = new ClientPackageList();
        Ui testUi = new Ui();
        Flight testFlight = new Flight(new String[]{"SQ-JPN1", "JPN", "SG", "23/10/21 1300", "27/10/21 0200"});
        Command addFlight = new AddFlightCommand(testFlight);
        addFlight.setData(dummyClientList, testFlightList, dummyTourList, dummyPackageList, testUi);
        addFlight.execute();

        Flight retrieveFlight = testFlightList.getFlightById(0);
        assertEquals("SQ-JPN1", retrieveFlight.getId());
        assertEquals("JPN", retrieveFlight.getToDestination());
        assertEquals("SG", retrieveFlight.getFromDestination());
        assertEquals("23/10/21 1300", retrieveFlight.getToDate());
        assertEquals("27/10/21 0200", retrieveFlight.getFromDate());
    }

}
