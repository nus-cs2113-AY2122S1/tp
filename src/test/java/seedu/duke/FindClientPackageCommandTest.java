package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.commands.Command;
import seedu.duke.commands.clientpackages.FindClientPackageCommand;
import seedu.duke.data.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindClientPackageCommandTest {

    PrintStream previousConsole = System.out;
    ByteArrayOutputStream newConsole = new ByteArrayOutputStream();

    ClientList dummyClientList = new ClientList();
    TourList dummyTourList = new TourList();
    FlightList dummyFlightList = new FlightList();
    ClientPackageList testPackageList = new ClientPackageList();
    Ui testUi = new Ui();

    @Test
    void findTourCommand_validSubscription_correctlyConstructed() throws TourPlannerException {
        System.setOut(new PrintStream(newConsole));

        Client botuan = new Client(new String[]{"c001", "Bo Tuan", "93338333", "bt@mail.com"});
        Tour jpn = new Tour(new String[]{"JPN", "Japan Basic Tour", "1500.00"});
        Flight sqjpn = new Flight(new String[]{"SQ-JPN", "JPN", "SG",
                "20/10/2021 18:00", "21/10/2021 03:00"});
        ClientPackage jpnPackage = new ClientPackage("p001", botuan, jpn, sqjpn);
        dummyClientList.add(botuan);
        dummyTourList.add(jpn);
        dummyFlightList.add(sqjpn);
        testPackageList.add(jpnPackage);

        Command findPackage = new FindClientPackageCommand(1);
        findPackage.setData(dummyClientList, dummyFlightList, dummyTourList, testPackageList, testUi);
        findPackage.execute();

        previousConsole.println(newConsole.toString());
        System.setOut(previousConsole);
        String expectedString = "This is the packages that matches your search\n" +
                "Package ID: p001\n" + "\n" +
                "Client: \n" +
                "Client ID: c001";

        String[] actualStringArray = newConsole.toString().trim().split("\r\n", 2);
        String actualString = actualStringArray[0] + "\n" + actualStringArray[1].trim().split("\r\n", 2)[0];
        assertEquals(expectedString, actualString);
    }
}
