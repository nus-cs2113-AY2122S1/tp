package happybit.goal;

import happybit.exception.HappyBitException;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class GoalTest {

    /**
     * Test getDescription when a goal class is initialised with the start date.
     */
    @Test
    void getDescription_withStartDate_expectStringDescription() throws HappyBitException {
        final Goal testGoal = new Goal("test", "07-Oct-2021", "21-Oct-2021");
        final String expectedResult = "test (Start: 07-Oct-2021 | End: 21-Oct-2021)\n";
        final String actualResult = testGoal.getDescription();
        assertEquals(expectedResult, actualResult);
    }

    /**
     * Test getDescription when a goal class is initialised without the start date.
     */
    @Test
    void getDescription_withoutStartDate_expectStringDescription() throws HappyBitException {
        final Goal testGoal = new Goal("test", "21-Oct-2021");
        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        String strCurrentDate = formatter.format(currentDate);
        final String expectedResult = "test (Start: " + strCurrentDate + " | End: 21-Oct-2021)\n";
        final String actualResult = testGoal.getDescription();
        assertEquals(expectedResult, actualResult);
    }

    /**
     * Test getDescription when a goal class is initialised with an invalid start date format.
     */
    @Test
    void getDescription_invalidStartDateFormat_expectException() {
        assertThrows(HappyBitException.class,
                () -> new Goal("test", "7-10-2021", "21-Oct-2021"));
    }

    /**
     * Test getDescription when a goal class is initialised with an invalid end date format.
     */
    @Test
    void getDescription_invalidEndDateFormat_expectException() {
        assertThrows(HappyBitException.class,
                () -> new Goal("test", "07-Oct-2021", "01/01/2021"));
    }

    /**
     * Test getDescription when a goal class is initialised with start date after the end date.
     */
    @Test
    void getDescription_startDateAfterEndDate_expectException() {
        assertThrows(HappyBitException.class,
                () -> new Goal("test", "07-Oct-2021", "07-Jan-2021"));
    }

    /**
     * Test if isDone is set properly if current date is after the end date.
     */
    @Test
    void setDone_currentDateAfterEndDate_isDoneEqualsFalse() throws HappyBitException {
        final Goal testGoal = new Goal("test", "07-Jan-2021", "21-Feb-2021");
        testGoal.setDone();
        final boolean expectedResult = true;
        assertEquals(expectedResult, testGoal.isDone);
    }

    /**
     * Test if isDone is set properly if current date is before the end date.
     */
    @Test
    void setDone_currentDateBeforeEndDate_isDoneEqualsFalse() throws HappyBitException {
        final Goal testGoal = new Goal("test", "07-Jan-2021", "21-Feb-2050");
        testGoal.setDone();
        final boolean expectedResult = false;
        assertEquals(expectedResult, testGoal.isDone);
    }

}
