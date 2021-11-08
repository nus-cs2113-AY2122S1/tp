package taa.comment;

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

public class SetCommentTest {
    @Test
    void setComment_userSetsTwoComments_setCommentCorrectly() throws TaaException {
        Ui ui = new Ui();
        Storage storage = new Storage(null);
        ClassList classList = new ClassList();
        String classInput = "add_class i/cs2102";
        Command command1 = Parser.parseUserInput(classInput);
        command1.parseArgument();
        command1.checkArgument();
        command1.execute(classList, ui, storage);

        String studentInput1 = "add_student c/cs2102 i/12345678 n/Jon Lin";
        Command command2 = Parser.parseUserInput(studentInput1);
        command2.parseArgument();
        command2.checkArgument();
        command2.execute(classList, ui, storage);

        String commentInput1 = "add_student c/cs2102 i/a123b n/Tim Ho";
        Command command3 = Parser.parseUserInput(commentInput1);
        command3.parseArgument();
        command3.checkArgument();
        command3.execute(classList, ui, storage);

        String commentInput2 = "set_comment c/cs2102 s/1 t/Doing Well";
        Command command4 = Parser.parseUserInput(commentInput2);
        command4.parseArgument();
        command4.checkArgument();
        command4.execute(classList, ui, storage);

        String commentInput3 = "set_comment c/cs2102 s/2 t/Doing poorly";
        Command command5 = Parser.parseUserInput(commentInput3);
        command5.parseArgument();
        command5.checkArgument();
        command5.execute(classList, ui, storage);


        assertEquals("Doing Well", classList.getClassWithId("cs2102").getStudentList().getStudentAt(0)
                .getComment());
        assertEquals("Doing poorly", classList.getClassWithId("cs2102").getStudentList().getStudentAt(1)
                .getComment());
    }

    @Test
    void setComment_userOverwritesComments_setCommentCorrectly() throws TaaException {
        Ui ui = new Ui();
        Storage storage = new Storage(null);
        ClassList classList = new ClassList();
        String classInput = "add_class i/cs2102";
        Command command1 = Parser.parseUserInput(classInput);
        command1.parseArgument();
        command1.checkArgument();
        command1.execute(classList, ui, storage);

        String studentInput1 = "add_student c/cs2102 i/12345678 n/Jon Lin";
        Command command2 = Parser.parseUserInput(studentInput1);
        command2.parseArgument();
        command2.checkArgument();
        command2.execute(classList, ui, storage);

        String commentInput1 = "set_comment c/cs2102 s/1 t/Doing Well";
        Command command3 = Parser.parseUserInput(commentInput1);
        command3.parseArgument();
        command3.checkArgument();
        command3.execute(classList, ui, storage);

        String commentInput2 = "set_comment c/cs2102 s/1 t/Doing poorly";
        Command command4 = Parser.parseUserInput(commentInput2);
        command4.parseArgument();
        command4.checkArgument();
        command4.execute(classList, ui, storage);

        assertEquals("Doing poorly", classList.getClassWithId("cs2102").getStudentList().getStudentAt(0)
                .getComment());
    }

    @Test
    void setComment_classDoesNotExist_taaExceptionThrown() throws TaaException {
        Ui ui = new Ui();
        Storage storage = new Storage(null);
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
            String commentInput = "set_comment c/cs2112 s/1 t/good";
            Command command3 = Parser.parseUserInput(commentInput);
            command3.parseArgument();
            command3.checkArgument();
            command3.execute(classList, ui, storage);
        }, "TaaException error was expected");
        Assertions.assertEquals("Class not found.", thrown.getMessage());
    }

    @Test
    void setComment_studentIndexDoesNotExist_taaExceptionThrown() throws TaaException {
        Ui ui = new Ui();
        Storage storage = new Storage(null);
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
            String commentInput = "set_comment c/cs2102 s/5 t/good";
            Command command4 = Parser.parseUserInput(commentInput);
            command4.parseArgument();
            command4.checkArgument();
            command4.execute(classList, ui, storage);
        }, "TaaException error was expected");
        Assertions.assertEquals("Invalid student index.", thrown.getMessage());
    }
}