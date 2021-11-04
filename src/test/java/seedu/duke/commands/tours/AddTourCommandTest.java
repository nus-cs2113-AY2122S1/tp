package seedu.duke.commands.tours;

import org.junit.jupiter.api.Test;
import seedu.duke.TourPlannerException;
import seedu.duke.Ui;
import seedu.duke.data.ClientList;
import seedu.duke.data.ClientPackageList;
import seedu.duke.data.FlightList;
import seedu.duke.data.Tour;
import seedu.duke.data.TourList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddTourCommandTest {

    public static final String TEST_TOUR_ID = "t001";
    public static final String TEST_TOUR_NAME = "AustralianRomance";
    public static final String TEST_TOUR_PRICE_STRING = "1500";
    public static final float TEST_TOUR_PRICE_FLOAT = 1500;

    TourList testTourList = new TourList();
    ClientList dummyClientList = new ClientList();
    FlightList dummyFlightList = new FlightList();
    ClientPackageList dummyPackageList = new ClientPackageList();
    Ui testUi = new Ui();


    @Test
    void addTourCommand_validData_correctlyConstructed() {
        Tour testTour =
                new Tour(new String[]{TEST_TOUR_ID,
                    TEST_TOUR_NAME,
                    TEST_TOUR_PRICE_STRING});
        AddTourCommand addTour = new AddTourCommand(testTour);

        Tour retrieveTour = addTour.getTour();
        assertEquals(TEST_TOUR_ID, retrieveTour.getId());
        assertEquals(TEST_TOUR_NAME, retrieveTour.getName());
        assertEquals(TEST_TOUR_PRICE_FLOAT, retrieveTour.getPrice());
    }

    @Test
    void addTourCommand_emptyTourList_populatedTourList() {
        Tour testTour =
                new Tour(new String[]{
                    TEST_TOUR_ID,
                    TEST_TOUR_NAME,
                    TEST_TOUR_PRICE_STRING});
        AddTourCommand addTour = new AddTourCommand(testTour);
        addTour.setData(dummyClientList, dummyFlightList, testTourList, dummyPackageList, testUi);
        addTour.execute();

        Tour retrieveTour = addTour.getTour();
        assertEquals(TEST_TOUR_ID, retrieveTour.getId());
        assertEquals(TEST_TOUR_NAME, retrieveTour.getName());
        assertEquals(TEST_TOUR_PRICE_FLOAT, retrieveTour.getPrice());
    }
}