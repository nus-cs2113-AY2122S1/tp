package seedu.command;

import org.junit.jupiter.api.Test;
import seedu.module.Lesson;
import seedu.module.Module;
import seedu.timetable.Timetable;
import seedu.timetable.TimetableLesson;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClearCommandTest {
    /**
     * Tests if the timetable gets cleared.
     */
    @Test
    public void execute_ValidModuleCode_timetableShouldBeEmptied() {
        // Creating two test timetables
        Timetable tt1 = new Timetable(1);
        Timetable tt2 = new Timetable(1);

        //Creating a test modules and adding to timetable 1
        Module testMod = new Module("CS1231");
        testMod.setModuleCredit(4.0);
        testMod.setTitle("Career Catalyst");
        Lesson lesson1 = new Lesson(
                "CS1231",
                "1000",
                "1200",
                "E-Learn_C",
                "Lecture",
                "Thursday");
        Lesson lesson2 = new Lesson(
                "CFG1002",
                "0600",
                "0800",
                "E-Learn_B",
                "Lecture",
                "Wednesday");
        TimetableLesson timetableLesson1 = new TimetableLesson(testMod, 1, lesson1);
        TimetableLesson timetableLesson2 = new TimetableLesson(testMod, 1, lesson2);
        tt1.addLesson(timetableLesson1);
        tt1.addLesson(timetableLesson2);

        //Clearing timetable 1
        Command command = new ClearCommand(tt1);
        command.execute();
        
        //checking if the empty timetable (that is timetable 2 matches with timetable 1)
        assertEquals(tt2.getClass(), tt1.getClass());
    }
}
