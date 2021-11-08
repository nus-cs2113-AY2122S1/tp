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

public class SetAttendanceTest {
    @Test
    void setAttendancePresent_oneAttendance_expectAddToAttendanceList() throws TaaException {
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

        assertEquals("Lesson 1 (Present)",
                classList.getClassWithId("cs2113t").getStudentList().getStudentAt(0)
                .getAttendanceList().getAttendance(1).toString());
    }

    @Test
    void setAttendanceAbsent_rangeAttendance_expectAddToAttendanceList() throws TaaException {
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

        String attendanceInput = "set_attendance c/cs2113t s/2-4 l/2 p/0";
        Command command6 = Parser.parseUserInput(attendanceInput);
        command6.parseArgument();
        command6.checkArgument();
        command6.execute(classList, ui, storage);

        assertEquals("Lesson 2 (Absent)",
                classList.getClassWithId("cs2113t").getStudentList().getStudentAt(1)
                        .getAttendanceList().getAttendance(2).toString());
        assertEquals("Lesson 2 (Absent)",
                classList.getClassWithId("cs2113t").getStudentList().getStudentAt(2)
                        .getAttendanceList().getAttendance(2).toString());
        assertEquals("Lesson 2 (Absent)",
                classList.getClassWithId("cs2113t").getStudentList().getStudentAt(3)
                        .getAttendanceList().getAttendance(2).toString());
    }

    @Test
    void setAttendancePresent_selectedAttendance_expectAddToAttendanceList() throws TaaException {
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

        String attendanceInput = "set_attendance c/cs2113t s/1,3,4 l/3 p/1";
        Command command6 = Parser.parseUserInput(attendanceInput);
        command6.parseArgument();
        command6.checkArgument();
        command6.execute(classList, ui, storage);

        assertEquals("Lesson 3 (Present)",
                classList.getClassWithId("cs2113t").getStudentList().getStudentAt(0)
                        .getAttendanceList().getAttendance(3).toString());
        assertEquals("Lesson 3 (Present)",
                classList.getClassWithId("cs2113t").getStudentList().getStudentAt(2)
                        .getAttendanceList().getAttendance(3).toString());
        assertEquals("Lesson 3 (Present)",
                classList.getClassWithId("cs2113t").getStudentList().getStudentAt(3)
                        .getAttendanceList().getAttendance(3).toString());
    }

