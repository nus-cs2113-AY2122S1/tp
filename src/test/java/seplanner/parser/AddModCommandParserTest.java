package seplanner.parser;

import org.junit.jupiter.api.Test;
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
        AddCommandParser acp = new AddCommandParser();
        assertEquals("CS1231", acp.searchForModule(moduleCode, moduleMasterList).getModuleCode());
        assertEquals("Discrete Structures", acp.searchForModule(moduleCode, moduleMasterList).getModuleName());
        assertEquals(4.0, acp.searchForModule(moduleCode, moduleMasterList).getModuleCredits());
    }

    @Test
    void parse_invalidModuleCode_expectException() throws IOException {
        String moduleCode = "CS1011";
        AddCommandParser acp = new AddCommandParser();
        assertThrows(ParseException.class, () -> acp.parse(moduleCode, universityMasterList,
                moduleMasterList, universitySelectedList, moduleSelectedList));
    }

    @Test
    void parse_nullInput_expectException() throws IOException {
        String moduleCode = "";
        AddCommandParser acp = new AddCommandParser();
        assertThrows(ParseException.class, () -> acp.parse(moduleCode, universityMasterList,
                moduleMasterList, universitySelectedList, moduleSelectedList));
    }
}