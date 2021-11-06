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

public class DeleteMarkTest {
    @Test
    void deleteMark_userDeletesTwoOutOfThreeMarks_marksDeletedCorrectly() throws TaaException {
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

        String assessmentInput2 = "add_assessment c/cs2102 n/Assignment 1 m/30 w/20";
        Command command3 = Parser.parseUserInput(assessmentInput2);
        command3.parseArgument();
        command3.checkArgument();
        command3.execute(classList, ui, storage);

        String assessmentInput3 = "add_assessment c/cs2102 n/Project m/20 w/60";
        Command command4 = Parser.parseUserInput(assessmentInput3);
        command4.parseArgument();
        command4.checkArgument();
        command4.execute(classList, ui, storage);

        String studentInput = "add_student c/cs2102 i/12345678 n/Jim Ho";
        Command command5 = Parser.parseUserInput(studentInput);
        command5.parseArgument();
        command5.checkArgument();
        command5.execute(classList, ui, storage);

        String markInput = "set_mark c/cs2102 s/1 a/Midterms m/48";
        Command command6 = Parser.parseUserInput(markInput);
        command6.parseArgument();
        command6.checkArgument();
        command6.execute(classList, ui, storage);

        assertEquals(48.0, classList.getClassWithId("cs2102").getStudentList().getStudentAt(0)
                .getMarks("Midterms"));

        String markInput2 = "set_mark c/cs2102 s/1 a/Assignment 1 m/27";
        Command command7 = Parser.parseUserInput(markInput2);
        command7.parseArgument();
        command7.checkArgument();
        command7.execute(classList, ui, storage);

        assertEquals(27.0, classList.getClassWithId("cs2102").getStudentList().getStudentAt(0)
                .getMarks("Assignment 1"));

        String markInput3 = "set_mark c/cs2102 s/1 a/Project m/16";
        Command command8 = Parser.parseUserInput(markInput3);
        command8.parseArgument();
        command8.checkArgument();
        command8.execute(classList, ui, storage);

        assertEquals(16.0, classList.getClassWithId("cs2102").getStudentList().getStudentAt(0)
                .getMarks("Project"));

        String deleteMarkInput = "delete_mark c/cs2102 s/1 a/Midterms";
        Command command9 = Parser.parseUserInput(deleteMarkInput);
        command9.parseArgument();
        command9.checkArgument();
        command9.execute(classList, ui, storage);

        assertEquals(-1, classList.getClassWithId("cs2102").getStudentList().getStudentAt(0)
                .getMarks("Midterms"));

        String deleteMarkInput2 = "delete_mark c/cs2102 s/1 a/Project";
        Command command10 = Parser.parseUserInput(deleteMarkInput2);
        command10.parseArgument();
        command10.checkArgument();
        command10.execute(classList, ui, storage);

        assertEquals(-1, classList.getClassWithId("cs2102").getStudentList().getStudentAt(0)
                .getMarks("Project"));
        assertEquals(27.0, classList.getClassWithId("cs2102").getStudentList().getStudentAt(0)
                .getMarks("Assignment 1"));
    }

    @Test
    void deleteMark_deletingWithoutSettingMarks_taaExceptionThrown() throws TaaException {
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
            String editMarkInput = "delete_mark c/cs2102 s/1 a/Midterms";
            Command command5 = Parser.parseUserInput(editMarkInput);
            command5.parseArgument();
            command5.checkArgument();
            command5.execute(classList, ui, storage);
        }, "TaaException error was expected");
        Assertions.assertEquals("This student has not been marked yet.", thrown.getMessage());
    }

    @Test
    void deleteMark_studentDoesNotExist_taaExceptionThrown() throws TaaException {
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
            String editMarkInput = "delete_mark c/cs2102 s/2 a/Midterms";
            Command command5 = Parser.parseUserInput(editMarkInput);
            command5.parseArgument();
            command5.checkArgument();
            command5.execute(classList, ui, storage);
        }, "TaaException error was expected");
        Assertions.assertEquals("Invalid student index.", thrown.getMessage());
    }

    @Test
    void deleteMark_classDoesNotExist_taaExceptionThrown() throws TaaException {
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
            String editMarkInput = "delete_mark c/cs2112 s/1 a/Midterms";
            Command command5 = Parser.parseUserInput(editMarkInput);
            command5.parseArgument();
            command5.checkArgument();
            command5.execute(classList, ui, storage);
        }, "TaaException error was expected");
        Assertions.assertEquals("Class not found.", thrown.getMessage());
    }

    @Test
    void deleteMark_assessmentDoesNotExist_taaExceptionThrown() throws TaaException {
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
            String editMarkInput = "delete_mark c/cs2102 s/1 a/Middle terms";
            Command command5 = Parser.parseUserInput(editMarkInput);
            command5.parseArgument();
            command5.checkArgument();
            command5.execute(classList, ui, storage);
        }, "TaaException error was expected");
        Assertions.assertEquals("Invalid assessment name.", thrown.getMessage());
    }
}
