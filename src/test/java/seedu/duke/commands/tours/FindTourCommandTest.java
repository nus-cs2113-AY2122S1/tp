package seedu.duke.commands.tours;

import org.junit.jupiter.api.Test;
import seedu.duke.TourPlannerException;
import seedu.duke.Ui;
import seedu.duke.commands.Command;
import seedu.duke.data.Client;
import seedu.duke.data.ClientList;
import seedu.duke.data.ClientPackage;
import seedu.duke.data.ClientPackageList;
import seedu.duke.data.Flight;
import seedu.duke.data.FlightList;
import seedu.duke.data.Tour;
import seedu.duke.data.TourList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindTourCommandTest {

    private static Tour TEST_TOUR_ONE = new Tour(new String[]{"JPN", "Japan Basic Tour", "1500.00"});
    private static Tour TEST_TOUR_TWO = new Tour(new String[]{"KOR", "Korea Cultural Tour", "3000.00"});
    private static Client TEST_CLIENT = new Client(new String[]{"c001", "Bo Tuan", "93338333", "bt@mail.com"});
    private static Flight TEST_FLIGHT = new Flight(new String[]{"SQ-JPN", "JPN", "SG", "20/10/2021 18:00",
        "21/10/2021 03:00"});
    private static ClientPackage TEST_CLIENTPACKAGE = new ClientPackage("p001",
            TEST_CLIENT, TEST_TOUR_ONE, TEST_FLIGHT);

    private static final String VALID_DATA_OUTPUT = "This is the tour that matches your search\n"
            + TEST_TOUR_ONE + "\n\n\n"
            + "Subscribed Clients:\n\n"
            + "Total Subscribed Clients: 0";
    private static final String VALID_SUBSCRIPTION_OUTPUT = "This is the tour that matches your search\n"
            + TEST_TOUR_ONE + "\n\n\n"
            + "Subscribed Clients:\n"
            + "1. Bo Tuan (ID: c001)\n"
            + "\n"
            + "Total Subscribed Clients: 1";
    private static final String INVALID_DATA_OUTPUT = "ERROR: Tour cannot be found. "
            + "Please try another tour ID.";

    PrintStream previousConsole = System.out;
    ByteArrayOutputStream newConsole = new ByteArrayOutputStream();

    ClientList dummyClientList = new ClientList();
    TourList testTourList = new TourList();
    FlightList dummyFlightList = new FlightList();
    ClientPackageList dummyPackageList = new ClientPackageList();
    Ui testUi = new Ui();

    @Test
    void findTourCommand_validData_correctlyConstructed() throws TourPlannerException {
        System.setOut(new PrintStream(newConsole));
        testTourList.add(TEST_TOUR_ONE);
        testTourList.add(TEST_TOUR_TWO);
        Command findTour = new FindTourCommand("JPN");
        findTour.setData(dummyClientList, dummyFlightList, testTourList, dummyPackageList, testUi);
        findTour.execute();
        previousConsole.println(newConsole.toString());
        System.setOut(previousConsole);
        String actualString = newConsole.toString().trim().replace("\r\n", "\n");
        assertEquals(VALID_DATA_OUTPUT, actualString);
    }

    @Test
    void findTourCommand_validSubscription_correctlyConstructed() throws TourPlannerException {
        System.setOut(new PrintStream(newConsole));

        dummyClientList.add(TEST_CLIENT);
        testTourList.add(TEST_TOUR_ONE);
        dummyFlightList.add(TEST_FLIGHT);
        dummyPackageList.add(TEST_CLIENTPACKAGE);

        Command findTour = new FindTourCommand("JPN");
        findTour.setData(dummyClientList, dummyFlightList, testTourList, dummyPackageList, testUi);
        findTour.execute();

        previousConsole.println(newConsole.toString());
        System.setOut(previousConsole);
        String actualString = newConsole.toString().trim().replace("\r\n", "\n");
        assertEquals(VALID_SUBSCRIPTION_OUTPUT, actualString);
    }

    @Test
    void findTourCommand_invalidData_correctlyConstructed() throws TourPlannerException {
        System.setOut(new PrintStream(newConsole));
        testTourList.add(TEST_TOUR_ONE);
        testTourList.add(TEST_TOUR_TWO);
        Command findTour = new FindTourCommand("SGP");
        findTour.setData(dummyClientList, dummyFlightList, testTourList, dummyPackageList, testUi);
        findTour.execute();

        previousConsole.println(newConsole.toString());
        System.setOut(previousConsole);
        String actualString = newConsole.toString().trim().replace("\r\n", "\n");
        assertEquals(INVALID_DATA_OUTPUT, actualString);
    }

}
