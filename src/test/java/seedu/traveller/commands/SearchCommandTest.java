package seedu.traveller.commands;

import org.junit.jupiter.api.Test;
import seedu.traveller.commands.SearchCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SearchCommandTest {
    @Test
    public void searchCommand_create_success() {
        SearchCommand searchCommand = new SearchCommand("CHN", "SIN");
        assertEquals("search: CHN to SIN", searchCommand.toString());
    }
}
