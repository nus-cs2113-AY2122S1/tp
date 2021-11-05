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

public class SetMarkTest {
    @Test
    void setMark_userMarkTwoStudents_marksSetCorrectly() throws TaaException {
        Ui ui = new Ui();
        Storage storage = new Storage("./data/taa_data.json");
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

        String studentInput2 = "add_student c/cs2102 i/12345677 n/Tim Ho";
        Command command4 = Parser.parseUserInput(studentInput2);
        command4.parseArgument();
        command4.checkArgument();
        command4.execute(classList, ui, storage);

        String markInput = "set_mark c/cs2102 s/1 a/Midterms m/48";
        Command command5 = Parser.parseUserInput(markInput);
        command5.parseArgument();
        command5.checkArgument();
        command5.execute(classList, ui, storage);

        String markInput2 = "set_mark c/cs2102 s/2 a/Midterms m/12";
        Command command6 = Parser.parseUserInput(markInput2);
        command6.parseArgument();
        command6.checkArgument();
        command6.execute(classList, ui, storage);

        assertEquals(48.00, classList.getClassWithId("cs2102").getStudentList().getStudentAt(0)
                .getMarks("Midterms"));
        assertEquals(12.00, classList.getClassWithId("cs2102").getStudentList().getStudentAt(1)
                .getMarks("Midterms"));
    }

    @Test
    void setMark_marksExceedMaximumMarks_taaExceptionThrown() throws TaaException {
        Ui ui = new Ui();
        Storage storage = new Storage("./data/taa_data.json");
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
            String markInput = "set_mark c/cs2102 s/1 a/Midterms m/51";
            Command command4 = Parser.parseUserInput(markInput);
            command4.parseArgument();
            command4.checkArgument();
            command4.execute(classList, ui, storage);
        }, "TaaException error was expected");
        Assertions.assertEquals("Invalid Marks. Marks must be between 0 and 50.00 (inclusive)",
                thrown.getMessage());
    }

    @Test
    void setMark_studentDoesNotExist_taaExceptionThrown() throws TaaException {
        Ui ui = new Ui();
        Storage storage = new Storage("./data/taa_data.json");
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
            String markInput = "set_mark c/cs2102 s/2 a/Midterms m/48";
            Command command4 = Parser.parseUserInput(markInput);
            command4.parseArgument();
            command4.checkArgument();
            command4.execute(classList, ui, storage);
        }, "TaaException error was expected");
        Assertions.assertEquals("Invalid student index.", thrown.getMessage());
    }

    @Test
    void setMark_classDoesNotExist_taaExceptionThrown() throws TaaException {
        Ui ui = new Ui();
        Storage storage = new Storage("./data/taa_data.json");
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
            String markInput = "set_mark c/cs2112 s/1 a/Midterms m/48";
            Command command4 = Parser.parseUserInput(markInput);
            command4.parseArgument();
            command4.checkArgument();
            command4.execute(classList, ui, storage);
        }, "TaaException error was expected");
        Assertions.assertEquals("Class not found.", thrown.getMessage());
    }

    @Test
    void setMark_assessmentDoesNotExist_taaExceptionThrown() throws TaaException {
        Ui ui = new Ui();
        Storage storage = new Storage("./data/taa_data.json");
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
            String markInput = "set_mark c/cs2102 s/1 a/Middle terms m/48";
            Command command4 = Parser.parseUserInput(markInput);
            command4.parseArgument();
            command4.checkArgument();
            command4.execute(classList, ui, storage);
        }, "TaaException error was expected");
        Assertions.assertEquals("Invalid assessment name.", thrown.getMessage());
    }

    @Test
    void setMark_doubleMarking_taaExceptionThrown() throws TaaException {
        Ui ui = new Ui();
        Storage storage = new Storage("./data/taa_data.json");
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
            String markInput2 = "set_mark c/cs2102 s/1 a/Midterms m/0";
            Command command5 = Parser.parseUserInput(markInput2);
            command5.parseArgument();
            command5.checkArgument();
            command5.execute(classList, ui, storage);
        }, "TaaException error was expected");
        Assertions.assertEquals("This student has already been marked.", thrown.getMessage());
    }
}
