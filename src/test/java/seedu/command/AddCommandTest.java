package seedu.command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import seedu.command.flags.AddFlag;
import seedu.timetable.Timetable;

class AddCommandTest {
    @Test
    public void semesterEqualTimetableSemester() {
        Timetable tt = new Timetable(1);
        AddCommand ac = new AddCommand(tt, AddFlag.LESSON);

        Timetable tt2 = new Timetable(2);
        AddCommand ac2 = new AddCommand(tt2, AddFlag.LESSON);

        assertEquals(ac.getClass(), ac2.getClass());
    }

    @Test
    public void parseDataIsAccurate() {
        Timetable tt = new Timetable(1);
        AddCommand ac = new AddCommand(tt, AddFlag.LESSON);
        String output = ac.parseDate("Monday");

        assertEquals(output, "Monday");
    }
}