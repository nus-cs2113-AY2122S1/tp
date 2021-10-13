package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.commands.RemoveCommand;
import seedu.duke.modules.ModuleList;
import seedu.duke.universities.UniversityList;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class RemoveCommandParserTest {

    private static UniversityList universitySelectedList = new UniversityList();
    private static ModuleList moduleSelectedList = new ModuleList();

    @Test void parse_universityFlagWithValidUniversity_success() {
        String arguments = "/u someUniversityName";
        RemoveCommandParser rcp = new RemoveCommandParser();
        try {
            assertEquals(RemoveCommand.class, rcp.parse(arguments, universitySelectedList, moduleSelectedList));
        } catch (Exception e) {
            assertEquals("university not in list", e.getMessage());
        }
    }

    @Test void parse_moduleFlagWithValidModule_success() {
        String arguments = "/m someModule";
        RemoveCommandParser rcp = new RemoveCommandParser();
        try {
            assertEquals(RemoveCommand.class, rcp.parse(arguments, universitySelectedList, moduleSelectedList));
        } catch (Exception e) {
            assertEquals("module does not exist", e.getMessage());
        }
    }

    @Test void parse_moduleFlagWithNoModuleCode_exceptionThrown() {
        String arguments = "/m";
        RemoveCommandParser rcp = new RemoveCommandParser();
        assertThrows(ParseException.class, () -> rcp.parse(arguments, universitySelectedList, moduleSelectedList));
    }

    @Test void parse_universityFlagWithNoUniversityName_exceptionThrown() {
        String arguments = "/u";
        RemoveCommandParser rcp = new RemoveCommandParser();
        assertThrows(ParseException.class, () -> rcp.parse(arguments, universitySelectedList, moduleSelectedList));
    }

    @Test
    public void parse_noArguments_exceptionThrown() {
        String arguments = "";
        RemoveCommandParser rcp = new RemoveCommandParser();
        assertThrows(ParseException.class, () -> rcp.parse(arguments, universitySelectedList, moduleSelectedList));
    }
}