package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.commands.Command;
import seedu.duke.commands.flights.AddFlightCommand;
import seedu.duke.data.Flight;
import seedu.duke.data.ClientList;
import seedu.duke.data.TourList;
import seedu.duke.data.FlightList;
import seedu.duke.data.ClientPackageList;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddFlightCommandTest {
    @Test
    void addFlightCommand_validData_correctlyConstructed() {
        Flight testFlight = new Flight(new String[]{"SQ-JPN1", "JPN", "SG", "23/10/21 13:00", "27/10/21 02:00"});
        Command addFlight = new AddFlightCommand(testFlight);

        Flight retrieveFlight = ((AddFlightCommand) addFlight).getFlight();
        assertEquals("SQ-JPN1", retrieveFlight.getId());
        assertEquals("JPN", retrieveFlight.getDepartDestination());
        assertEquals("SG", retrieveFlight.getReturnDestination());
        assertEquals("23/10/21 13:00", retrieveFlight.getDepartDate());
        assertEquals("27/10/21 02:00", retrieveFlight.getReturnDate());
    }

    @Test
    void addFlightCommand_emptyFlightList_populatedFlightList() throws TourPlannerException {
        FlightList testFlightList = new FlightList();
        ClientList dummyClientList = new ClientList();
        TourList dummyTourList = new TourList();
        ClientPackageList dummyPackageList = new ClientPackageList();
        Ui testUi = new Ui();
        Flight testFlight = new Flight(new String[]{"SQ-JPN1", "JPN", "SG", "23/10/21 13:00", "27/10/21 02:00"});
        Command addFlight = new AddFlightCommand(testFlight);
        addFlight.setData(dummyClientList, testFlightList, dummyTourList, dummyPackageList, testUi);
        addFlight.execute();

        Flight retrieveFlight = testFlightList.getFlightByIndex(0);
        assertEquals("SQ-JPN1", retrieveFlight.getId());
        assertEquals("JPN", retrieveFlight.getDepartDestination());
        assertEquals("SG", retrieveFlight.getReturnDestination());
        assertEquals("23/10/21 13:00", retrieveFlight.getDepartDate());
        assertEquals("27/10/21 02:00", retrieveFlight.getReturnDate());
    }

}
