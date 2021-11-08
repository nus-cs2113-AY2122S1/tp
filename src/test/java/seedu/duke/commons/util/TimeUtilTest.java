package seedu.duke.commons.util;

import org.junit.jupiter.api.Test;
import seedu.duke.DukeException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.duke.commons.util.TimeUtil.compareTime;

public class TimeUtilTest {
    @Test
    public void testCompareTime() {
        try {
            assertEquals(-1, compareTime("10:10", "11:11"));
            assertEquals(0, compareTime("11:11", "11:11"));
            assertEquals(1, compareTime("11:11", "10:10"));
        } catch (DukeException e) {
            fail(); // The progam should never reach this line
        }
    }

    @Test
    public void compareTime_invalidTime_exceptionThrown() {
        assertThrows(DukeException.class, () -> compareTime("24:00", "01:00"));
        assertThrows(DukeException.class, () -> compareTime("01:00", "24:00"));
        assertThrows(DukeException.class, () -> compareTime("24:00", "24:00"));
    }
}
