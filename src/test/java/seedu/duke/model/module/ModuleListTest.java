package seedu.duke.model.module;

import org.junit.jupiter.api.Test;
import seedu.duke.DukeException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

//@@author richwill28
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
            moduleList.deleteModule("CS2105");
            assertEquals(1, moduleList.getSize());
            moduleList.deleteAllModules();
            assertEquals(0, moduleList.getSize());
        } catch (DukeException e) {
            fail(); // fail if there are any errors while manipulating the list of modules
        }
    }

    @Test
    public void deleteModule_emptyList_exceptionThrown() {
        assertThrows(DukeException.class, () -> {
            ModuleList moduleList = new ModuleList();
            moduleList.addModule(new Module("CS3244", "Machine Learning", "4 MC"));
            moduleList.deleteModule("CS1010");
        });
    }

    //@@author rebchua39
    @Test
    public void calculateCap_gradeWithValidGradePoint_expectedCap() {
        try {
            ModuleList moduleList = new ModuleList();
            Module module1 = new Module("CS2105", "Introduction to Computer Networks", "4");
            Module module2 = new Module("CS2106", "Introduction to Operating Systems", "4");
            moduleList.addModule(module1);
            moduleList.addModule(module2);
            module1.setGrade("A");
            module2.setGrade("B");
            assertEquals(4.25, moduleList.calculateCap());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void calculateCap_gradeWithoutValidGradePoint_defaultCap() {
        try {
            ModuleList moduleList = new ModuleList();
            Module module1 = new Module("CS2105", "Introduction to Computer Networks", "4");
            Module module2 = new Module("CS2106", "Introduction to Operating Systems", "4");
            moduleList.addModule(module1);
            moduleList.addModule(module2);
            module1.setGrade("S");
            module2.setGrade("NONE");
            assertEquals(-1, moduleList.calculateCap());
        } catch (DukeException e) {
            fail();
        }
    }

    //@@author Roycius
    @Test
    public void calculateTotalMcs_emptyList_zeroTotalMcs() {
        ModuleList moduleList = new ModuleList();
        assertEquals(0, moduleList.getTotalMC());
    }

    @Test
    public void calculateTotalMcs_nonEmptyList_correctTotalMcs() {
        try {
            ModuleList moduleList = new ModuleList();
            Module module1 = new Module("CS2105", "Introduction to Computer Networks", "4");
            Module module2 = new Module("CS2106", "Introduction to Operating Systems", "4");
            moduleList.addModule(module1);
            moduleList.addModule(module2);
            assertEquals(8, moduleList.getTotalMC());
        } catch (DukeException e) {
            fail();
        }
    }
}