    @Test
    void setAttendancePresent_invalidStudentIndex_taaExceptionThrown() throws TaaException {
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
            String attendanceInput = "set_attendance c/cs2113t s/2 l/2 p/1";
            Command command3 = Parser.parseUserInput(attendanceInput);
            command3.parseArgument();
            command3.checkArgument();
            command3.execute(classList, ui, storage);
        }, "TaaException error was expected");
        Assertions.assertEquals("Invalid student index.",
                thrown.getMessage());
    }

    @Test
    void setAttendancePresent_studentIndexNotAnInteger_taaExceptionThrown() throws TaaException {
        Ui ui = new Ui();
        Storage storage = new Storage(null);
        ClassList classList = new ClassList();
        String classInput = "add_class i/cs2113t";
        Command command1 = Parser.parseUserInput(classInput);
        command1.parseArgument();
        command1.checkArgument();
        command1.execute(classList, ui, storage);

        TaaException thrown = Assertions.assertThrows(TaaException.class, () -> {
            String attendanceInput = "set_attendance c/cs2113t s/a l/2 p/1";
            Command command2 = Parser.parseUserInput(attendanceInput);
            command2.parseArgument();
            command2.checkArgument();
            command2.execute(classList, ui, storage);
        }, "TaaException error was expected");
        Assertions.assertEquals("Invalid student index.",
                thrown.getMessage());
    }

    @Test
    void setAttendancePresent_lessonNumberNotAnInteger_taaExceptionThrown() throws TaaException {
        Ui ui = new Ui();
        Storage storage = new Storage(null);
        ClassList classList = new ClassList();
        String classInput = "add_class i/cs2113t";
        Command command1 = Parser.parseUserInput(classInput);
        command1.parseArgument();
        command1.checkArgument();
        command1.execute(classList, ui, storage);

        TaaException thrown = Assertions.assertThrows(TaaException.class, () -> {
            String attendanceInput = "set_attendance c/cs2113t s/1 l/a p/1";
            Command command2 = Parser.parseUserInput(attendanceInput);
            command2.parseArgument();
            command2.checkArgument();
            command2.execute(classList, ui, storage);
        }, "TaaException error was expected");
        Assertions.assertEquals("Invalid lesson number.",
                thrown.getMessage());
    }

    @Test
    void setAttendancePresent_lessonNumberExceedsMax_taaExceptionThrown() throws TaaException {
        Ui ui = new Ui();
        Storage storage = new Storage(null);
        ClassList classList = new ClassList();
        String classInput = "add_class i/cs2113t";
        Command command1 = Parser.parseUserInput(classInput);
        command1.parseArgument();
        command1.checkArgument();
        command1.execute(classList, ui, storage);

        TaaException thrown = Assertions.assertThrows(TaaException.class, () -> {
            String attendanceInput = "set_attendance c/cs2113t s/1 l/10001 p/1";
            Command command2 = Parser.parseUserInput(attendanceInput);
            command2.parseArgument();
            command2.checkArgument();
            command2.execute(classList, ui, storage);
        }, "TaaException error was expected");
        Assertions.assertEquals("Lesson number exceeds 1000.",
                thrown.getMessage());
    }

    @Test
    void setAttendance_presentNotBinary_taaExceptionThrown() throws TaaException {
        Ui ui = new Ui();
        Storage storage = new Storage(null);
        ClassList classList = new ClassList();
        String classInput = "add_class i/cs2113t";
        Command command1 = Parser.parseUserInput(classInput);
        command1.parseArgument();
        command1.checkArgument();
        command1.execute(classList, ui, storage);

        TaaException thrown = Assertions.assertThrows(TaaException.class, () -> {
            String attendanceInput = "set_attendance c/cs2113t s/1 l/1 p/2";
            Command command2 = Parser.parseUserInput(attendanceInput);
            command2.parseArgument();
            command2.checkArgument();
            command2.execute(classList, ui, storage);
        }, "TaaException error was expected");
        Assertions.assertEquals("Invalid present value.\nPossible values: 1 (Present), 0 (Absent)",
                thrown.getMessage());
    }

    @Test
    void setAttendancePresent_rangeAttendanceOutOfBounds_taaExceptionThrown() throws TaaException {
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

        TaaException thrown = Assertions.assertThrows(TaaException.class, () -> {
            String attendanceInput = "set_attendance c/cs2113t s/3-10 l/1 p/1";
            Command command6 = Parser.parseUserInput(attendanceInput);
            command6.parseArgument();
            command6.checkArgument();
            command6.execute(classList, ui, storage);
        }, "TaaException error was expected");
        Assertions.assertEquals("Invalid student index.",
                thrown.getMessage());
    }

    @Test
    void setAttendancePresent_selectionAttendanceOutOfBounds_taaExceptionThrown() throws TaaException {
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

        TaaException thrown = Assertions.assertThrows(TaaException.class, () -> {
            String attendanceInput = "set_attendance c/cs2113t s/2,3,5 l/1 p/1";
            Command command6 = Parser.parseUserInput(attendanceInput);
            command6.parseArgument();
            command6.checkArgument();
            command6.execute(classList, ui, storage);
        }, "TaaException error was expected");
        Assertions.assertEquals("Invalid student index.",
                thrown.getMessage());
    }

    @Test
    void setAttendancePresent_invalidRangeFormat1_taaExceptionThrown() throws TaaException {
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
            String attendanceInput = "set_attendance c/cs2113t s/1---3 l/1 p/1";
            Command command3 = Parser.parseUserInput(attendanceInput);
            command3.parseArgument();
            command3.checkArgument();
            command3.execute(classList, ui, storage);
        }, "TaaException error was expected");
        Assertions.assertEquals("Invalid range format.",
                thrown.getMessage());
    }

    @Test
    void setAttendancePresent_invalidRangeFormat2_taaExceptionThrown() throws TaaException {
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
            String attendanceInput = "set_attendance c/cs2113t s/1-2,3 l/1 p/1";
            Command command3 = Parser.parseUserInput(attendanceInput);
            command3.parseArgument();
            command3.checkArgument();
            command3.execute(classList, ui, storage);
        }, "TaaException error was expected");
        Assertions.assertEquals("Invalid range format.",
                thrown.getMessage());
    }

    @Test
    void setAttendancePresent_invalidRangeFormat3_taaExceptionThrown() throws TaaException {
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
            String attendanceInput = "set_attendance c/cs2113t s/1-2-3 l/1 p/1";
            Command command3 = Parser.parseUserInput(attendanceInput);
            command3.parseArgument();
            command3.checkArgument();
            command3.execute(classList, ui, storage);
        }, "TaaException error was expected");
        Assertions.assertEquals("Invalid range format.",
                thrown.getMessage());
    }

    @Test
    void setAttendancePresent_invalidRangeFormat4_taaExceptionThrown() throws TaaException {
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
            String attendanceInput = "set_attendance c/cs2113t s/1--3 l/1 p/1";
            Command command3 = Parser.parseUserInput(attendanceInput);
            command3.parseArgument();
            command3.checkArgument();
            command3.execute(classList, ui, storage);
        }, "TaaException error was expected");
        Assertions.assertEquals("Invalid range format.",
                thrown.getMessage());
    }

    @Test
    void setAttendancePresent_invalidSelectionFormat1_taaExceptionThrown() throws TaaException {
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
            String attendanceInput = "set_attendance c/cs2113t s/1,,3 l/1 p/1";
            Command command3 = Parser.parseUserInput(attendanceInput);
            command3.parseArgument();
            command3.checkArgument();
            command3.execute(classList, ui, storage);
        }, "TaaException error was expected");
        Assertions.assertEquals("Invalid format for selected indexes.",
                thrown.getMessage());
    }

    @Test
    void setAttendancePresent_invalidSelectionFormat2_taaExceptionThrown() throws TaaException {
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
            String attendanceInput = "set_attendance c/cs2113t s/1, 3 l/1 p/1";
            Command command3 = Parser.parseUserInput(attendanceInput);
            command3.parseArgument();
            command3.checkArgument();
            command3.execute(classList, ui, storage);
        }, "TaaException error was expected");
        Assertions.assertEquals("Invalid format for selected indexes.",
                thrown.getMessage());
    }
}
