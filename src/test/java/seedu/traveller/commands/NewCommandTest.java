package seedu.traveller.commands;

import org.junit.jupiter.api.Test;
import seedu.traveller.commands.NewCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class NewCommandTest {
    @Test
    public void newCommand_create_success() {
        final String expectedOutput = "New command:"
                + "\n\ttripName: testTrip"
                + "\n\tstartCountry: MLY"
                + "\n\tendCountry: CHN";
        NewCommand newCommand = new NewCommand("testTrip", "MLY", "CHN");
        assertEquals(expectedOutput, newCommand.toString());
    }
}
