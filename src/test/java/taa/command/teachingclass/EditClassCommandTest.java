package taa.command.teachingclass;

//@@author leyondlee
import org.junit.jupiter.api.Test;
import taa.Ui;
import taa.command.Command;
import taa.exception.TaaException;
import taa.storage.Storage;
import taa.teachingclass.ClassList;
import taa.teachingclass.TeachingClass;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EditClassCommandTest {
    @Test
    void editClass_noArgument_expectTaaException() throws TaaException {
        String argument = "";
        Command command = new EditClassCommand(argument);
        command.parseArgument();

        assertThrows(TaaException.class, command::checkArgument);
    }

    @Test
    void editClass_noIdAndValidNewId_expectTaaException() throws TaaException {
        String argument = "c/ i/cs2113-f12";
        Command command = new EditClassCommand(argument);
        command.parseArgument();

        assertThrows(TaaException.class, command::checkArgument);
    }

    @Test
    void editClass_nonExistingId_expectTaaException() throws TaaException {
        String argument = "c/cs2113t n/Hello";
        Command command = new EditClassCommand(argument);
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
    void editClass_noOptionalArgument_expectTaaException() throws TaaException {
        String argument = "c/cs2113t";
        Command command = new EditClassCommand(argument);
        command.parseArgument();

        assertThrows(TaaException.class, command::checkArgument);
    }

    @Test
    void editClass_existingIdAndSameNewId_expectNewName() throws TaaException {
        String argument = "c/cs2113t i/cs2113t n/This is a class.";
        Command command = new EditClassCommand(argument);
        command.parseArgument();
        command.checkArgument();

        TeachingClass teachingClass = new TeachingClass("cs2113t", "");
        ClassList classList = new ClassList();
        classList.addClass(teachingClass);
        Ui ui = new Ui();
        Storage storage = new Storage(null);
        command.execute(classList, ui, storage);

        assertEquals(teachingClass.getName(), "This is a class.");
    }

    @Test
    void editClass_existingIdAndNotSame_expectTaaException() throws TaaException {
        String argument = "c/cs2113t-f12 i/cs2113t n/This is a class.";
        Command command = new EditClassCommand(argument);
        command.parseArgument();
        command.checkArgument();


        ClassList classList = new ClassList();
        TeachingClass teachingClass = new TeachingClass("cs2113t", "");
        classList.addClass(teachingClass);
        TeachingClass teachingClass2 = new TeachingClass("cs2113t-f12", "");
        classList.addClass(teachingClass2);

        Ui ui = new Ui();
        Storage storage = new Storage(null);

        assertThrows(TaaException.class, () -> {
            command.execute(classList, ui, storage);
        });
    }

    @Test
    void editClass_nonExistingId_expectNewIdAndName() throws TaaException {
        String argument = "c/cs2113t-f12 i/cs2113t n/This is a class.";
        Command command = new EditClassCommand(argument);
        command.parseArgument();
        command.checkArgument();


        ClassList classList = new ClassList();
        TeachingClass teachingClass = new TeachingClass("cs2113t-f12", "");
        classList.addClass(teachingClass);

        Ui ui = new Ui();
        Storage storage = new Storage(null);
        command.execute(classList, ui, storage);

        boolean hasNewId = teachingClass.getId().equals("CS2113T");
        boolean hasNewName = teachingClass.getName().equals("This is a class.");
        assertTrue(hasNewId && hasNewName);
    }

    @Test
    void editClass_existingIdAndOnlyNewId_expectNoException() throws TaaException {
        String argument = "c/cs2113t-f12 i/cs2113t";
        Command command = new EditClassCommand(argument);
        command.parseArgument();
        command.checkArgument();


        ClassList classList = new ClassList();
        TeachingClass teachingClass = new TeachingClass("cs2113t-f12", "");
        classList.addClass(teachingClass);

        Ui ui = new Ui();
        Storage storage = new Storage(null);
        command.execute(classList, ui, storage);

        assertEquals(teachingClass.getId(), "CS2113T");
    }


    @Test
    void editClass_existingIdAndOnlyNewName_expectNewName() throws TaaException {
        String argument = "c/cs2113t-f12 n/Hello world, (123)";
        Command command = new EditClassCommand(argument);
        command.parseArgument();
        command.checkArgument();


        ClassList classList = new ClassList();
        TeachingClass teachingClass = new TeachingClass("cs2113t-f12", "");
        classList.addClass(teachingClass);

        Ui ui = new Ui();
        Storage storage = new Storage(null);
        command.execute(classList, ui, storage);

        assertEquals(teachingClass.getName(), "Hello world, (123)");
    }

    @Test
    void editClass_existingIdAndEmptyName_expectEmpty() throws TaaException {
        String argument = "c/cs2113t-f12 n/";
        Command command = new EditClassCommand(argument);
        command.parseArgument();
        command.checkArgument();


        ClassList classList = new ClassList();
        TeachingClass teachingClass = new TeachingClass("cs2113t-f12", "This is old name");
        classList.addClass(teachingClass);

        Ui ui = new Ui();
        Storage storage = new Storage(null);
        command.execute(classList, ui, storage);

        assertTrue(teachingClass.getName().isEmpty());
    }

    @Test
    void editClass_existingIdAndInvalidNewId_expectTaaException() throws TaaException {
        String argument = "c/cs2113t-f12 i/cs2113t f12";
        Command command = new EditClassCommand(argument);
        command.parseArgument();

        assertThrows(TaaException.class, command::checkArgument);
    }

    @Test
    void editClass_existingIdAndEmptyNewId_expectTaaException() throws TaaException {
        String argument = "c/cs2113t-f12 i/";
        Command command = new EditClassCommand(argument);
        command.parseArgument();

        assertThrows(TaaException.class, command::checkArgument);
    }

    @Test
    void editClass_emptyIdAndValidNewId_expectTaaException() throws TaaException {
        String argument = "c/ i/cs2113t-f12";
        Command command = new EditClassCommand(argument);
        command.parseArgument();

        assertThrows(TaaException.class, command::checkArgument);
    }

    @Test
    void editClass_noId_expectTaaException() throws TaaException {
        String argument = "i/cs2113t-f12 n/";
        Command command = new EditClassCommand(argument);
        command.parseArgument();

        assertThrows(TaaException.class, command::checkArgument);
    }
}