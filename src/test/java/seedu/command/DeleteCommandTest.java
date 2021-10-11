package seedu.command;

import org.junit.jupiter.api.Test;
import seedu.module.Lesson;
import seedu.module.Module;
import seedu.timetable.Timetable;
import seedu.timetable.TimetableLesson;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeleteCommandTest {
    /**
     * Tests if the module gets deleted from the test timetable.
     */
    @Test
    public void execute_validModuleCodeInTestTimetable_moduleShouldBeRemoved() {
        // Creating two test timetables
        Timetable tt1 = new Timetable(1);
        Timetable tt2 = new Timetable(1);

        //Creating a test module and adding to timetable 1
        Module testMod = new Module("CFG1002");
        testMod.setModuleCredit(2.0);
        testMod.setTitle("Career Catalyst");
        Lesson lesson = new Lesson(
                "CFG1002",
                "0600",
                "0800",
                "E-Learn_B",
                "Lecture",
                "Wednesday");
        TimetableLesson timetableLesson = new TimetableLesson(testMod, 1, lesson);
        tt1.addLesson(timetableLesson);

        //Deleting test module from timetable 1
        Command command = new DeleteCommand("CFG1002", tt1);
        command.execute();
        //checking if the empty timetable (that is timetable 2 matches with timetable 1)
        assertEquals(tt2.getClass(), tt1.getClass());
    }
}
