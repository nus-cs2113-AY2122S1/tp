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

public class AddStudentTest {
    @Test
    void addStudent_userAddTwoStudents_addStudentCorrectly() throws TaaException {
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

        assertEquals("12345678", classList.getClassWithId("cs2102").getStudentList().getStudentAt(0)
                .getId());
        assertEquals("A123B", classList.getClassWithId("cs2102").getStudentList().getStudentAt(1)
                .getId());
        assertEquals("Jon Lin", classList.getClassWithId("cs2102").getStudentList().getStudentAt(0)
                .getName());
        assertEquals("Tim Ho", classList.getClassWithId("cs2102").getStudentList().getStudentAt(1)
                .getName());
    }

    @Test
    void addStudent_userAddSameStudentID_taaExceptionThrown() throws TaaException {
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

        TaaException thrown = Assertions.assertThrows(TaaException.class, () -> {
            String studentInput2 = "add_student c/cs2102 i/12345678 n/Tim Ho";
            Command command3 = Parser.parseUserInput(studentInput2);
            command3.parseArgument();
            command3.checkArgument();
            command3.execute(classList, ui, storage);
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

        TaaException thrown = Assertions.assertThrows(TaaException.class, () -> {
            String studentInput = "add_student c/cs2101 i/12345678 n/Jim Ho";
            Command command2 = Parser.parseUserInput(studentInput);
            command2.parseArgument();
            command2.checkArgument();
            command2.execute(classList, ui, storage);
        }, "TaaException error was expected");
        Assertions.assertEquals("Class not found.", thrown.getMessage());
    }

}
