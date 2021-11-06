package taa.student;

//@@author hozhenhong99
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

public class DeleteStudentTest {
    @Test
    void deleteStudent_userDeletesTwoOutOfThreeStudents_deleteStudentCorrectly() throws TaaException {
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

        String studentInput1 = "add_student c/cs2102 i/ABC12345 n/Jon Linen";
        Command command3 = Parser.parseUserInput(studentInput1);
        command3.parseArgument();
        command3.checkArgument();
        command3.execute(classList, ui, storage);

        String studentInput2 = "add_student c/cs2102 i/543123 n/Jonny Limbs";
        Command command4 = Parser.parseUserInput(studentInput2);
        command4.parseArgument();
        command4.checkArgument();
        command4.execute(classList, ui, storage);

        String deleteStudentInput1 = "delete_student c/cs2102 s/1";
        Command command5 = Parser.parseUserInput(deleteStudentInput1);
        command5.parseArgument();
        command5.checkArgument();
        command5.execute(classList, ui, storage);

        String deleteStudentInput2 = "delete_student c/cs2102 s/1";
        Command command6 = Parser.parseUserInput(deleteStudentInput2);
        command6.parseArgument();
        command6.checkArgument();
        command6.execute(classList, ui, storage);

        assertEquals("543123", classList.getClassWithId("cs2102").getStudentList().getStudentAt(0)
                .getId());
        assertEquals("Jonny Limbs", classList.getClassWithId("cs2102").getStudentList().getStudentAt(0)
                .getName());
    }

    @Test
    void deleteStudent_classDoesNotExist_taaExceptionThrown() throws TaaException {
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
            String deleteStudentInput = "delete_student c/cs2112 s/1";
            Command command3 = Parser.parseUserInput(deleteStudentInput);
            command3.parseArgument();
            command3.checkArgument();
            command3.execute(classList, ui, storage);
        }, "TaaException error was expected");
        Assertions.assertEquals("Class not found.", thrown.getMessage());
    }

    @Test
    void deleteStudent_studentIndexDoesNotExist_taaExceptionThrown() throws TaaException {
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
            String deleteStudentInput = "delete_student c/cs2102 s/5";
            Command command4 = Parser.parseUserInput(deleteStudentInput);
            command4.parseArgument();
            command4.checkArgument();
            command4.execute(classList, ui, storage);
        }, "TaaException error was expected");
        Assertions.assertEquals("Invalid student index.", thrown.getMessage());
    }

}