package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.commands.Command;
import seedu.duke.commands.clients.FindClientCommand;
import seedu.duke.data.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class FindClientCommandTest {

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

        Client botuan = new Client(new String[]{"c001", "Bo Tuan", "93338333", "bt@mail.com"});
        Client wayne = new Client(new String[]{"c002", "Wayne", "56667888", "wen@mail.com"});
        testClientList.add(botuan);
        testClientList.add(wayne);
        Command findClient = new FindClientCommand("Bo Tuan");
        findClient.setData(testClientList, dummyFlightList, dummyTourList, dummyPackageList, testUi);
        findClient.execute();

        previousConsole.println(newConsole.toString());
        System.setOut(previousConsole);
        String expectedString = "This is the client(s) that matches your search\n" +
                "1. Client ID: c001\n" +
                "Name: Bo Tuan\n" +
                "Contact Number: 93338333\n" +
                "Email: bt@mail.com";
        String actualString = newConsole.toString().trim().replace("\r\n", "\n");
        assertEquals(expectedString, actualString);
    }

    @Test
    void findClientCommand_TwoOrMoreSameName_correctlyConstructed() throws TourPlannerException {
        System.setOut(new PrintStream(newConsole));

        Client botuan = new Client(new String[]{"c001", "Bo Tuan", "93338333", "bt@mail.com"});
        Client bootuan = new Client(new String[]{"c002", "Bo Tuan", "56667888", "bbt@mail.com"});
        testClientList.add(botuan);
        testClientList.add(bootuan);
        Command findClient = new FindClientCommand("Bo Tuan");
        findClient.setData(testClientList, dummyFlightList, dummyTourList, dummyPackageList, testUi);
        findClient.execute();

        previousConsole.println(newConsole.toString());
        System.setOut(previousConsole);
        String expectedString = "This is the client(s) that matches your search\n" +
                "1. Client ID: c001\n" +
                "Name: Bo Tuan\n" +
                "Contact Number: 93338333\n" +
                "Email: bt@mail.com\n" + "\n" +
                "2. Client ID: c002\n" +
                "Name: Bo Tuan\n" +
                "Contact Number: 56667888\n" +
                "Email: bbt@mail.com";
        String actualString = newConsole.toString().trim().replace("\r\n", "\n");
        assertEquals(expectedString, actualString);
    }

    @Test
    void findClientCommand_invalidData_correctlyConstructed() throws TourPlannerException {
        System.setOut(new PrintStream(newConsole));

        Client botuan = new Client(new String[]{"c001", "Bo Tuan", "93338333", "bt@mail.com"});
        Client wayne = new Client(new String[]{"c002", "Wayne", "56667888", "wen@mail.com"});
        testClientList.add(botuan);
        testClientList.add(wayne);
        Command findClient = new FindClientCommand("Sem");
        findClient.setData(testClientList, dummyFlightList, dummyTourList, dummyPackageList, testUi);
        findClient.execute();

        previousConsole.println(newConsole.toString());
        System.setOut(previousConsole);
        String expectedString = "I'm sorry, there seems to be no client(s) that matches your search";
        String actualString = newConsole.toString().trim().replace("\r\n", "\n");
        assertEquals(expectedString, actualString);
    }
}
