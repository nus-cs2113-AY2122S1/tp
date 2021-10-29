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

class SortTourCommandTest {

    public static final String VALID_SORT_BY_PRICE_FILTER = "/p";
    private static final String VALID_SORT_BY_ID_FILTER = "/id";
    private static final String INVALID_FILTER = "/i";
    private static final String EMPTY_FILTER = "";

    PrintStream previousConsole = System.out;
    ByteArrayOutputStream newConsole = new ByteArrayOutputStream();

    TourList testTourList = new TourList();
    ClientList dummyClientList = new ClientList();
    FlightList dummyFlightList = new FlightList();
    ClientPackageList dummyPackageList = new ClientPackageList();
    Ui testUi = new Ui();

    void initialiseTourListForTesting() {
        Tour jpn = new Tour(new String[]{"JPN", "Japan Basic Tour", "1500.00"});
        Tour kor = new Tour(new String[]{"KOR", "Korea Cultural Tour", "3000.00"});
        Tour aus = new Tour(new String[]{"AUS", "Australia Romantic Tour", "1800.00"});
        testTourList.add(jpn);
        testTourList.add(kor);
        testTourList.add(aus);
    }

    @Test
    void sortTourCommand_validFilterByPrice_correctlySortedPriceAscending() throws TourPlannerException {
        System.setOut(new PrintStream(newConsole));
        initialiseTourListForTesting();

        Command testSortTourCommand = new SortTourCommand(VALID_SORT_BY_PRICE_FILTER);
        testSortTourCommand.setData(dummyClientList, dummyFlightList, testTourList, dummyPackageList, testUi);
        testSortTourCommand.execute();

        previousConsole.println(newConsole.toString());
        System.setOut(previousConsole);

        testTourList.initTempArray();
        String expectedString = Ui.SORT_TOUR_PRICE_MESSAGE + "\n"
                + "1. " + testTourList.getTourByPrice(1500.00F) + "\n\n"
                + "2. " + testTourList.getTourByPrice(1800.00F) + "\n\n"
                + "3. " + testTourList.getTourByPrice(3000.00F);

        String actualString = newConsole.toString().trim().replace("\r\n", "\n");
        assertEquals(expectedString, actualString);
    }

    @Test
    void sortTourCommand_validFilterById_correctlySortedIdAscending() throws TourPlannerException {
        System.setOut(new PrintStream(newConsole));
        initialiseTourListForTesting();

        Command testSortTourCommand = new SortTourCommand(VALID_SORT_BY_ID_FILTER);
        testSortTourCommand.setData(dummyClientList, dummyFlightList, testTourList, dummyPackageList, testUi);
        testSortTourCommand.execute();

        previousConsole.println(newConsole.toString());
        System.setOut(previousConsole);

        testTourList.initTempArray();
        String expectedString = Ui.SORT_TOUR_ID_MESSAGE + "\n"
                + "1. " + testTourList.getTourById("AUS") + "\n\n"
                + "2. " + testTourList.getTourById("JPN") + "\n\n"
                + "3. " + testTourList.getTourById("KOR");

        String actualString = newConsole.toString().trim().replace("\r\n", "\n");
        assertEquals(expectedString, actualString);
    }

    @Test
    void sortTourCommand_invalidFilter_throwException() throws TourPlannerException {
        System.setOut(new PrintStream(newConsole));
        initialiseTourListForTesting();

        Command testSortTourCommand = new SortTourCommand(INVALID_FILTER);
        testSortTourCommand.setData(dummyClientList, dummyFlightList, testTourList, dummyPackageList, testUi);
        testSortTourCommand.execute();

        previousConsole.println(newConsole.toString());
        System.setOut(previousConsole);

        testTourList.initTempArray();
        String expectedString = SortTourCommand.ERROR_MISSING_FILTER;
        String actualString = newConsole.toString().trim().replace("\r\n", "\n");
        assertEquals(expectedString, actualString);
    }

    @Test
    void sortTourCommand_emptyFilter_throwException() throws TourPlannerException {
        System.setOut(new PrintStream(newConsole));
        initialiseTourListForTesting();

        Command testSortTourCommand = new SortTourCommand(EMPTY_FILTER);
        testSortTourCommand.setData(dummyClientList, dummyFlightList, testTourList, dummyPackageList, testUi);
        testSortTourCommand.execute();

        previousConsole.println(newConsole.toString());
        System.setOut(previousConsole);

        testTourList.initTempArray();
        String expectedString = SortTourCommand.ERROR_MISSING_FILTER;
        String actualString = newConsole.toString().trim().replace("\r\n", "\n");
        assertEquals(expectedString, actualString);
    }

}