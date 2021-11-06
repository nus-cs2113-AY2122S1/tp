package taa.command.teachingclass;

//@@author leyondlee
import org.junit.jupiter.api.Test;
import taa.Ui;
import taa.command.Command;
import taa.exception.DuplicatedArgumentException;
import taa.exception.TaaException;
import taa.storage.Storage;
import taa.teachingclass.ClassList;
import taa.teachingclass.TeachingClass;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AddClassCommandTest {
    @Test
    void addClass_oneClass_expectAddToClassList() throws TaaException {
        String argument = "i/cs2113T-f12 n/Class F-12 (AY21_22)";
        Command command = new AddClassCommand(argument);
        command.parseArgument();
        command.checkArgument();

        ClassList classList = new ClassList();
        Ui ui = new Ui();
        Storage storage = new Storage(null);
        command.execute(classList, ui, storage);

        TeachingClass teachingClass = classList.getClassWithId("cs2113t-f12");
        boolean isAdded = teachingClass != null;
        boolean hasId = false;
        boolean hasName = false;
        if (isAdded) {
            hasId = teachingClass.getId().equals("CS2113T-F12");
            hasName = teachingClass.getName().equals("Class F-12 (AY21_22)");
        }

        assertTrue(isAdded && hasId && hasName);
    }

    @Test
    void addClass_duplicateArgument_expectDuplicatedArgumentException() {
        String argument = "i/cs2113T-f12 n/Class F-12 (AY21_22) n/This is second, one";
        Command command = new AddClassCommand(argument);

        assertThrows(DuplicatedArgumentException.class, command::parseArgument);
    }

    @Test
    void addClass_existingClass_expectTaaException() throws TaaException {
        String argument = "i/cs2113T-f12 n/Class F-12 (AY21_22)";
        Command command = new AddClassCommand(argument);
        command.parseArgument();
        command.checkArgument();

        ClassList classList = new ClassList();
        Ui ui = new Ui();
        Storage storage = new Storage(null);
        command.execute(classList, ui, storage);

        String argument2 = "i/cs2113T-f12 n/";
        Command command2 = new AddClassCommand(argument2);
        command2.parseArgument();
        command2.checkArgument();

        assertThrows(TaaException.class, () -> {
            command2.execute(classList, ui, storage);
        });
    }

    @Test
    void addClass_emptyName_expectAddToClassList() throws TaaException {
        String argument = "i/cs2113T-f12 n/";
        Command command = new AddClassCommand(argument);
        command.parseArgument();
        command.checkArgument();

        ClassList classList = new ClassList();
        Ui ui = new Ui();
        Storage storage = new Storage(null);
        command.execute(classList, ui, storage);

        TeachingClass teachingClass = classList.getClassWithId("cs2113t-f12");
        boolean isAdded = teachingClass != null;
        boolean hasId = false;
        boolean hasEmptyName = false;
        if (isAdded) {
            hasId = teachingClass.getId().equals("CS2113T-F12");
            hasEmptyName = teachingClass.getName().isEmpty();
        }

        assertTrue(isAdded && hasId && hasEmptyName);
    }

    @Test
    void addClass_spaceInId_expectTaaException() throws TaaException {
        String argument = "i/cs2113T f12 n/";
        Command command = new AddClassCommand(argument);
        command.parseArgument();

        assertThrows(TaaException.class, command::checkArgument);
    }

    @Test
    void addClass_validCharacters_expectAddToClassList() throws TaaException {
        String argument = "i/cs2113.T-(f_12) n/This, is-nothing";
        Command command = new AddClassCommand(argument);
        command.parseArgument();
        command.checkArgument();

        ClassList classList = new ClassList();
        Ui ui = new Ui();
        Storage storage = new Storage(null);
        command.execute(classList, ui, storage);

        TeachingClass teachingClass = classList.getClassWithId("cs2113.T-(f_12)");
        boolean isAdded = teachingClass != null;
        boolean hasId = false;
        boolean hasName = false;
        if (isAdded) {
            hasId = teachingClass.getId().equals("cs2113.T-(f_12)".toUpperCase());
            hasName = teachingClass.getName().equals("This, is-nothing");
        }

        assertTrue(isAdded && hasId && hasName);
    }

    @Test
    void addClass_invalidCharacter_expectTaaException() {
        String argument = "i/cs2113.T-(f_12) n/This, is\\-nothing";
        Command command = new AddClassCommand(argument);

        assertThrows(TaaException.class, command::parseArgument);
    }

    @Test
    void addClass_noArgument_expectTaaException() throws TaaException {
        String argument = "";
        Command command = new AddClassCommand(argument);
        command.parseArgument();

        assertThrows(TaaException.class, command::checkArgument);
    }

    @Test
    void addClass_withoutId_expectTaaException() throws TaaException {
        String argument = "n/Software Engineering Group F12-3";
        Command command = new AddClassCommand(argument);
        command.parseArgument();

        assertThrows(TaaException.class, command::checkArgument);
    }

    @Test
    void addClass_withoutName_expectAddToClassList() throws TaaException {
        String argument = "i/Cs2113t(f11)";
        Command command = new AddClassCommand(argument);
        command.parseArgument();
        command.checkArgument();

        ClassList classList = new ClassList();
        Ui ui = new Ui();
        Storage storage = new Storage(null);
        command.execute(classList, ui, storage);

        TeachingClass teachingClass = classList.getClassWithId("cs2113t(f11)");
        boolean isAdded = teachingClass != null;
        boolean hasId = false;
        boolean hasEmptyName = false;
        if (isAdded) {
            hasId = teachingClass.getId().equals("CS2113T(F11)");
            hasEmptyName = teachingClass.getName().isEmpty();
        }

        assertTrue(isAdded && hasId && hasEmptyName);
    }
}