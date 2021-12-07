package seedu.duke.module;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author nvbinh15

/**
 * A test class for Module.
 */
class ModuleTest {

    @Test
    void getCode_newModuleSpecifyingCode_specifiedCode() {
        String moduleCode = "CS2113T";
        Module module = new Module(moduleCode);
        assertEquals(moduleCode, module.getCode());
    }

    @Test
    void getName_newModuleSpecifyingCodeAndName_specifiedName() {
        String moduleCode = "CS2113T";
        String moduleName = "Software Engineering & Object-Oriented Programming";
        Module module = new Module(moduleCode, moduleName);
        assertEquals(moduleName, module.getName());
    }

    @Test
    void getModularCredits_newModuleSpecifyingCodeNameAndMc_specifiedMc() {
        String moduleCode = "CS2113T";
        String moduleName = "Software Engineering & Object-Oriented Programming";
        int modularCredits = 4;
        Module module = new Module(moduleCode, moduleName, modularCredits);
        assertEquals(modularCredits, module.getModularCredits());
    }

    @Test
    void getExpectedGrade_newModuleSpecifyingCodeNameMcAndGrade_specifiedGrade() {
        String moduleCode = "CS2113T";
        String moduleName = "Software Engineering & Object-Oriented Programming";
        int modularCredits = 4;
        String expectedGrade = "A";
        Module module = new Module(moduleCode, moduleName, modularCredits, expectedGrade);
        assertEquals(expectedGrade, module.getExpectedGrade());
    }

    @Test
    void setCode_newModuleChangeToNewCode_newCode() {
        String moduleCode = "CS2113T";
        Module module = new Module(moduleCode);
        String newModuleCode = "CS2113";
        module.setCode(newModuleCode);
        assertEquals(newModuleCode, module.getCode());
    }

    @Test
    void setName_newModuleChangeToNewName_newName() {
        String moduleCode = "CS2113T";
        String moduleName = "Software Engineering & Object-Oriented Programming";
        Module module = new Module(moduleCode, moduleName);
        String newModuleName = "Software Engineering and Object-Oriented Programming";
        module.setName(newModuleName);
        assertEquals(newModuleName, module.getName());
    }

    @Test
    void setExpectedGrade_newModuleChangeToNewGrade_newGrade() {
        String moduleCode = "CS2113T";
        String moduleName = "Software Engineering & Object-Oriented Programming";
        int modularCredits = 4;
        String expectedGrade = "A";
        Module module = new Module(moduleCode, moduleName, modularCredits, expectedGrade);
        String newExpectedGrade = "A-";
        module.setExpectedGrade(newExpectedGrade);
        assertEquals(newExpectedGrade, module.getExpectedGrade());
    }

    @Test
    void setModularCredits_newModuleChangeToNewMc_newMc() {
        String moduleCode = "CS2113T";
        String moduleName = "Software Engineering & Object-Oriented Programming";
        int modularCredits = 3;
        Module module = new Module(moduleCode, moduleName, modularCredits);
        int newModularCredits = 4;
        module.setModularCredits(newModularCredits);
        assertEquals(newModularCredits, module.getModularCredits());
    }

    @Test
    void testToString() {
        String moduleCode = "CS2113T";
        String moduleName = "Software Engineering & Object-Oriented Programming";
        int modularCredits = 4;
        String expectedGrade = "A";
        Module module = new Module(moduleCode, moduleName, modularCredits, expectedGrade);
        String expectedString = "CS2113T | Software Engineering & Object-Oriented Programming | MC: 4"
                + " | Expected grade: A";
        assertEquals(expectedString, module.toString());
    }


}
