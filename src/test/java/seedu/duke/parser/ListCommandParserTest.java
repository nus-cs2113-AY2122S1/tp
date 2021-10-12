package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.commands.ListCommand;
import seedu.duke.modules.Module;
import seedu.duke.storage.Storage;
import seedu.duke.universities.UniversityList;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ListCommandParserTest {

    @Test
    public void test_moduleMasterListFlag_success() throws IOException, ParseException {
        String flag = "/m";
        ListCommandParser lcp = new ListCommandParser();
        ArrayList<Module> moduleMasterList = new ArrayList<>(Storage.loadModules());
        String message = "";
        for (int i = 0; i < moduleMasterList.size(); i++) {
            message += "[" + (i + 1) + "] " + moduleMasterList.get(i).getModuleCode() + System.lineSeparator();
        }
        assertEquals(message, lcp.parse(flag));
    }

    @Test
    public void test_universityMasterListFlag_success() throws IOException, ParseException {
        String flag = "/u";
        ListCommandParser lcp = new ListCommandParser();
        UniversityList universityMasterList = new UniversityList(Storage.loadUniversities());
        String message = "";
        for (int i = 0; i < universityMasterList.getSize(); i++) {
            message += "[" + (i + 1) + "] " + universityMasterList.get(i).getName() + System.lineSeparator();
        }
        assertEquals(message, lcp.parse(flag));
    }

    @Test
    public void test_selectedModuleListFlag_success() {
        String flag = "/t";
        ListCommandParser lcp = new ListCommandParser();

    }

    @Test
    public void test_selectedUniversityListFlag_success() {
        String flag = "/s";
        ListCommandParser lcp = new ListCommandParser();

    }

    @Test
    public void test_emptyFlag_exceptionThrown() {
        String flag = "";
        ListCommandParser lcp = new ListCommandParser();
        assertThrows(ParseException.class, () -> lcp.parse(flag));
    }

}
