package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.commands.Command;
import seedu.duke.commands.clients.ListClientCommand;
import seedu.duke.data.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListClientCommandTest {

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

        Client botuan = new Client(new String[]{"c001", "Bo Tuan", "93338333", "bt@mail.com"});
        Client wayne = new Client(new String[]{"c002", "Wayne", "56667888", "wen@mail.com"});
        testClientList.add(botuan);
        testClientList.add(wayne);
        Command listClient = new ListClientCommand();
        listClient.setData(testClientList, dummyFlightList, dummyTourList, dummyPackageList, testUi);
        listClient.execute();

        previousConsole.println(newConsole.toString());
        System.setOut(previousConsole);
        String expectedString = "Here is a list of all clients:\n"
                + "1. Client ID: c001\n"
                + "Name: Bo Tuan\n"
                + "Contact Number: 93338333\n"
                + "Email: bt@mail.com\n" + "\n"
                + "2. Client ID: c002\n"
                + "Name: Wayne\n"
                + "Contact Number: 56667888\n"
                + "Email: wen@mail.com\n" + "\n"
                + "Total Clients: 2";
        String actualString = newConsole.toString().trim().replace("\r\n", "\n");
        assertEquals(expectedString, actualString);
    }

    @Test
    void listClientCommand_noData_correctlyConstructed() throws TourPlannerException {
        System.setOut(new PrintStream(newConsole));

        Command listClient = new ListClientCommand();
        listClient.setData(testClientList, dummyFlightList, dummyTourList, dummyPackageList, testUi);
        listClient.execute();

        previousConsole.println(newConsole.toString());
        System.setOut(previousConsole);
        String expectedString = "I'm sorry, there seems to be no clients";
        String actualString = newConsole.toString().trim().replace("\r\n", "\n");
        assertEquals(expectedString, actualString);
    }
}
