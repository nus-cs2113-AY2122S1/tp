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
    public static final String PRICE_ORDER_ONE = "1500.00";
    public static final String PRICE_ORDER_THREE = "3000.00";
    public static final String PRICE_ORDER_TWO = "1800.00";
    public static final String ID_ORDER_TWO = "JPN";
    public static final String ID_ORDER_THREE = "KOR";
    public static final String ID_ORDER_ONE = "AUS";

    PrintStream previousConsole = System.out;
    ByteArrayOutputStream newConsole = new ByteArrayOutputStream();

    TourList testTourList = new TourList();
    ClientList dummyClientList = new ClientList();
    FlightList dummyFlightList = new FlightList();
    ClientPackageList dummyPackageList = new ClientPackageList();
    Ui testUi = new Ui();

    void initialiseTourListForTesting() {
        Tour jpn = new Tour(new String[]{ID_ORDER_TWO, "Japan Basic Tour", PRICE_ORDER_ONE});
        Tour kor = new Tour(new String[]{ID_ORDER_THREE, "Korea Cultural Tour", PRICE_ORDER_THREE});
        Tour aus = new Tour(new String[]{ID_ORDER_ONE, "Australia Romantic Tour", PRICE_ORDER_TWO});
        testTourList.add(jpn);
        testTourList.add(kor);
        testTourList.add(aus);
    }

    Float parseFloat(String value) {
        return Float.parseFloat(value);
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
                + "1. " + testTourList.getTourByPrice(parseFloat(PRICE_ORDER_ONE)) + "\n\n"
                + "2. " + testTourList.getTourByPrice(parseFloat(PRICE_ORDER_TWO)) + "\n\n"
                + "3. " + testTourList.getTourByPrice(parseFloat(PRICE_ORDER_THREE));

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
                + "1. " + testTourList.getTourById(ID_ORDER_ONE) + "\n\n"
                + "2. " + testTourList.getTourById(ID_ORDER_TWO) + "\n\n"
                + "3. " + testTourList.getTourById(ID_ORDER_THREE);

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