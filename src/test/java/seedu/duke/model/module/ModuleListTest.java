package seedu.duke.model.module;

import org.junit.jupiter.api.Test;
import seedu.duke.DukeException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class ModuleListTest {
    @Test
    public void isEmpty_emptyList_true() {
        assertTrue(new ModuleList().isEmpty());
    }

    @Test
    public void isEmpty_nonEmptyList_false() {
        try {
            ModuleList moduleList = new ModuleList();
            moduleList.addModule(new Module("CS2105", "Introduction to Computer Networks", "4 MC"));
            moduleList.addModule(new Module("CS2106", "Introduction to Operating Systems", "4 MC"));
            assertEquals(2, moduleList.getSize());
            moduleList.deleteModule(1);
            assertEquals(1, moduleList.getSize());
            moduleList.deleteAllModules();
            assertEquals(0, moduleList.getSize());
        } catch (DukeException e) {
            fail(); // fail if there are any errors while manipulating the list of modules
        }
    }

    @Test
    public void deleteModule_indexOutOfBounds_exceptionThrown() {
        assertThrows(DukeException.class, () -> {
            ModuleList moduleList = new ModuleList();
            moduleList.addModule(new Module("CS3244", "Machine Learning", "4 MC"));
            moduleList.deleteModule(1);
        });
    }

    @Test
    public void deleteModule_negativeIndex_exceptionThrown() {
        assertThrows(DukeException.class, () -> {
            new ModuleList().deleteModule(-1);
        });
    }
}
