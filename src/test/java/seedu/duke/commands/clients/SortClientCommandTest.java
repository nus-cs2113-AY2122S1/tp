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

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SortClientCommandTest {

    public static final String VALID_SORT_BY_NAME_FILTER = "/n";
    private static final String VALID_SORT_BY_ID_FILTER = "/id";
    public static final String INVALID_FILTER = "/t";

    PrintStream previousConsole = System.out;
    ByteArrayOutputStream newConsole = new ByteArrayOutputStream();

    ClientList testClientList = new ClientList();
    TourList dummyTourList = new TourList();
    FlightList dummyFlightList = new FlightList();
    ClientPackageList dummyPackageList = new ClientPackageList();
    Ui testUi = new Ui();

    void initialiseClientListForTesting() {
        Client botuan = new Client(new String[]{"c001", "Bo Tuan", "93338333", "bt@mail.com"});
        Client wayne = new Client(new String[]{"c002", "Wayne", "56667888", "wen@mail.com"});
        testClientList.add(botuan);
        testClientList.add(wayne);
    }

    @Test
    void sortClientCommand_validFilterByName_correctlySortedNameAlphabetically() throws TourPlannerException {
        System.setOut(new PrintStream(newConsole));
        initialiseClientListForTesting();

        Command testSortClientCommand = new SortClientCommand(VALID_SORT_BY_NAME_FILTER);
        testSortClientCommand.setData(testClientList, dummyFlightList, dummyTourList, dummyPackageList, testUi);
        testSortClientCommand.execute();

        previousConsole.println(newConsole.toString());
        System.setOut(previousConsole);

        testClientList.initTempArray();
        String expectedString = Ui.SORT_CLIENT_NAME_MESSAGE + "\n"
                + "1. " + testClientList.getClientByName("Bo Tuan") + "\n\n"
                + "2. " + testClientList.getClientByName("Wayne");

        String actualString = newConsole.toString().trim().replace("\r\n", "\n");
        assertEquals(expectedString, actualString);
    }

    @Test
    void sortClientCommand_validFilterById_correctlySortedIdAlphabetically() throws TourPlannerException {
        System.setOut(new PrintStream(newConsole));
        initialiseClientListForTesting();

        Command testSortClientCommand = new SortClientCommand(VALID_SORT_BY_ID_FILTER);
        testSortClientCommand.setData(testClientList, dummyFlightList, dummyTourList, dummyPackageList, testUi);
        testSortClientCommand.execute();

        previousConsole.println(newConsole.toString());
        System.setOut(previousConsole);

        testClientList.initTempArray();
        String expectedString = Ui.SORT_CLIENT_ID_MESSAGE + "\n"
                + "1. " + testClientList.getClientById("c001") + "\n\n"
                + "2. " + testClientList.getClientById("c002");

        String actualString = newConsole.toString().trim().replace("\r\n", "\n");
        assertEquals(expectedString, actualString);
    }

    @Test
    void sortClientCommand_invalidFilter_throwException() throws TourPlannerException {
        System.setOut(new PrintStream(newConsole));
        initialiseClientListForTesting();

        Command testSortClientCommand = new SortClientCommand(INVALID_FILTER);
        testSortClientCommand.setData(testClientList, dummyFlightList, dummyTourList, dummyPackageList, testUi);
        testSortClientCommand.execute();

        previousConsole.println(newConsole.toString());
        System.setOut(previousConsole);

        testClientList.initTempArray();
        String expectedString = SortClientCommand.ERROR_MISSING_FILTER;
        String actualString = newConsole.toString().trim().replace("\r\n", "\n");
        assertEquals(expectedString, actualString);
    }
}