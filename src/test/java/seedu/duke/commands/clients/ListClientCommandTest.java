package seedu.duke.commands.clients;

import org.junit.jupiter.api.Test;
import seedu.duke.TourPlannerException;
import seedu.duke.Ui;
import seedu.duke.commands.Command;
import seedu.duke.data.Client;
import seedu.duke.data.ClientList;
import seedu.duke.data.TourList;
import seedu.duke.data.FlightList;
import seedu.duke.data.ClientPackageList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListClientCommandTest {

    private static Client TEST_CLIENT_ONE = new Client(new String[]{"c001", "Bo Tuan", "93338333", "bt@mail.com"});
    private static Client TEST_CLIENT_TWO = new Client(new String[]{"c002", "Wayne", "56667888", "wen@mail.com"});
    private static final String VALID_DATA_OUTPUT = "Here is a list of all clients:\n"
            + "1. " + TEST_CLIENT_ONE + "\n\n"
            + "2. " + TEST_CLIENT_TWO + "\n\n"
            + "Total Clients: 2";
    private static final String NO_DATA_OUTPUT = "I'm sorry, there seems to be no clients";

    PrintStream previousConsole = System.out;
    ByteArrayOutputStream newConsole = new ByteArrayOutputStream();

    ClientList testClientList = new ClientList();
    TourList dummyTourList = new TourList();
    FlightList dummyFlightList = new FlightList();
    ClientPackageList dummyPackageList = new ClientPackageList();
    Ui testUi = new Ui();

    @Test
    void listClientCommand_validData_correctlyConstructed() throws TourPlannerException {
        System.setOut(new PrintStream(newConsole));

        testClientList.add(TEST_CLIENT_ONE);
        testClientList.add(TEST_CLIENT_TWO);
        Command listClient = new ListClientCommand();
        listClient.setData(testClientList, dummyFlightList, dummyTourList, dummyPackageList, testUi);
        listClient.execute();

        previousConsole.println(newConsole.toString());
        System.setOut(previousConsole);
        String actualString = newConsole.toString().trim().replace("\r\n", "\n");
        assertEquals(VALID_DATA_OUTPUT, actualString);
    }

    @Test
    void listClientCommand_noData_correctlyConstructed() throws TourPlannerException {
        System.setOut(new PrintStream(newConsole));

        Command listClient = new ListClientCommand();
        listClient.setData(testClientList, dummyFlightList, dummyTourList, dummyPackageList, testUi);
        listClient.execute();

        previousConsole.println(newConsole.toString());
        System.setOut(previousConsole);
        String actualString = newConsole.toString().trim().replace("\r\n", "\n");
        assertEquals(NO_DATA_OUTPUT, actualString);
    }
}
