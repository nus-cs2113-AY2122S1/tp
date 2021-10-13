package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.commands.ListCommand;
import seedu.duke.modules.Module;
import seedu.duke.modules.ModuleList;
import seedu.duke.modules.ModuleMapping;
import seedu.duke.storage.Storage;
import seedu.duke.universities.University;
import seedu.duke.universities.UniversityList;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ListCommandParserTest {

    private static UniversityList universitySelectedList = new UniversityList();
    private static ModuleList moduleSelectedList = new ModuleList();

    @Test
    public void test_emptyFlag_exceptionThrown() {
        String flag = "";
        ListCommandParser lcp = new ListCommandParser();
        assertThrows(ParseException.class, () -> lcp.parse(flag, universitySelectedList, moduleSelectedList));
    }

}
