package seedu.duke.commands.clientpackages;

import org.junit.jupiter.api.Test;
import seedu.duke.TourPlannerException;
import seedu.duke.Ui;
import seedu.duke.commands.Command;
import seedu.duke.data.Client;
import seedu.duke.data.Tour;
import seedu.duke.data.Flight;
import seedu.duke.data.ClientPackage;
import seedu.duke.data.ClientList;
import seedu.duke.data.TourList;
import seedu.duke.data.FlightList;
import seedu.duke.data.ClientPackageList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListClientPackageCommandTest {

    private static Client TEST_CLIENT = new Client(new String[]{"c001", "Bo Tuan", "93338333", "bt@mail.com"});
    private static Tour TEST_TOUR = new Tour(new String[]{"JPN", "Japan Basic Tour", "1500.00"});
    private static Flight TEST_FLIGHT = new Flight(new String[]{"SQ-JPN", "JPN", "SG", "20/10/2021 18:00",
        "21/10/2021 03:00"});
    private static ClientPackage TEST_CLIENTPACKAGE = new ClientPackage("p001",
            TEST_CLIENT, TEST_TOUR, TEST_FLIGHT);
    private static final String VALID_DATA_OUTPUT = "Here is a list of all packages:\n"
            + "1. " + TEST_CLIENTPACKAGE + "\n\n\n"
            + "Total Packages: 1";

    PrintStream previousConsole = System.out;
    ByteArrayOutputStream newConsole = new ByteArrayOutputStream();

    ClientList dummyClientList = new ClientList();
    TourList dummyTourList = new TourList();
    FlightList dummyFlightList = new FlightList();
    ClientPackageList testPackageList = new ClientPackageList();
    Ui testUi = new Ui();

    @Test
    void listClientCommand_validData_correctlyConstructed() throws TourPlannerException {
        System.setOut(new PrintStream(newConsole));

        dummyClientList.add(TEST_CLIENT);
        dummyTourList.add(TEST_TOUR);
        dummyFlightList.add(TEST_FLIGHT);
        testPackageList.add(TEST_CLIENTPACKAGE);

        Command listPackage = new ListClientPackageCommand();
        listPackage.setData(dummyClientList, dummyFlightList, dummyTourList, testPackageList, testUi);
        listPackage.execute();

        previousConsole.println(newConsole.toString());
        System.setOut(previousConsole);
        String actualString = newConsole.toString().trim().replace("\r\n", "\n");
        assertEquals(VALID_DATA_OUTPUT, actualString);
    }
}
