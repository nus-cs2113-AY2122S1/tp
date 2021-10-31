package seedu.duke.commons.core;

import org.junit.jupiter.api.Test;
import seedu.duke.DukeException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

public class DayOfTheWeekTest {
    @Test
    public void isDayOfTheWeek_validMonday_true() {
        assertTrue(DayOfTheWeek.is("mon"));
        assertTrue(DayOfTheWeek.is("mond"));
        assertTrue(DayOfTheWeek.is("monda"));
        assertTrue(DayOfTheWeek.is("monday"));
    }

    @Test
    public void isDayOfTheWeek_validTuesday_true() {
        assertTrue(DayOfTheWeek.is("tue"));
        assertTrue(DayOfTheWeek.is("tues"));
        assertTrue(DayOfTheWeek.is("tuesd"));
        assertTrue(DayOfTheWeek.is("tuesda"));
        assertTrue(DayOfTheWeek.is("tuesday"));
    }

    @Test
    public void isDayOfTheWeek_validWednesday_true() {
        assertTrue(DayOfTheWeek.is("wed"));
        assertTrue(DayOfTheWeek.is("wedn"));
        assertTrue(DayOfTheWeek.is("wedne"));
        assertTrue(DayOfTheWeek.is("wednes"));
        assertTrue(DayOfTheWeek.is("wednesd"));
        assertTrue(DayOfTheWeek.is("wednesda"));
        assertTrue(DayOfTheWeek.is("wednesday"));
    }

    @Test
    public void isDayOfTheWeek_validThursday_true() {
        assertTrue(DayOfTheWeek.is("thu"));
        assertTrue(DayOfTheWeek.is("thur"));
        assertTrue(DayOfTheWeek.is("thurs"));
        assertTrue(DayOfTheWeek.is("thursd"));
        assertTrue(DayOfTheWeek.is("thursda"));
        assertTrue(DayOfTheWeek.is("thursday"));
    }

    @Test
    public void isDayOfTheWeek_validFriday_true() {
        assertTrue(DayOfTheWeek.is("fri"));
        assertTrue(DayOfTheWeek.is("frid"));
        assertTrue(DayOfTheWeek.is("frida"));
        assertTrue(DayOfTheWeek.is("friday"));
    }

    @Test
    public void isDayOfTheWeek_validSaturday_true() {
        assertTrue(DayOfTheWeek.is("sat"));
        assertTrue(DayOfTheWeek.is("satu"));
        assertTrue(DayOfTheWeek.is("satur"));
        assertTrue(DayOfTheWeek.is("saturd"));
        assertTrue(DayOfTheWeek.is("saturda"));
        assertTrue(DayOfTheWeek.is("saturday"));
    }

    @Test
    public void isDayOfTheWeek_validSunday_true() {
        assertTrue(DayOfTheWeek.is("sun"));
        assertTrue(DayOfTheWeek.is("sund"));
        assertTrue(DayOfTheWeek.is("sunda"));
        assertTrue(DayOfTheWeek.is("sunday"));
    }

    @Test
    public void isDayOfTheWeek_inputWithLengthLessThanThree_false() {
        assertFalse(DayOfTheWeek.is("mo"));
        assertFalse(DayOfTheWeek.is("tu"));
        assertFalse(DayOfTheWeek.is("we"));
        assertFalse(DayOfTheWeek.is("th"));
        assertFalse(DayOfTheWeek.is("fr"));
        assertFalse(DayOfTheWeek.is("sa"));
        assertFalse(DayOfTheWeek.is("su"));
    }

    @Test
    public void isDayOfTheWeek_invalidDay_false() {
        assertFalse(DayOfTheWeek.is("mondayy"));
        assertFalse(DayOfTheWeek.is("tuesdayy"));
        assertFalse(DayOfTheWeek.is("wednesdayy"));
        assertFalse(DayOfTheWeek.is("thursdayy"));
        assertFalse(DayOfTheWeek.is("fridayy"));
        assertFalse(DayOfTheWeek.is("saturdayy"));
        assertFalse(DayOfTheWeek.is("sundayy"));
    }

    @Test
    public void toProper_validDay_properFormat() {
        try {
            assertEquals("Monday", DayOfTheWeek.toProper("MON"));
            assertEquals("Monday", DayOfTheWeek.toProper("MONDA"));
            assertEquals("Tuesday", DayOfTheWeek.toProper("TUE"));
            assertEquals("Tuesday", DayOfTheWeek.toProper("TUESD"));
            assertEquals("Wednesday", DayOfTheWeek.toProper("WED"));
            assertEquals("Wednesday", DayOfTheWeek.toProper("WEDNES"));
            assertEquals("Thursday", DayOfTheWeek.toProper("THU"));
            assertEquals("Thursday", DayOfTheWeek.toProper("THUR"));
            assertEquals("Friday", DayOfTheWeek.toProper("FRI"));
            assertEquals("Friday", DayOfTheWeek.toProper("FRIDAY"));
            assertEquals("Saturday", DayOfTheWeek.toProper("SAT"));
            assertEquals("Saturday", DayOfTheWeek.toProper("SATURDA"));
            assertEquals("Sunday", DayOfTheWeek.toProper("SUN"));
            assertEquals("Sunday", DayOfTheWeek.toProper("SUNDAY"));
        } catch (DukeException e) {
            fail(); // The program should never reach this line
        }
    }

    @Test
    public void toProper_invalidDay_exceptionThrown() {
        assertThrows(DukeException.class, () -> DayOfTheWeek.toProper("montue"));
        assertThrows(DukeException.class, () -> DayOfTheWeek.toProper("tuewed"));
        assertThrows(DukeException.class, () -> DayOfTheWeek.toProper("wedthu"));
        assertThrows(DukeException.class, () -> DayOfTheWeek.toProper("thufri"));
        assertThrows(DukeException.class, () -> DayOfTheWeek.toProper("frisat"));
        assertThrows(DukeException.class, () -> DayOfTheWeek.toProper("satsun"));
        assertThrows(DukeException.class, () -> DayOfTheWeek.toProper("sunmon"));
    }
}
