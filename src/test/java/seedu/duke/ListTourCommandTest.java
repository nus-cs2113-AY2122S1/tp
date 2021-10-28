package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.commands.Command;
import seedu.duke.commands.tours.ListTourCommand;
import seedu.duke.data.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListTourCommandTest {

    PrintStream previousConsole = System.out;
    ByteArrayOutputStream newConsole = new ByteArrayOutputStream();

    ClientList dummyClientList = new ClientList();
    TourList testTourList = new TourList();
    FlightList dummyFlightList = new FlightList();
    ClientPackageList dummyPackageList = new ClientPackageList();
    Ui testUi = new Ui();

    @Test
    void listClientCommand_validData_correctlyConstructed() throws TourPlannerException {
        System.setOut(new PrintStream(newConsole));

        Tour jpn = new Tour(new String[]{"JPN", "Japan Basic Tour", "1500.00"});
        Tour kor = new Tour(new String[]{"KOR", "Korea Cultural Tour", "3000.00"});
        testTourList.add(jpn);
        testTourList.add(kor);
        Command listTour = new ListTourCommand();
        listTour.setData(dummyClientList, dummyFlightList, testTourList, dummyPackageList, testUi);
        listTour.execute();

        previousConsole.println(newConsole.toString());
        System.setOut(previousConsole);
        String expectedString = "Here is a list of all tours:\n"
                + "1. Name: Japan Basic Tour\n"
                + "Code: JPN\n"
                + "Price per pax: $1500.00\n" + "\n"
                + "2. Name: Korea Cultural Tour\n"
                + "Code: KOR\n"
                + "Price per pax: $3000.00\n" + "\n"
                + "Total Tours: 2";
        String actualString = newConsole.toString().trim().replace("\r\n", "\n");
        assertEquals(expectedString, actualString);
    }

    @Test
    void listTourCommand_noData_correctlyConstructed() throws TourPlannerException {
        System.setOut(new PrintStream(newConsole));

        Command listTour = new ListTourCommand();
        listTour.setData(dummyClientList, dummyFlightList, testTourList, dummyPackageList, testUi);
        listTour.execute();

        previousConsole.println(newConsole.toString());
        System.setOut(previousConsole);
        String expectedString = "I'm sorry, there seems to be no tours";
        String actualString = newConsole.toString().trim().replace("\r\n", "\n");
        assertEquals(expectedString, actualString);
    }
}
