package seedu.traveller.commands;

import org.junit.jupiter.api.Test;
import seedu.traveller.commands.ShortestCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SearchCommandTest {
    @Test
    public void searchCommand_create_success() {
        final String expectedOutput = "Search command: "
                + "\n\tstartCountry: CHN"
                + "\n\tendCountry: SIN";
        ShortestCommand searchCommand = new ShortestCommand("CHN", "SIN");
        assertEquals(expectedOutput, searchCommand.toString());
    }
}
