package seedu.duke.commands.flights;

import org.junit.jupiter.api.Test;
import seedu.duke.TourPlannerException;
import seedu.duke.Ui;
import seedu.duke.commands.Command;
import seedu.duke.data.ClientList;
import seedu.duke.data.ClientPackageList;
import seedu.duke.data.Flight;
import seedu.duke.data.FlightList;
import seedu.duke.data.TourList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SortFlightCommandTest {

    private static final String VALID_SORT_BY_DEPART_FILTER = "/d";
    private static final String VALID_SORT_BY_ARRIVE_FILTER = "/r";
    public static final String VALID_SORT_BY_ID_FILTER = "/id";
    private static final String INVALID_FILTER = "/rubbish";

    PrintStream previousConsole = System.out;
    ByteArrayOutputStream newConsole = new ByteArrayOutputStream();

    TourList dummyTourList = new TourList();
    ClientList dummyClientList = new ClientList();
    FlightList testFlightList = new FlightList();
    ClientPackageList dummyPackageList = new ClientPackageList();
    Ui testUi = new Ui();

    void initialiseFlightListForTesting() {
        Flight sqjpn = new Flight(new String[]{"SQ-JPN", "JPN", "SG", "20/10/21 18:00", "24/10/21 03:00"});
        Flight msiaaus = new Flight(new String[]{"MSIA-AUS", "AUS", "MSIA", "2/11/21 23:00", "9/11/21 11:00"});
        Flight sqkor = new Flight(new String[]{"SQ-KOR", "KOR", "SG", "26/10/21 14:00", "28/10/21 05:00"});
        testFlightList.add(sqjpn);
        testFlightList.add(msiaaus);
        testFlightList.add(sqkor);
    }

    @Test
    void sortFlightCommand_validFilterByDepartureDate_correctlySortedTime() throws TourPlannerException {
        System.setOut(new PrintStream(newConsole));
        initialiseFlightListForTesting();

        Command testSortFlightCommand = new SortFlightCommand(VALID_SORT_BY_DEPART_FILTER);
        testSortFlightCommand.setData(dummyClientList, testFlightList, dummyTourList, dummyPackageList, testUi);
        testSortFlightCommand.execute();

        previousConsole.println(newConsole.toString());
        System.setOut(previousConsole);

        testFlightList.initTempArray();
        String expectedString = Ui.SORT_FLIGHT_BY_DEPARTURE_MESSAGE + "\n"
                + "1. " + testFlightList.getFlightByDepartDate("20/10/21 18:00") + "\n\n"
                + "2. " + testFlightList.getFlightByDepartDate("26/10/21 14:00") + "\n\n"
                + "3. " + testFlightList.getFlightByDepartDate("2/11/21 23:00");

        String actualString = newConsole.toString().trim().replace("\r\n", "\n");
        assertEquals(expectedString, actualString);
    }

    @Test
    void sortFlightCommand_validFilterByArriveDate_correctlySortedTime() throws TourPlannerException {
        System.setOut(new PrintStream(newConsole));
        initialiseFlightListForTesting();

        Command testSortFlightCommand = new SortFlightCommand(VALID_SORT_BY_ARRIVE_FILTER);
        testSortFlightCommand.setData(dummyClientList, testFlightList, dummyTourList, dummyPackageList, testUi);
        testSortFlightCommand.execute();

        previousConsole.println(newConsole.toString());
        System.setOut(previousConsole);

        testFlightList.initTempArray();
        String expectedString = Ui.SORT_FLIGHT_BY_ARRIVAL_MESSAGE + "\n"
                + "1. " + testFlightList.getFlightByArriveDate("24/10/21 03:00") + "\n\n"
                + "2. " + testFlightList.getFlightByArriveDate("28/10/21 05:00") + "\n\n"
                + "3. " + testFlightList.getFlightByArriveDate("9/11/21 11:00");

        String actualString = newConsole.toString().trim().replace("\r\n", "\n");
        assertEquals(expectedString, actualString);
    }

    @Test
    void sortFlightCommand_validFilterById_correctlySortedIdAlphabetically() throws TourPlannerException {
        System.setOut(new PrintStream(newConsole));
        initialiseFlightListForTesting();

        Command testSortFlightCommand = new SortFlightCommand(VALID_SORT_BY_ID_FILTER);
        testSortFlightCommand.setData(dummyClientList, testFlightList, dummyTourList, dummyPackageList, testUi);
        testSortFlightCommand.execute();

        previousConsole.println(newConsole.toString());
        System.setOut(previousConsole);

        testFlightList.initTempArray();
        String expectedString = Ui.SORT_FLIGHT_ID_MESSAGE + "\n"
                + "1. " + testFlightList.getFlightById("MSIA-AUS") + "\n\n"
                + "2. " + testFlightList.getFlightById("SQ-JPN") + "\n\n"
                + "3. " + testFlightList.getFlightById("SQ-KOR");

        String actualString = newConsole.toString().trim().replace("\r\n", "\n");
        assertEquals(expectedString, actualString);
    }

    @Test
    void sortTourCommand_invalidFilter_throwException() throws TourPlannerException {
        System.setOut(new PrintStream(newConsole));
        initialiseFlightListForTesting();

        Command testSortFlightCommand = new SortFlightCommand(INVALID_FILTER);
        testSortFlightCommand.setData(dummyClientList, testFlightList, dummyTourList, dummyPackageList, testUi);
        testSortFlightCommand.execute();

        previousConsole.println(newConsole.toString());
        System.setOut(previousConsole);

        testFlightList.initTempArray();
        String expectedString = SortFlightCommand.ERROR_MISSING_FILTER;
        String actualString = newConsole.toString().trim().replace("\r\n", "\n");
        assertEquals(expectedString, actualString);
    }
}