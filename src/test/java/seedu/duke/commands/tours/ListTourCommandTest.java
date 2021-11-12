package seedu.duke.commands.tours;

import org.junit.jupiter.api.Test;
import seedu.duke.TourPlannerException;
import seedu.duke.Ui;
import seedu.duke.commands.Command;
import seedu.duke.data.ClientList;
import seedu.duke.data.ClientPackageList;
import seedu.duke.data.FlightList;
import seedu.duke.data.Tour;
import seedu.duke.data.TourList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListTourCommandTest {

    private static Tour TEST_TOUR_ONE = new Tour(new String[]{"JPN", "Japan Basic Tour", "1500.00"});
    private static Tour TEST_TOUR_TWO = new Tour(new String[]{"KOR", "Korea Cultural Tour", "3000.00"});
    private static final String VALID_DATA_OUTPUT = "Here is a list of all tours:\n"
            + "1. " + TEST_TOUR_ONE + "\n\n"
            + "2. " + TEST_TOUR_TWO + "\n\n"
            + "Total Tours: 2";
    private static final String NO_DATA_OUTPUT = "I'm sorry, there seems to be no tours";

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

        testTourList.add(TEST_TOUR_ONE);
        testTourList.add(TEST_TOUR_TWO);
        Command listTour = new ListTourCommand();
        listTour.setData(dummyClientList, dummyFlightList, testTourList, dummyPackageList, testUi);
        listTour.execute();

        previousConsole.println(newConsole.toString());
        System.setOut(previousConsole);
        String actualString = newConsole.toString().trim().replace("\r\n", "\n");
        assertEquals(VALID_DATA_OUTPUT, actualString);
    }

    @Test
    void listTourCommand_noData_correctlyConstructed() throws TourPlannerException {
        System.setOut(new PrintStream(newConsole));

        Command listTour = new ListTourCommand();
        listTour.setData(dummyClientList, dummyFlightList, testTourList, dummyPackageList, testUi);
        listTour.execute();

        previousConsole.println(newConsole.toString());
        System.setOut(previousConsole);
        String actualString = newConsole.toString().trim().replace("\r\n", "\n");
        assertEquals(NO_DATA_OUTPUT, actualString);
    }
}
