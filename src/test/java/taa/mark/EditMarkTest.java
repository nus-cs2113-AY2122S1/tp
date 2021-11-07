package taa.mark;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import taa.Parser;
import taa.Ui;
import taa.command.Command;
import taa.exception.TaaException;
import taa.storage.Storage;
import taa.teachingclass.ClassList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EditMarkTest {
    @Test
    void editMark_userEditsThreeTimes_marksEditedCorrectly() throws TaaException {
        Ui ui = new Ui();
        Storage storage = new Storage(null);
        ClassList classList = new ClassList();
        String classInput = "add_class i/cs2102";
        Command command1 = Parser.parseUserInput(classInput);
        command1.parseArgument();
        command1.checkArgument();
        command1.execute(classList, ui, storage);

        String assessmentInput = "add_assessment c/cs2102 n/Midterms m/50 w/20";
        Command command2 = Parser.parseUserInput(assessmentInput);
        command2.parseArgument();
        command2.checkArgument();
        command2.execute(classList, ui, storage);

        String studentInput = "add_student c/cs2102 i/12345678 n/Jim Ho";
        Command command3 = Parser.parseUserInput(studentInput);
        command3.parseArgument();
        command3.checkArgument();
        command3.execute(classList, ui, storage);

        String markInput = "set_mark c/cs2102 s/1 a/Midterms m/48";
        Command command4 = Parser.parseUserInput(markInput);
        command4.parseArgument();
        command4.checkArgument();
        command4.execute(classList, ui, storage);

        assertEquals(48.0, classList.getClassWithId("cs2102").getStudentList().getStudentAt(0)
                .getMarks("Midterms"));

        String editMarkInput = "edit_mark c/cs2102 s/1 a/Midterms m/47";
        Command command5 = Parser.parseUserInput(editMarkInput);
        command5.parseArgument();
        command5.checkArgument();
        command5.execute(classList, ui, storage);

        assertEquals(47.0, classList.getClassWithId("cs2102").getStudentList().getStudentAt(0)
                .getMarks("Midterms"));

        String editMarkInput2 = "edit_mark c/cs2102 s/1 a/Midterms m/46";
        Command command6 = Parser.parseUserInput(editMarkInput2);
        command6.parseArgument();
        command6.checkArgument();
        command6.execute(classList, ui, storage);

        assertEquals(46.0, classList.getClassWithId("cs2102").getStudentList().getStudentAt(0)
                .getMarks("Midterms"));

        String editMarkInput3 = "edit_mark c/cs2102 s/1 a/Midterms m/50";
        Command command7 = Parser.parseUserInput(editMarkInput3);
        command7.parseArgument();
        command7.checkArgument();
        command7.execute(classList, ui, storage);

        assertEquals(50.0, classList.getClassWithId("cs2102").getStudentList().getStudentAt(0)
                .getMarks("Midterms"));
    }

    @Test
    void editMark_editingWithoutSettingMarks_taaExceptionThrown() throws TaaException {
        Ui ui = new Ui();
        Storage storage = new Storage(null);
        ClassList classList = new ClassList();
        String classInput = "add_class i/cs2102";
        Command command1 = Parser.parseUserInput(classInput);
        command1.parseArgument();
        command1.checkArgument();
        command1.execute(classList, ui, storage);

        String assessmentInput = "add_assessment c/cs2102 n/Midterms m/50 w/20";
        Command command2 = Parser.parseUserInput(assessmentInput);
        command2.parseArgument();
        command2.checkArgument();
        command2.execute(classList, ui, storage);

        String studentInput = "add_student c/cs2102 i/12345678 n/Jim Ho";
        Command command3 = Parser.parseUserInput(studentInput);
        command3.parseArgument();
        command3.checkArgument();
        command3.execute(classList, ui, storage);

        TaaException thrown = Assertions.assertThrows(TaaException.class, () -> {
            String editMarkInput = "edit_mark c/cs2102 s/1 a/Midterms m/38";
            Command command5 = Parser.parseUserInput(editMarkInput);
            command5.parseArgument();
            command5.checkArgument();
            command5.execute(classList, ui, storage);
        }, "TaaException error was expected");
        Assertions.assertEquals("This student has not been marked yet.", thrown.getMessage());
    }

    @Test
    void editMark_marksExceedMaximumMarks_taaExceptionThrown() throws TaaException {
        Ui ui = new Ui();
        Storage storage = new Storage(null);
        ClassList classList = new ClassList();
        String classInput = "add_class i/cs2102";
        Command command1 = Parser.parseUserInput(classInput);
        command1.parseArgument();
        command1.checkArgument();
        command1.execute(classList, ui, storage);

        String assessmentInput = "add_assessment c/cs2102 n/Midterms m/50 w/20";
        Command command2 = Parser.parseUserInput(assessmentInput);
        command2.parseArgument();
        command2.checkArgument();
        command2.execute(classList, ui, storage);

        String studentInput = "add_student c/cs2102 i/12345678 n/Jim Ho";
        Command command3 = Parser.parseUserInput(studentInput);
        command3.parseArgument();
        command3.checkArgument();
        command3.execute(classList, ui, storage);

        String markInput = "set_mark c/cs2102 s/1 a/Midterms m/48";
        Command command4 = Parser.parseUserInput(markInput);
        command4.parseArgument();
        command4.checkArgument();
        command4.execute(classList, ui, storage);

        TaaException thrown = Assertions.assertThrows(TaaException.class, () -> {
            String editMarkInput = "edit_mark c/cs2102 s/1 a/Midterms m/100";
            Command command5 = Parser.parseUserInput(editMarkInput);
            command5.parseArgument();
            command5.checkArgument();
            command5.execute(classList, ui, storage);
        }, "TaaException error was expected");
        Assertions.assertEquals("Invalid Marks. Marks must be between 0 and 50.00 (inclusive)",
                thrown.getMessage());
    }

    @Test
    void editMark_studentDoesNotExist_taaExceptionThrown() throws TaaException {
        Ui ui = new Ui();
        Storage storage = new Storage(null);
        ClassList classList = new ClassList();
        String classInput = "add_class i/cs2102";
        Command command1 = Parser.parseUserInput(classInput);
        command1.parseArgument();
        command1.checkArgument();
        command1.execute(classList, ui, storage);

        String assessmentInput = "add_assessment c/cs2102 n/Midterms m/50 w/20";
        Command command2 = Parser.parseUserInput(assessmentInput);
        command2.parseArgument();
        command2.checkArgument();
        command2.execute(classList, ui, storage);

        String studentInput = "add_student c/cs2102 i/12345678 n/Jim Ho";
        Command command3 = Parser.parseUserInput(studentInput);
        command3.parseArgument();
        command3.checkArgument();
        command3.execute(classList, ui, storage);

        String markInput = "set_mark c/cs2102 s/1 a/Midterms m/48";
        Command command4 = Parser.parseUserInput(markInput);
        command4.parseArgument();
        command4.checkArgument();
        command4.execute(classList, ui, storage);

        TaaException thrown = Assertions.assertThrows(TaaException.class, () -> {
            String editMarkInput = "edit_mark c/cs2102 s/2 a/Midterms m/38";
            Command command5 = Parser.parseUserInput(editMarkInput);
            command5.parseArgument();
            command5.checkArgument();
            command5.execute(classList, ui, storage);
        }, "TaaException error was expected");
        Assertions.assertEquals("Invalid student index.", thrown.getMessage());
    }

    @Test
    void editMark_classDoesNotExist_taaExceptionThrown() throws TaaException {
        Ui ui = new Ui();
        Storage storage = new Storage(null);
        ClassList classList = new ClassList();
        String classInput = "add_class i/cs2102";
        Command command1 = Parser.parseUserInput(classInput);
        command1.parseArgument();
        command1.checkArgument();
        command1.execute(classList, ui, storage);

        String assessmentInput = "add_assessment c/cs2102 n/Midterms m/50 w/20";
        Command command2 = Parser.parseUserInput(assessmentInput);
        command2.parseArgument();
        command2.checkArgument();
        command2.execute(classList, ui, storage);

        String studentInput = "add_student c/cs2102 i/12345678 n/Jim Ho";
        Command command3 = Parser.parseUserInput(studentInput);
        command3.parseArgument();
        command3.checkArgument();
        command3.execute(classList, ui, storage);

        String markInput = "set_mark c/cs2102 s/1 a/Midterms m/48";
        Command command4 = Parser.parseUserInput(markInput);
        command4.parseArgument();
        command4.checkArgument();
        command4.execute(classList, ui, storage);

        TaaException thrown = Assertions.assertThrows(TaaException.class, () -> {
            String editMarkInput = "edit_mark c/cs2112 s/1 a/Midterms m/38";
            Command command5 = Parser.parseUserInput(editMarkInput);
            command5.parseArgument();
            command5.checkArgument();
            command5.execute(classList, ui, storage);
        }, "TaaException error was expected");
        Assertions.assertEquals("Class not found.", thrown.getMessage());
    }

    @Test
    void editMark_assessmentDoesNotExist_taaExceptionThrown() throws TaaException {
        Ui ui = new Ui();
        Storage storage = new Storage(null);
        ClassList classList = new ClassList();
        String classInput = "add_class i/cs2102";
        Command command1 = Parser.parseUserInput(classInput);
        command1.parseArgument();
        command1.checkArgument();
        command1.execute(classList, ui, storage);

        String assessmentInput = "add_assessment c/cs2102 n/Midterms m/50 w/20";
        Command command2 = Parser.parseUserInput(assessmentInput);
        command2.parseArgument();
        command2.checkArgument();
        command2.execute(classList, ui, storage);

        String studentInput = "add_student c/cs2102 i/12345678 n/Jim Ho";
        Command command3 = Parser.parseUserInput(studentInput);
        command3.parseArgument();
        command3.checkArgument();
        command3.execute(classList, ui, storage);

        String markInput = "set_mark c/cs2102 s/1 a/Midterms m/48";
        Command command4 = Parser.parseUserInput(markInput);
        command4.parseArgument();
        command4.checkArgument();
        command4.execute(classList, ui, storage);

        TaaException thrown = Assertions.assertThrows(TaaException.class, () -> {
            String editMarkInput = "edit_mark c/cs2102 s/1 a/Middle terms m/38";
            Command command5 = Parser.parseUserInput(editMarkInput);
            command5.parseArgument();
            command5.checkArgument();
            command5.execute(classList, ui, storage);
        }, "TaaException error was expected");
        Assertions.assertEquals("Invalid assessment name.", thrown.getMessage());
    }
}
