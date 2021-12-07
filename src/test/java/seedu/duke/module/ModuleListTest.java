package seedu.duke.module;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@@author nvbinh15

/**
 * A test class for ModuleList.
 */
class ModuleListTest {

    ModuleList moduleList;

    @BeforeEach
    void setUpModuleListTest() {
        moduleList = new ModuleList();
    }

    @Test
    void getNumberOfModules_newModuleList_zero() {
        assertEquals(moduleList.getNumberOfModules(), 0);
    }

    @Test
    void addModule_addSampleModuleToModuleList_moduleListContainsSampleModule() {
        Module sampleModule = new Module("CS2113T", "Software Engineering", 4, "A");
        moduleList.addModule(sampleModule);
        assertTrue(moduleList.getModules().contains(sampleModule));
    }

    @Test
    void getModuleByIndex_addSampleModuleToModuleList_sampleModuleHasIndexZero() {
        Module sampleModule = new Module("CS2113T", "Software Engineering", 4, "A");
        moduleList.addModule(sampleModule);
        assertEquals(moduleList.getModuleByIndex(0), sampleModule);
    }

    @Test
    void removeModuleByIndex_addAndRemoveSampleModule_ModuleListNotContainSampleModule() {
        Module sampleModule = new Module("CS2113T", "Software Engineering", 4, "A");
        moduleList.addModule(sampleModule);
        moduleList.removeModuleByIndex(0);
        assertFalse(moduleList.getModules().contains(sampleModule));
    }

    @Test
    void exist_duplicateModules() {
        Module sampleModule = new Module("CS2113T", "Software Engineering", 4, "A");
        Module moduleWithExistedCode = new Module("CS2113T");
        moduleList.addModule(sampleModule);
        assertTrue(moduleList.exist(moduleWithExistedCode));
    }
}