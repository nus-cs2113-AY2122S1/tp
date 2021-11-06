package seplanner.parser;

import org.graalvm.compiler.core.common.type.ArithmeticOpTable;
import org.junit.jupiter.api.Test;
import seplanner.exceptions.AddParseException;
import seplanner.modules.Module;
import seplanner.modules.ModuleList;
import seplanner.storage.Storage;
import seplanner.universities.UniversityList;

import java.io.IOException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@@author madhanse
class AddModCommandParserTest {
    private static Storage storage = new Storage();
    private static UniversityList universitySelectedList = new UniversityList();
    private static ModuleList moduleSelectedList = new ModuleList();
    private static UniversityList universityMasterList;
    private static ModuleList moduleMasterList;

    static {
        try {
            moduleMasterList = new ModuleList(storage.readModuleList());
            universityMasterList = new UniversityList(storage.readUniversityList(moduleMasterList));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void parse_validModuleCode_expectModuleObject() {
        String moduleCode = "CS1231";
        assertEquals("CS1231", ParseCondition.isValidModule(moduleMasterList, moduleCode).getModuleCode());
        assertEquals("Discrete Structures", ParseCondition
                .isValidModule(moduleMasterList, moduleCode).getModuleName());
        assertEquals(4.0, ParseCondition.isValidModule(moduleMasterList, moduleCode).getModuleCredits());
    }

    @Test
    void parse_invalidModuleCode_expectException() {
        String moduleCode = "CS1011";
        AddCommandParser acp = new AddCommandParser();
        assertThrows(ParseException.class, () -> acp.parse(moduleCode, universityMasterList,
                moduleMasterList, universitySelectedList, moduleSelectedList));
    }

    @Test
    void parse_nullInput_expectException() {
        String moduleCode = "";
        AddCommandParser acp = new AddCommandParser();
        assertThrows(ParseException.class, () -> acp.parse(moduleCode, universityMasterList,
                moduleMasterList, universitySelectedList, moduleSelectedList));
    }

    @Test
    void parse_moduleIndexExceedLowerBound_exceptionThrown() {
        String input = "0";
        AddCommandParser acp = new AddCommandParser();
        assertThrows(AddParseException.class, () -> acp.handleModFlagArgs(input, moduleMasterList, moduleSelectedList));
    }

    @Test
    void parse_moduleIndexExceedUpperBound_exceptionThrown() {
        String input = "999";
        AddCommandParser acp = new AddCommandParser();
        assertThrows(AddParseException.class, () -> acp.handleModFlagArgs(input, moduleMasterList, moduleSelectedList));
    }

    @Test
    void parse_nonAlphabeticalOrNumericalInput_exceptionThrown() {
        String input = ",.";
        AddCommandParser acp = new AddCommandParser();
        assertThrows(AddParseException.class, () -> acp.handleModFlagArgs(input, moduleMasterList, moduleSelectedList));
    }

    @Test
    void parse_userAddDuplicateModule_exceptionThrown() {
        String input = "CS1231";
        AddCommandParser acp = new AddCommandParser();
        Module dummyModule = new Module("CS1231", "test", 0, 0);
        moduleSelectedList.addModule(dummyModule);
        assertThrows(AddParseException.class, () -> acp.handleModFlagArgs(input,
                moduleMasterList, moduleSelectedList));
    }

}