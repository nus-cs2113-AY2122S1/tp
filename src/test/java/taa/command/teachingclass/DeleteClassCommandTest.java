package taa.command.teachingclass;

import org.junit.jupiter.api.Test;
import taa.Ui;
import taa.command.Command;
import taa.exception.TaaException;
import taa.storage.Storage;
import taa.teachingclass.ClassList;
import taa.teachingclass.TeachingClass;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DeleteClassCommandTest {
    @Test
    void deleteClass_noArgument_expectTaaException() throws TaaException {
        String argument = "";
        Command command = new DeleteClassCommand(argument);
        command.parseArgument();

        assertThrows(TaaException.class, command::checkArgument);
    }

    @Test
    void deleteClass_emptyId_expectTaaException() throws TaaException {
        String argument = "c/";
        Command command = new DeleteClassCommand(argument);
        command.parseArgument();

        assertThrows(TaaException.class, command::checkArgument);
    }

    @Test
    void editClass_nonExistingId_expectTaaException() throws TaaException {
        String argument = "c/cs2113t";
        Command command = new DeleteClassCommand(argument);
        command.parseArgument();
        command.checkArgument();

        ClassList classList = new ClassList();
        Ui ui = new Ui();
        Storage storage = new Storage(null);
        assertThrows(TaaException.class, () -> {
            command.execute(classList, ui, storage);
        });
    }

    @Test
    void editClass_existingId_expectTaaException() throws TaaException {
        String argument = "c/CS2113t";
        Command command = new DeleteClassCommand(argument);
        command.parseArgument();
        command.checkArgument();

        ClassList classList = new ClassList();
        TeachingClass teachingClass = new TeachingClass("cs2113t", "");
        classList.addClass(teachingClass);
        boolean hasExistPreviously = classList.getClassWithId("cs2113t") != null;

        Ui ui = new Ui();
        Storage storage = new Storage(null);
        command.execute(classList, ui, storage);

        boolean isExistAfterExecute = classList.getClassWithId("cs2113t") != null;
        assertTrue(hasExistPreviously && !isExistAfterExecute);
    }
}