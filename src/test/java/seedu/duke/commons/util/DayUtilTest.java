package seedu.duke.commons.util;

import org.junit.jupiter.api.Test;
import seedu.duke.DukeException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.duke.commons.util.DayUtil.compareDay;

public class DayUtilTest {
    @Test
    public void testCompareDay() {
        try {
            assertEquals(-1, compareDay("Monday", "Tuesday"));
            assertEquals(0, compareDay("Monday", "Monday"));
            assertEquals(1, compareDay("Tuesday", "Monday"));
        } catch (DukeException e) {
            fail(); // The program should never reach this line
        }
    }

    @Test
    public void compareDay_invalidDay_exceptionThrown() {
        assertThrows(DukeException.class, () -> compareDay("Monday", "Mondayy"));
        assertThrows(DukeException.class, () -> compareDay("Mondayy", "Monday"));
        assertThrows(DukeException.class, () -> compareDay("Mondayy", "Mondayy"));
    }
}
