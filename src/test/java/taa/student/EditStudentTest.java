package taa.student;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import taa.Parser;
import taa.Ui;
import taa.command.Command;
import taa.exception.TaaException;
import taa.storage.Storage;
import taa.teachingclass.ClassList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EditStudentTest {
    @Test
    void editStudent_userEditsThreeTimes_editStudentCorrectly() throws TaaException {
        Ui ui = new Ui();
        Storage storage = new Storage("./data/taa_data.json");
        ClassList classList = new ClassList();
        String classInput = "add_class i/cs2102";
        Command command1 = Parser.parseUserInput(classInput);
        command1.parseArgument();
        command1.checkArgument();
        command1.execute(classList, ui, storage);

        String studentInput = "add_student c/cs2102 i/ABC123 n/Jon Lin";
        Command command2 = Parser.parseUserInput(studentInput);
        command2.parseArgument();
        command2.checkArgument();
        command2.execute(classList, ui, storage);

        String editStudentInput1 = "edit_student c/cs2102 s/1 i/555 n/Jon Lim";
        Command command3 = Parser.parseUserInput(editStudentInput1);
        command3.parseArgument();
        command3.checkArgument();
        command3.execute(classList, ui, storage);

        String editStudentInput2 = "edit_student c/cs2102 s/1 i/09123 n/Jonny Lim";
        Command command4 = Parser.parseUserInput(editStudentInput2);
        command4.parseArgument();
        command4.checkArgument();
        command4.execute(classList, ui, storage);

        String editStudentInput3 = "edit_student c/cs2102 s/1 i/a609123x n/Jonny Limbs";
        Command command5 = Parser.parseUserInput(editStudentInput3);
        command5.parseArgument();
        command5.checkArgument();
        command5.execute(classList, ui, storage);

        assertEquals("A609123X", classList.getClassWithId("cs2102").getStudentList().getStudentAt(0)
                .getId());
        assertEquals("Jonny Limbs", classList.getClassWithId("cs2102").getStudentList().getStudentAt(0)
                .getName());
    }

    @Test
    void editStudent_userAddSameStudentID_taaExceptionThrown() throws TaaException {
        Ui ui = new Ui();
        Storage storage = new Storage("./data/taa_data.json");
        ClassList classList = new ClassList();
        String classInput = "add_class i/cs2102";
        Command command1 = Parser.parseUserInput(classInput);
        command1.parseArgument();
        command1.checkArgument();
        command1.execute(classList, ui, storage);

        String studentInput = "add_student c/cs2102 i/12345678 n/Jon Lin";
        Command command2 = Parser.parseUserInput(studentInput);
        command2.parseArgument();
        command2.checkArgument();
        command2.execute(classList, ui, storage);

        String studentInput2 = "add_student c/cs2102 i/a123b n/Tim Ho";
        Command command3 = Parser.parseUserInput(studentInput2);
        command3.parseArgument();
        command3.checkArgument();
        command3.execute(classList, ui, storage);

        TaaException thrown = Assertions.assertThrows(TaaException.class, () -> {
            String editStudentInput = "edit_student c/cs2102 s/1 i/A123b n/Jon Lim";
            Command command4 = Parser.parseUserInput(editStudentInput);
            command4.parseArgument();
            command4.checkArgument();
            command4.execute(classList, ui, storage);
        }, "TaaException error was expected");

        Assertions.assertEquals("A student with that ID already exists", thrown.getMessage());
    }

    @Test
    void addStudent_classDoesNotExist_taaExceptionThrown() throws TaaException {
        Ui ui = new Ui();
        Storage storage = new Storage("./data/taa_data.json");
        ClassList classList = new ClassList();
        String classInput = "add_class i/cs2102";
        Command command1 = Parser.parseUserInput(classInput);
        command1.parseArgument();
        command1.checkArgument();
        command1.execute(classList, ui, storage);

        String studentInput = "add_student c/cs2102 i/12345123678 n/Jon Lin";
        Command command2 = Parser.parseUserInput(studentInput);
        command2.parseArgument();
        command2.checkArgument();
        command2.execute(classList, ui, storage);

        TaaException thrown = Assertions.assertThrows(TaaException.class, () -> {
            String editStudentInput = "edit_student c/cs2112 s/1 i/A1234567B n/Jon Lim";
            Command command3 = Parser.parseUserInput(editStudentInput);
            command3.parseArgument();
            command3.checkArgument();
            command3.execute(classList, ui, storage);
        }, "TaaException error was expected");
        Assertions.assertEquals("Class not found.", thrown.getMessage());
    }

    @Test
    void editStudent_studentIndexDoesNotExist_taaExceptionThrown() throws TaaException {
        Ui ui = new Ui();
        Storage storage = new Storage("./data/taa_data.json");
        ClassList classList = new ClassList();
        String classInput = "add_class i/cs2102";
        Command command1 = Parser.parseUserInput(classInput);
        command1.parseArgument();
        command1.checkArgument();
        command1.execute(classList, ui, storage);

        String studentInput = "add_student c/cs2102 i/12312345678 n/Jon Lin";
        Command command2 = Parser.parseUserInput(studentInput);
        command2.parseArgument();
        command2.checkArgument();
        command2.execute(classList, ui, storage);

        String studentInput2 = "add_student c/cs2102 i/a121233b n/Tim Ho";
        Command command3 = Parser.parseUserInput(studentInput2);
        command3.parseArgument();
        command3.checkArgument();
        command3.execute(classList, ui, storage);

        TaaException thrown = Assertions.assertThrows(TaaException.class, () -> {
            String editStudentInput = "edit_student c/cs2102 s/5 i/A123b n/Jon Lim";
            Command command4 = Parser.parseUserInput(editStudentInput);
            command4.parseArgument();
            command4.checkArgument();
            command4.execute(classList, ui, storage);
        }, "TaaException error was expected");
        Assertions.assertEquals("Invalid student index.", thrown.getMessage());
    }

}