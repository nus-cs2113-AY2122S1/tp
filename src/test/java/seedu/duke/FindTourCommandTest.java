package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.commands.Command;
import seedu.duke.commands.tours.FindTourCommand;
import seedu.duke.data.Client;
import seedu.duke.data.ClientList;
import seedu.duke.data.ClientPackage;
import seedu.duke.data.ClientPackageList;
import seedu.duke.data.Flight;
import seedu.duke.data.FlightList;
import seedu.duke.data.Tour;
import seedu.duke.data.TourList;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class FindTourCommandTest {

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

        Tour jpn = new Tour(new String[]{"JPN", "Japan Basic Tour", "1500.00"});
        Tour kor = new Tour(new String[]{"KOR", "Korea Cultural Tour", "3000.00"});
        testTourList.add(jpn);
        testTourList.add(kor);
        Command findTour = new FindTourCommand("JPN");
        findTour.setData(dummyClientList, dummyFlightList, testTourList, dummyPackageList, testUi);
        findTour.execute();

        previousConsole.println(newConsole.toString());
        System.setOut(previousConsole);
        String expectedString = "This is the tours that matches your search\n"
                + "Name: Japan Basic Tour\n"
                + "Code: JPN\n"
                + "Price per pax: $1500.00\n" + "\n" + "\n"
                + "Subscribed Clients:\n"
                + "Total Subscribed Clients: 0";
        String actualString = newConsole.toString().trim().replace("\r\n", "\n");
        assertEquals(expectedString, actualString);
    }

    @Test
    void findTourCommand_validSubscription_correctlyConstructed() throws TourPlannerException {
        System.setOut(new PrintStream(newConsole));

        Client botuan = new Client(new String[]{"c001", "Bo Tuan", "93338333", "bt@mail.com"});
        dummyClientList.add(botuan);
        Tour jpn = new Tour(new String[]{"JPN", "Japan Basic Tour", "1500.00"});
        testTourList.add(jpn);
        Flight sqjpn = new Flight(new String[]{"SQ-JPN", "JPN", "SG", "20/10/2021 18:00", "21/10/2021 03:00"});
        dummyFlightList.add(sqjpn);
        ClientPackage jpnPackage = new ClientPackage("p001", botuan, jpn, sqjpn);
        dummyPackageList.add(jpnPackage);

        Command findTour = new FindTourCommand("JPN");
        findTour.setData(dummyClientList, dummyFlightList, testTourList, dummyPackageList, testUi);
        findTour.execute();

        previousConsole.println(newConsole.toString());
        System.setOut(previousConsole);
        String expectedString = "This is the tours that matches your search\n"
                + "Name: Japan Basic Tour\n"
                + "Code: JPN\n"
                + "Price per pax: $1500.00\n" + "\n" + "\n"
                + "Subscribed Clients:\n"
                + "1. Bo Tuan\n" + "\n"
                + "Total Subscribed Clients: 1";
        String actualString = newConsole.toString().trim().replace("\r\n", "\n");
        assertEquals(expectedString, actualString);
    }

    @Test
    void findTourCommand_invalidData_correctlyConstructed() throws TourPlannerException {
        System.setOut(new PrintStream(newConsole));

        Tour jpn = new Tour(new String[]{"JPN", "Japan Basic Tour", "1500.00"});
        Tour kor = new Tour(new String[]{"KOR", "Korea Cultural Tour", "3000.00"});
        testTourList.add(jpn);
        testTourList.add(kor);
        Command findTour = new FindTourCommand("SGP");
        findTour.setData(dummyClientList, dummyFlightList, testTourList, dummyPackageList, testUi);
        findTour.execute();

        previousConsole.println(newConsole.toString());
        System.setOut(previousConsole);
        String expectedString = "Tour code cannot be found. Please try another Tour code.";
        String actualString = newConsole.toString().trim().replace("\r\n", "\n");
        assertEquals(expectedString, actualString);
    }

}
