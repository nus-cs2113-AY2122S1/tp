package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.commands.Command;
import seedu.duke.commands.flights.FindFlightCommand;
import seedu.duke.data.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class FindFlightCommandTest {

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

        Flight sqjpn = new Flight(new String[]{"SQ-JPN", "JPN", "SG",
                "20/10/2021 18:00", "21/10/2021 03:00"});
        Flight sqkor = new Flight(new String[]{"SQ-KOR", "KOR", "SG",
                "23/10/2021 18:00", "30/10/2021 03:00"});
        testFlightList.add(sqjpn);
        testFlightList.add(sqkor);
        Command findFlight = new FindFlightCommand("SQ-JPN");
        findFlight.setData(dummyClientList, testFlightList, dummyTourList, dummyPackageList, testUi);
        findFlight.execute();

        previousConsole.println(newConsole.toString());
        System.setOut(previousConsole);
        String expectedString = "This is the flights that matches your search\n" +
                "Flight ID: SQ-JPN\n" +
                "Departure Flight: JPN, 20/10/2021 18:00\n" +
                "Return Flight: SG, 21/10/2021 03:00\n" + "\n" + "\n" +
                "Passengers:\n" +
                "Total Passengers: 0";
        String actualString = newConsole.toString().trim().replace("\r\n", "\n");
        assertEquals(expectedString, actualString);
    }

    @Test
    void findFlightCommand_validPassenger_correctlyConstructed() throws TourPlannerException {
        System.setOut(new PrintStream(newConsole));

        Client botuan = new Client(new String[]{"c001", "Bo Tuan", "93338333", "bt@mail.com"});
        Tour jpn = new Tour(new String[]{"JPN", "Japan Basic Tour", "1500.00"});
        Flight sqjpn = new Flight(new String[]{"SQ-JPN", "JPN", "SG",
                "20/10/2021 18:00", "21/10/2021 03:00"});
        ClientPackage jpnPackage = new ClientPackage("p001", botuan, jpn, sqjpn);
        dummyClientList.add(botuan);
        dummyTourList.add(jpn);
        testFlightList.add(sqjpn);
        dummyPackageList.add(jpnPackage);

        Command findFlight = new FindFlightCommand("SG-JPN");
        findFlight.setData(dummyClientList, testFlightList, dummyTourList, dummyPackageList, testUi);
        findFlight.execute();

        previousConsole.println(newConsole.toString());
        System.setOut(previousConsole);
        String expectedString = "This is the flights that matches your search\n" +
                "Flight ID: SQ-JPN\n" +
                "Departure Flight: JPN, 20/10/2021 18:00\n" +
                "Return Flight: SG, 21/10/2021 03:00\n" + "\n" + "\n" +
                "Passengers:\n" +
                "1. Bo Tuan\n" + "\n" +
                "Total Passengers: 1";
        String actualString = newConsole.toString().trim().replace("\r\n", "\n");
        assertEquals(expectedString, actualString);
    }

    @Test
    void findFlightCommand_invalidData_correctlyConstructed() throws TourPlannerException {
        System.setOut(new PrintStream(newConsole));

        Flight sqjpn = new Flight(new String[]{"SQ-JPN", "JPN", "SG",
                "20/10/2021 18:00", "21/10/2021 03:00"});
        Flight sqkor = new Flight(new String[]{"SQ-KOR", "KOR", "SG",
                "23/10/2021 18:00", "30/10/2021 03:00"});
        testFlightList.add(sqjpn);
        testFlightList.add(sqkor);
        Command findFlight = new FindFlightCommand("SQ-ZBW");
        findFlight.setData(dummyClientList, testFlightList, dummyTourList, dummyPackageList, testUi);
        findFlight.execute();

        previousConsole.println(newConsole.toString());
        System.setOut(previousConsole);
        String expectedString = "Flight ID cannot be found. Please try another Flight ID.";
        String actualString = newConsole.toString().trim().replace("\r\n", "\n");
        assertEquals(expectedString, actualString);
    }
}
