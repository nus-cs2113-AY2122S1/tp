package seedu.duke.commands.flights;

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

public class FindFlightCommandTest {

    private static Flight TEST_FLIGHT_ONE = new Flight(new String[]{"SQ-JPN", "JPN", "SG", "20/10/2021 18:00",
        "21/10/2021 03:00"});
    private static Flight TEST_FLIGHT_TWO = new Flight(new String[]{"SQ-KOR", "KOR", "SG", "23/10/2021 18:00",
        "30/10/2021 03:00"});
    private static Client TEST_CLIENT = new Client(new String[]{"c001", "Bo Tuan", "93338333", "bt@mail.com"});
    private static Tour TEST_TOUR = new Tour(new String[]{"JPN", "Japan Basic Tour", "1500.00"});
    private static ClientPackage TEST_CLIENTPACKAGE = new ClientPackage("p001",
            TEST_CLIENT, TEST_TOUR, TEST_FLIGHT_ONE);

    private static final String VALID_DATA_OUTPUT = "This is the flight that matches your search\n"
            + TEST_FLIGHT_ONE + "\n\n\n"
            + "Passengers:\n\n"
            + "Total Passengers: 0";
    private static final String VALID_PASSENGER_OUTPUT = "This is the flight that matches your search\n"
            + TEST_FLIGHT_ONE + "\n\n\n"
            + "Passengers:\n"
            + "1. Bo Tuan (ID: c001)\n" + "\n"
            + "Total Passengers: 1";
    private static final String INVALID_DATA_OUTPUT = "ERROR: Flight cannot be found. Please try another flight ID.";

    PrintStream previousConsole = System.out;
    ByteArrayOutputStream newConsole = new ByteArrayOutputStream();

    ClientList dummyClientList = new ClientList();
    TourList dummyTourList = new TourList();
    FlightList testFlightList = new FlightList();
    ClientPackageList dummyPackageList = new ClientPackageList();
    Ui testUi = new Ui();

    @Test
    void findFlightCommand_validData_correctlyConstructed() throws TourPlannerException {

        System.setOut(new PrintStream(newConsole));

        testFlightList.add(TEST_FLIGHT_ONE);
        testFlightList.add(TEST_FLIGHT_TWO);
        Command findFlight = new FindFlightCommand("SQ-JPN");
        findFlight.setData(dummyClientList, testFlightList, dummyTourList, dummyPackageList, testUi);
        findFlight.execute();

        previousConsole.println(newConsole.toString());
        System.setOut(previousConsole);
        String actualString = newConsole.toString().trim().replace("\r\n", "\n");
        assertEquals(VALID_DATA_OUTPUT, actualString);
    }

    @Test
    void findFlightCommand_validPassenger_correctlyConstructed() throws TourPlannerException {
        System.setOut(new PrintStream(newConsole));

        dummyClientList.add(TEST_CLIENT);
        dummyTourList.add(TEST_TOUR);
        testFlightList.add(TEST_FLIGHT_ONE);
        dummyPackageList.add(TEST_CLIENTPACKAGE);

        Command findFlight = new FindFlightCommand("SQ-JPN");
        findFlight.setData(dummyClientList, testFlightList, dummyTourList, dummyPackageList, testUi);
        findFlight.execute();

        previousConsole.println(newConsole.toString());
        System.setOut(previousConsole);
        String actualString = newConsole.toString().trim().replace("\r\n", "\n");
        assertEquals(VALID_PASSENGER_OUTPUT, actualString);
    }

    @Test
    void findFlightCommand_invalidData_correctlyConstructed() throws TourPlannerException {
        System.setOut(new PrintStream(newConsole));

        testFlightList.add(TEST_FLIGHT_ONE);
        testFlightList.add(TEST_FLIGHT_TWO);
        Command findFlight = new FindFlightCommand("SQ-ZBW");
        findFlight.setData(dummyClientList, testFlightList, dummyTourList, dummyPackageList, testUi);
        findFlight.execute();

        previousConsole.println(newConsole.toString());
        System.setOut(previousConsole);
        String actualString = newConsole.toString().trim().replace("\r\n", "\n");
        assertEquals(INVALID_DATA_OUTPUT, actualString);
    }
}
