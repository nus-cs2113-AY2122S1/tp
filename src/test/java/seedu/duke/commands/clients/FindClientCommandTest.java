package seedu.duke.commands.clients;

import org.junit.jupiter.api.Test;
import seedu.duke.TourPlannerException;
import seedu.duke.Ui;
import seedu.duke.commands.Command;
import seedu.duke.data.Client;
import seedu.duke.data.ClientList;
import seedu.duke.data.ClientPackageList;
import seedu.duke.data.FlightList;
import seedu.duke.data.TourList;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class FindClientCommandTest {

    private static Client TEST_CLIENT_ONE = new Client(new String[]{"c001", "Bo Tuan", "93338333", "bt@mail.com"});
    private static Client TEST_CLIENT_TWO = new Client(new String[]{"c002", "Wayne", "56667888", "wen@mail.com"});
    private static Client TEST_CLIENT_THREE = new Client(new String[]{"c002", "Bo Tuan", "56667888", "bbt@mail.com"});

    private static final String VALID_DATA_OUTPUT = "This is the client(s) that matches your search\n"
            + "1. " + TEST_CLIENT_ONE;
    private static final String TWO_OR_MORE_OUTPUT = "This is the client(s) that matches your search\n"
            + "1. " + TEST_CLIENT_ONE + "\n\n"
            + "2. " + TEST_CLIENT_THREE;
    private static final String INVALID_DATA_OUTPUT = "I'm sorry, "
            + "there seems to be no client(s) that matches your search";

    PrintStream previousConsole = System.out;
    ByteArrayOutputStream newConsole = new ByteArrayOutputStream();

    ClientList testClientList = new ClientList();
    TourList dummyTourList = new TourList();
    FlightList dummyFlightList = new FlightList();
    ClientPackageList dummyPackageList = new ClientPackageList();
    Ui testUi = new Ui();

    @Test
    void findClientCommand_validData_correctlyConstructed() throws TourPlannerException {
        System.setOut(new PrintStream(newConsole));

        testClientList.add(TEST_CLIENT_ONE);
        testClientList.add(TEST_CLIENT_TWO);
        Command findClient = new FindClientCommand("Bo Tuan");
        findClient.setData(testClientList, dummyFlightList, dummyTourList, dummyPackageList, testUi);
        findClient.execute();

        previousConsole.println(newConsole.toString());
        System.setOut(previousConsole);
        String actualString = newConsole.toString().trim().replace("\r\n", "\n");
        assertEquals(VALID_DATA_OUTPUT, actualString);
    }

    @Test
    void findClientCommand_TwoOrMoreSameName_correctlyConstructed() throws TourPlannerException {
        System.setOut(new PrintStream(newConsole));

        testClientList.add(TEST_CLIENT_ONE);
        testClientList.add(TEST_CLIENT_THREE);
        Command findClient = new FindClientCommand("Bo Tuan");
        findClient.setData(testClientList, dummyFlightList, dummyTourList, dummyPackageList, testUi);
        findClient.execute();

        previousConsole.println(newConsole.toString());
        System.setOut(previousConsole);
        String actualString = newConsole.toString().trim().replace("\r\n", "\n");
        assertEquals(TWO_OR_MORE_OUTPUT, actualString);
    }

    @Test
    void findClientCommand_invalidData_correctlyConstructed() throws TourPlannerException {
        System.setOut(new PrintStream(newConsole));

        testClientList.add(TEST_CLIENT_ONE);
        testClientList.add(TEST_CLIENT_TWO);
        Command findClient = new FindClientCommand("Sem");
        findClient.setData(testClientList, dummyFlightList, dummyTourList, dummyPackageList, testUi);
        findClient.execute();

        previousConsole.println(newConsole.toString());
        System.setOut(previousConsole);
        String actualString = newConsole.toString().trim().replace("\r\n", "\n");
        assertEquals(INVALID_DATA_OUTPUT, actualString);
    }
}
