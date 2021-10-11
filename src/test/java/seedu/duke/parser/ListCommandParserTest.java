package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.commands.ListCommand;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ListCommandParserTest {

    @Test
    public void test_moduleMasterListFlag_success() {
        String flag = "/m";
        ListCommandParser lcp = new ListCommandParser();

    }

    @Test
    public void test_universityMasterListFlag_success() {
        String flag = "/u";
        ListCommandParser lcp = new ListCommandParser();

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
