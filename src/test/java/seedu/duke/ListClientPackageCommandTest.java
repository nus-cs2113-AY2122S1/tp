package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.commands.Command;
import seedu.duke.commands.clientpackages.ListClientPackageCommand;
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

        Client botuan = new Client(new String[]{"c001", "Bo Tuan", "93338333", "bt@mail.com"});
        Tour jpn = new Tour(new String[]{"JPN", "Japan Basic Tour", "1500.00"});
        Flight sqjpn = new Flight(new String[]{"SQ-JPN", "JPN", "SG",
                "20/10/2021 18:00", "21/10/2021 03:00"});
        dummyClientList.add(botuan);
        dummyTourList.add(jpn);
        dummyFlightList.add(sqjpn);
        ClientPackage jpnPackage = new ClientPackage("p001", botuan, jpn, sqjpn);
        testPackageList.add(jpnPackage);

        Command listPackage = new ListClientPackageCommand();
        listPackage.setData(dummyClientList, dummyFlightList, dummyTourList, testPackageList, testUi);
        listPackage.execute();

        previousConsole.println(newConsole.toString());
        System.setOut(previousConsole);
        String expectedString = "Here is a list of all packages\n"
                + "Package ID: p001\n" + "\n"
                + "Client: \n"
                + "Client ID: c001";
        String actualString = newConsole.toString().trim().replace("\r\n", "\n");
        assertEquals(expectedString, actualString);
    }
}
