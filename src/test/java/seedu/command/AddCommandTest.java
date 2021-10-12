package seedu.command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import seedu.timetable.Timetable;

class AddCommandTest {
    @Test
    public void semesterEqualTimetableSemester() {
        Timetable tt = new Timetable(1);
        AddCommand ac = new AddCommand("CS2113T", tt);

        Timetable tt2 = new Timetable(2);
        AddCommand ac2 = new AddCommand("CS2113T", tt2);

        assertEquals(ac.getClass(), ac2.getClass());
    }
}