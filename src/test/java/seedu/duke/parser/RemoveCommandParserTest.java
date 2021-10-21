package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.commands.RemoveModCommand;
import seedu.duke.commands.RemoveUniCommand;
import seedu.duke.modules.ModuleList;
import seedu.duke.storage.ModuleStorage;
import seedu.duke.storage.UniversityStorage;
import seedu.duke.universities.UniversityList;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class RemoveCommandParserTest {

    private static UniversityList universitySelectedList = new UniversityList();
    private static ModuleList moduleSelectedList = new ModuleList();
    private static UniversityList universityMasterList;
    private static ModuleList moduleMasterList;

    @Test void parse_universityFlagWithValidUniversity_success() {
        String arguments = "/u someUniversityName";
        RemoveCommandParser rcp = new RemoveCommandParser();
        try {
            moduleMasterList = new ModuleList(ModuleStorage.load());
            universityMasterList = new UniversityList(UniversityStorage.load());
            assertEquals(RemoveUniCommand.class, rcp.parse(arguments, universityMasterList, moduleMasterList,
                    universitySelectedList, moduleSelectedList));
        } catch (Exception e) {
            assertEquals("Incorrect flags passed.", e.getMessage());
        }
    }

    @Test void parse_moduleFlagWithValidModule_success() {
        String arguments = "/m someModule";
        RemoveCommandParser rcp = new RemoveCommandParser();
        try {
            assertEquals(RemoveModCommand.class, rcp.parse(arguments, universityMasterList, moduleMasterList,
                    universitySelectedList, moduleSelectedList));
        } catch (Exception e) {
            assertEquals("Incorrect flags passed.", e.getMessage());
        }
    }

    @Test void parse_moduleFlagWithNoModuleCode_exceptionThrown() {
        String arguments = "/m";
        RemoveCommandParser rcp = new RemoveCommandParser();
        assertThrows(ParseException.class, () -> rcp.parse(arguments, universityMasterList, moduleMasterList,
                universitySelectedList, moduleSelectedList));
    }

    @Test void parse_universityFlagWithNoUniversityName_exceptionThrown() {
        String arguments = "/u";
        RemoveCommandParser rcp = new RemoveCommandParser();
        assertThrows(ParseException.class, () -> rcp.parse(arguments, universityMasterList, moduleMasterList,
                universitySelectedList, moduleSelectedList));
    }

    @Test
    public void parse_noArguments_exceptionThrown() {
        String arguments = "";
        RemoveCommandParser rcp = new RemoveCommandParser();
        assertThrows(ParseException.class, () -> rcp.parse(arguments, universityMasterList, moduleMasterList,
                universitySelectedList, moduleSelectedList));
    }
}