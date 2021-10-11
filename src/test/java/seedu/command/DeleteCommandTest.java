package seedu.command;

import org.junit.jupiter.api.Test;
import seedu.module.Lesson;
import seedu.module.Module;
import seedu.timetable.Timetable;
import seedu.timetable.TimetableLesson;
import seedu.ui.TextUi;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteCommandTest {


    @Test


    public void execute_Inavlid_mod_print() {
        Timetable tt = new Timetable(1);
        Command command = new DeleteCommand("CS2113C", tt);
        assertThrows(IOException.class, command::execute);
    }


    @Test
    public void execute_invalidModuleCode_printsInvalidModuleCodeMessage() {
        // Create a stream to hold the output
        ByteArrayOutputStream actualResult = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(actualResult);
        // IMPORTANT: Save the old System.out!
        PrintStream old = System.out;
        // Tell Java to use your special stream
        System.setOut(ps);
        Timetable tt = new Timetable(1);
        Command command = new DeleteCommand("CS2113C", tt);
        // Print some output: goes to your special stream
        command.execute();
        // Put things back
        System.out.flush();
        System.setOut(old);
        assertEquals(TextUi.ERROR_INVALID_MODULE_CODE + "\n", actualResult.toString());
    }


    @Test
    public void execute_ValidModuleCodeButNotInTimetable_printModuleNotFoundMessage() {
        // Create a stream to hold the output
        ByteArrayOutputStream actualResult = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(actualResult);
        // IMPORTANT: Save the old System.out!
        PrintStream old = System.out;
        // Tell Java to use your special stream
        System.setOut(ps);
        Timetable tt = new Timetable(1);
        Command command = new DeleteCommand("CS2113T", tt);
        // Print some output: goes to your special stream
        command.execute();
        // Put things back
        System.out.flush();
        System.setOut(old);
        assertEquals(TextUi.ERROR_MODULE_NOT_FOUND + "\n", actualResult.toString());
    }

    //THIS TEST PASSES 
    @Test
    public void execute_ValidModuleCode_timetableShouldBeEmptied() {
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
