package taa.attendance;

//@@author daknam2001
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import taa.Parser;
import taa.Ui;
import taa.command.Command;
import taa.exception.TaaException;
import taa.storage.Storage;
import taa.teachingclass.ClassList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DeleteAttendanceTest {
    @Test
    void deleteAttendance_oneAttendance_expectRemoveFromAttendanceList() throws TaaException {
        Ui ui = new Ui();
        Storage storage = new Storage(null);
        ClassList classList = new ClassList();
        String classInput = "add_class i/cs2113t";
        Command command1 = Parser.parseUserInput(classInput);
        command1.parseArgument();
        command1.checkArgument();
        command1.execute(classList, ui, storage);

        String studentInput = "add_student c/cs2113t i/A012345 n/Jim Ho";
        Command command2 = Parser.parseUserInput(studentInput);
        command2.parseArgument();
        command2.checkArgument();
        command2.execute(classList, ui, storage);

        String attendanceInput = "set_attendance c/cs2113t s/1 l/1 p/1";
        Command command3 = Parser.parseUserInput(attendanceInput);
        command3.parseArgument();
        command3.checkArgument();
        command3.execute(classList, ui, storage);

        String deleteInput = "delete_attendance c/cs2113t s/1 l/1";
        Command command4 = Parser.parseUserInput(deleteInput);
        command4.parseArgument();
        command4.checkArgument();
        command4.execute(classList, ui, storage);

        assertNull(classList.getClassWithId("cs2113t").getStudentList().getStudentAt(0)
                .getAttendanceList().getAttendance(1));
    }

    @Test
    void deleteAttendance_rangeAttendance_expectRemoveFromAttendanceList() throws TaaException {
        Ui ui = new Ui();
        Storage storage = new Storage(null);
        ClassList classList = new ClassList();
        String classInput = "add_class i/cs2113t";
        Command command1 = Parser.parseUserInput(classInput);
        command1.parseArgument();
        command1.checkArgument();
        command1.execute(classList, ui, storage);

        String studentInput = "add_student c/cs2113t i/A012345 n/Jim Ho";
        Command command2 = Parser.parseUserInput(studentInput);
        command2.parseArgument();
        command2.checkArgument();
        command2.execute(classList, ui, storage);

        String studentInput2 = "add_student c/cs2113t i/A2345 n/Luke Sky";
        Command command3 = Parser.parseUserInput(studentInput2);
        command3.parseArgument();
        command3.checkArgument();
        command3.execute(classList, ui, storage);

        String studentInput3 = "add_student c/cs2113t i/37472 n/Seb Vettel";
        Command command4 = Parser.parseUserInput(studentInput3);
        command4.parseArgument();
        command4.checkArgument();
        command4.execute(classList, ui, storage);

        String studentInput4 = "add_student c/cs2113t i/3741 n/Kimi Rai";
        Command command5 = Parser.parseUserInput(studentInput4);
        command5.parseArgument();
        command5.checkArgument();
        command5.execute(classList, ui, storage);

        String attendanceInput = "set_attendance c/cs2113t s/1-4 l/2 p/0";
        Command command6 = Parser.parseUserInput(attendanceInput);
        command6.parseArgument();
        command6.checkArgument();
        command6.execute(classList, ui, storage);

        String deleteInput = "delete_attendance c/cs2113t s/2-4 l/2";
        Command command7 = Parser.parseUserInput(deleteInput);
        command7.parseArgument();
        command7.checkArgument();
        command7.execute(classList, ui, storage);

        assertEquals("Lesson 2 (Absent)",
                classList.getClassWithId("cs2113t").getStudentList().getStudentAt(0)
                        .getAttendanceList().getAttendance(2).toString());
        assertNull(classList.getClassWithId("cs2113t").getStudentList().getStudentAt(1)
                .getAttendanceList().getAttendance(2));
        assertNull(classList.getClassWithId("cs2113t").getStudentList().getStudentAt(2)
                .getAttendanceList().getAttendance(2));
        assertNull(classList.getClassWithId("cs2113t").getStudentList().getStudentAt(3)
                .getAttendanceList().getAttendance(2));
    }

    @Test
    void deleteAttendance_selectedAttendance_expectRemoveFromAttendanceList() throws TaaException {
        Ui ui = new Ui();
        Storage storage = new Storage(null);
        ClassList classList = new ClassList();
        String classInput = "add_class i/cs2113t";
        Command command1 = Parser.parseUserInput(classInput);
        command1.parseArgument();
        command1.checkArgument();
        command1.execute(classList, ui, storage);

        String studentInput = "add_student c/cs2113t i/A012345 n/Jim Ho";
        Command command2 = Parser.parseUserInput(studentInput);
        command2.parseArgument();
        command2.checkArgument();
        command2.execute(classList, ui, storage);

        String studentInput2 = "add_student c/cs2113t i/A2345 n/Luke Sky";
        Command command3 = Parser.parseUserInput(studentInput2);
        command3.parseArgument();
        command3.checkArgument();
        command3.execute(classList, ui, storage);

        String studentInput3 = "add_student c/cs2113t i/37472 n/Seb Vettel";
        Command command4 = Parser.parseUserInput(studentInput3);
        command4.parseArgument();
        command4.checkArgument();
        command4.execute(classList, ui, storage);

        String studentInput4 = "add_student c/cs2113t i/3741 n/Kimi Rai";
        Command command5 = Parser.parseUserInput(studentInput4);
        command5.parseArgument();
        command5.checkArgument();
        command5.execute(classList, ui, storage);

        String attendanceInput = "set_attendance c/cs2113t s/1-4 l/3 p/1";
        Command command6 = Parser.parseUserInput(attendanceInput);
        command6.parseArgument();
        command6.checkArgument();
        command6.execute(classList, ui, storage);

        String deleteInput = "delete_attendance c/cs2113t s/1,3,4 l/3";
        Command command7 = Parser.parseUserInput(deleteInput);
        command7.parseArgument();
        command7.checkArgument();
        command7.execute(classList, ui, storage);

        assertNull(classList.getClassWithId("cs2113t").getStudentList().getStudentAt(0)
                .getAttendanceList().getAttendance(3));
        assertEquals("Lesson 3 (Present)",
                classList.getClassWithId("cs2113t").getStudentList().getStudentAt(1)
                        .getAttendanceList().getAttendance(3).toString());
        assertNull(classList.getClassWithId("cs2113t").getStudentList().getStudentAt(2)
                .getAttendanceList().getAttendance(3));
        assertNull(classList.getClassWithId("cs2113t").getStudentList().getStudentAt(3)
                .getAttendanceList().getAttendance(3));
    }

    @Test
    void deleteAttendance_invalidStudentIndex_taaExceptionThrown() throws TaaException {
        Ui ui = new Ui();
        Storage storage = new Storage(null);
        ClassList classList = new ClassList();
        String classInput = "add_class i/cs2113t";
        Command command1 = Parser.parseUserInput(classInput);
        command1.parseArgument();
        command1.checkArgument();
        command1.execute(classList, ui, storage);

        String studentInput = "add_student c/cs2113t i/A012345 n/Jim Ho";
        Command command2 = Parser.parseUserInput(studentInput);
        command2.parseArgument();
        command2.checkArgument();
        command2.execute(classList, ui, storage);

        TaaException thrown = Assertions.assertThrows(TaaException.class, () -> {
            String deleteInput = "delete_attendance c/cs2113t s/2 l/2";
            Command command3 = Parser.parseUserInput(deleteInput);
            command3.parseArgument();
            command3.checkArgument();
            command3.execute(classList, ui, storage);
        }, "TaaException error was expected");
        Assertions.assertEquals("Invalid student index.",
                thrown.getMessage());
    }

    @Test
    void deleteAttendance_invalidLessonNumber_taaExceptionThrown() throws TaaException {
        Ui ui = new Ui();
        Storage storage = new Storage(null);
        ClassList classList = new ClassList();
        String classInput = "add_class i/cs2113t";
        Command command1 = Parser.parseUserInput(classInput);
        command1.parseArgument();
        command1.checkArgument();
        command1.execute(classList, ui, storage);

        String studentInput = "add_student c/cs2113t i/A012345 n/Jim Ho";
        Command command2 = Parser.parseUserInput(studentInput);
        command2.parseArgument();
        command2.checkArgument();
        command2.execute(classList, ui, storage);

        String attendanceInput = "set_attendance c/cs2113t s/1 l/2 p/1";
        Command command3 = Parser.parseUserInput(attendanceInput);
        command3.parseArgument();
        command3.checkArgument();
        command3.execute(classList, ui, storage);

        TaaException thrown = Assertions.assertThrows(TaaException.class, () -> {
            String deleteInput = "delete_attendance c/cs2113t s/1 l/1";
            Command command4 = Parser.parseUserInput(deleteInput);
            command4.parseArgument();
            command4.checkArgument();
            command4.execute(classList, ui, storage);
        }, "TaaException error was expected");
        Assertions.assertEquals("Invalid lesson number for student of index 1 (Jim Ho).",
                thrown.getMessage());
    }

    @Test
    void deleteAttendance_LessonNumberExceedsMax_taaExceptionThrown() throws TaaException {
        Ui ui = new Ui();
        Storage storage = new Storage(null);
        ClassList classList = new ClassList();
        String classInput = "add_class i/cs2113t";
        Command command1 = Parser.parseUserInput(classInput);
        command1.parseArgument();
        command1.checkArgument();
        command1.execute(classList, ui, storage);

        String studentInput = "add_student c/cs2113t i/A012345 n/Jim Ho";
        Command command2 = Parser.parseUserInput(studentInput);
        command2.parseArgument();
        command2.checkArgument();
        command2.execute(classList, ui, storage);

        String attendanceInput = "set_attendance c/cs2113t s/1 l/2 p/1";
        Command command3 = Parser.parseUserInput(attendanceInput);
        command3.parseArgument();
        command3.checkArgument();
        command3.execute(classList, ui, storage);

        TaaException thrown = Assertions.assertThrows(TaaException.class, () -> {
            String deleteInput = "delete_attendance c/cs2113t s/1 l/1001";
            Command command4 = Parser.parseUserInput(deleteInput);
            command4.parseArgument();
            command4.checkArgument();
            command4.execute(classList, ui, storage);
        }, "TaaException error was expected");
        Assertions.assertEquals("Lesson number exceeds 1000.",
                thrown.getMessage());
    }

    @Test
    void deleteAttendance_studentIndexNotAnInteger_taaExceptionThrown() throws TaaException {
        Ui ui = new Ui();
        Storage storage = new Storage(null);
        ClassList classList = new ClassList();
        String classInput = "add_class i/cs2113t";
        Command command1 = Parser.parseUserInput(classInput);
        command1.parseArgument();
        command1.checkArgument();
        command1.execute(classList, ui, storage);

        TaaException thrown = Assertions.assertThrows(TaaException.class, () -> {
            String deleteInput = "delete_attendance c/cs2113t s/a l/2";
            Command command2 = Parser.parseUserInput(deleteInput);
            command2.parseArgument();
            command2.checkArgument();
            command2.execute(classList, ui, storage);
        }, "TaaException error was expected");
        Assertions.assertEquals("Invalid student index.",
                thrown.getMessage());
    }

    @Test
    void deleteAttendance_lessonNumberNotAnInteger_taaExceptionThrown() throws TaaException {
        Ui ui = new Ui();
        Storage storage = new Storage(null);
        ClassList classList = new ClassList();
        String classInput = "add_class i/cs2113t";
        Command command1 = Parser.parseUserInput(classInput);
        command1.parseArgument();
        command1.checkArgument();
        command1.execute(classList, ui, storage);

        TaaException thrown = Assertions.assertThrows(TaaException.class, () -> {
            String deleteInput = "delete_attendance c/cs2113t s/1 l/a";
            Command command2 = Parser.parseUserInput(deleteInput);
            command2.parseArgument();
            command2.checkArgument();
            command2.execute(classList, ui, storage);
        }, "TaaException error was expected");
        Assertions.assertEquals("Invalid lesson number.",
                thrown.getMessage());
    }

    @Test
    void deleteAttendance_rangeAttendanceOutOfBounds_taaExceptionThrown() throws TaaException {
        Ui ui = new Ui();
        Storage storage = new Storage(null);
        ClassList classList = new ClassList();
        String classInput = "add_class i/cs2113t";
        Command command1 = Parser.parseUserInput(classInput);
        command1.parseArgument();
        command1.checkArgument();
        command1.execute(classList, ui, storage);

        String studentInput = "add_student c/cs2113t i/A012345 n/Jim Ho";
        Command command2 = Parser.parseUserInput(studentInput);
        command2.parseArgument();
        command2.checkArgument();
        command2.execute(classList, ui, storage);

        String studentInput2 = "add_student c/cs2113t i/A2345 n/Luke Sky";
        Command command3 = Parser.parseUserInput(studentInput2);
        command3.parseArgument();
        command3.checkArgument();
        command3.execute(classList, ui, storage);

        String studentInput3 = "add_student c/cs2113t i/37472 n/Seb Vettel";
        Command command4 = Parser.parseUserInput(studentInput3);
        command4.parseArgument();
        command4.checkArgument();
        command4.execute(classList, ui, storage);

        String studentInput4 = "add_student c/cs2113t i/3741 n/Kimi Rai";
        Command command5 = Parser.parseUserInput(studentInput4);
        command5.parseArgument();
        command5.checkArgument();
        command5.execute(classList, ui, storage);

        String attendanceInput = "set_attendance c/cs2113t s/1-4 l/1 p/1";
        Command command6 = Parser.parseUserInput(attendanceInput);
        command6.parseArgument();
        command6.checkArgument();
        command6.execute(classList, ui, storage);

        TaaException thrown = Assertions.assertThrows(TaaException.class, () -> {
            String deleteInput = "delete_attendance c/cs2113t s/3-10 l/1";
            Command command7 = Parser.parseUserInput(deleteInput);
            command7.parseArgument();
            command7.checkArgument();
            command7.execute(classList, ui, storage);
        }, "TaaException error was expected");
        Assertions.assertEquals("Invalid student index.",
                thrown.getMessage());
    }

    @Test
    void deleteAttendance_selectionAttendanceOutOfBounds_taaExceptionThrown() throws TaaException {
        Ui ui = new Ui();
        Storage storage = new Storage(null);
        ClassList classList = new ClassList();
        String classInput = "add_class i/cs2113t";
        Command command1 = Parser.parseUserInput(classInput);
        command1.parseArgument();
        command1.checkArgument();
        command1.execute(classList, ui, storage);

        String studentInput = "add_student c/cs2113t i/A012345 n/Jim Ho";
        Command command2 = Parser.parseUserInput(studentInput);
        command2.parseArgument();
        command2.checkArgument();
        command2.execute(classList, ui, storage);

        String studentInput2 = "add_student c/cs2113t i/A2345 n/Luke Sky";
        Command command3 = Parser.parseUserInput(studentInput2);
        command3.parseArgument();
        command3.checkArgument();
        command3.execute(classList, ui, storage);

        String studentInput3 = "add_student c/cs2113t i/37472 n/Seb Vettel";
        Command command4 = Parser.parseUserInput(studentInput3);
        command4.parseArgument();
        command4.checkArgument();
        command4.execute(classList, ui, storage);

        String studentInput4 = "add_student c/cs2113t i/3741 n/Kimi Rai";
        Command command5 = Parser.parseUserInput(studentInput4);
        command5.parseArgument();
        command5.checkArgument();
        command5.execute(classList, ui, storage);

        String attendanceInput = "set_attendance c/cs2113t s/1-4 l/1 p/1";
        Command command6 = Parser.parseUserInput(attendanceInput);
        command6.parseArgument();
        command6.checkArgument();
        command6.execute(classList, ui, storage);

        TaaException thrown = Assertions.assertThrows(TaaException.class, () -> {
            String deleteInput = "delete_attendance c/cs2113t s/1,3,10 l/1";
            Command command7 = Parser.parseUserInput(deleteInput);
            command7.parseArgument();
            command7.checkArgument();
            command7.execute(classList, ui, storage);
        }, "TaaException error was expected");
        Assertions.assertEquals("Invalid student index.",
                thrown.getMessage());
    }

    @Test
    void deleteAttendance_invalidRangeFormat1_taaExceptionThrown() throws TaaException {
        Ui ui = new Ui();
        Storage storage = new Storage(null);
        ClassList classList = new ClassList();
        String classInput = "add_class i/cs2113t";
        Command command1 = Parser.parseUserInput(classInput);
        command1.parseArgument();
        command1.checkArgument();
        command1.execute(classList, ui, storage);

        String studentInput = "add_student c/cs2113t i/A012345 n/Jim Ho";
        Command command2 = Parser.parseUserInput(studentInput);
        command2.parseArgument();
        command2.checkArgument();
        command2.execute(classList, ui, storage);

        String studentInput2 = "add_student c/cs2113t i/A2345 n/Luke Sky";
        Command command3 = Parser.parseUserInput(studentInput2);
        command3.parseArgument();
        command3.checkArgument();
        command3.execute(classList, ui, storage);

        String studentInput3 = "add_student c/cs2113t i/37472 n/Seb Vettel";
        Command command4 = Parser.parseUserInput(studentInput3);
        command4.parseArgument();
        command4.checkArgument();
        command4.execute(classList, ui, storage);

        String studentInput4 = "add_student c/cs2113t i/3741 n/Kimi Rai";
        Command command5 = Parser.parseUserInput(studentInput4);
        command5.parseArgument();
        command5.checkArgument();
        command5.execute(classList, ui, storage);

        String attendanceInput = "set_attendance c/cs2113t s/1-4 l/1 p/1";
        Command command6 = Parser.parseUserInput(attendanceInput);
        command6.parseArgument();
        command6.checkArgument();
        command6.execute(classList, ui, storage);

        TaaException thrown = Assertions.assertThrows(TaaException.class, () -> {
            String deleteInput = "delete_attendance c/cs2113t s/1--2 l/1";
            Command command7 = Parser.parseUserInput(deleteInput);
            command7.parseArgument();
            command7.checkArgument();
            command7.execute(classList, ui, storage);
        }, "TaaException error was expected");
        Assertions.assertEquals("Invalid range format.",
                thrown.getMessage());
    }

    @Test
    void deleteAttendance_invalidSelectionFormat1_taaExceptionThrown() throws TaaException {
        Ui ui = new Ui();
        Storage storage = new Storage(null);
        ClassList classList = new ClassList();
        String classInput = "add_class i/cs2113t";
        Command command1 = Parser.parseUserInput(classInput);
        command1.parseArgument();
        command1.checkArgument();
        command1.execute(classList, ui, storage);

        String studentInput = "add_student c/cs2113t i/A012345 n/Jim Ho";
        Command command2 = Parser.parseUserInput(studentInput);
        command2.parseArgument();
        command2.checkArgument();
        command2.execute(classList, ui, storage);

        String studentInput2 = "add_student c/cs2113t i/A2345 n/Luke Sky";
        Command command3 = Parser.parseUserInput(studentInput2);
        command3.parseArgument();
        command3.checkArgument();
        command3.execute(classList, ui, storage);

        String studentInput3 = "add_student c/cs2113t i/37472 n/Seb Vettel";
        Command command4 = Parser.parseUserInput(studentInput3);
        command4.parseArgument();
        command4.checkArgument();
        command4.execute(classList, ui, storage);

        String studentInput4 = "add_student c/cs2113t i/3741 n/Kimi Rai";
        Command command5 = Parser.parseUserInput(studentInput4);
        command5.parseArgument();
        command5.checkArgument();
        command5.execute(classList, ui, storage);

        String attendanceInput = "set_attendance c/cs2113t s/1-4 l/1 p/1";
        Command command6 = Parser.parseUserInput(attendanceInput);
        command6.parseArgument();
        command6.checkArgument();
        command6.execute(classList, ui, storage);

        TaaException thrown = Assertions.assertThrows(TaaException.class, () -> {
            String deleteInput = "delete_attendance c/cs2113t s/1,,2 l/1";
            Command command7 = Parser.parseUserInput(deleteInput);
            command7.parseArgument();
            command7.checkArgument();
            command7.execute(classList, ui, storage);
        }, "TaaException error was expected");
        Assertions.assertEquals("Invalid format for selected indexes.",
                thrown.getMessage());
    }

}
