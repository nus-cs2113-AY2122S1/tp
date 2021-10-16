package seedu.traveller;

import org.junit.jupiter.api.Test;
import seedu.traveller.commands.NewCommand;
import seedu.traveller.exceptions.TravellerException;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ParserTest {
    private final NewCommand newCommand;

    public ParserTest() {
        newCommand = new NewCommand("trip2", "CHN", "JPN");
    }

    @Test
    public void parse_new_success() {
        try {
            assertEquals(newCommand.toString(), Parser.parse("new trip2 CHN JPN").toString());
        } catch (TravellerException e) {
            assertEquals("\tWrong format for New!\n\tCorrect format: new TRIP_NAME START END", e.getMessage());
        }
    }

    @Test
    public void parse_newCommand1Country_exceptionThrown() {
        try {
            assertEquals(newCommand, Parser.parse("new trip2 CHN-JPN"));
        } catch (TravellerException e) {
            assertEquals("\tWrong format for New!\n\tCorrect format: new TRIP_NAME START END", e.getMessage());
        }
    }

    @Test
    public void parse_newCommandMissingSpace_exceptionThrown() {
        try {
            Parser.parse("newtestTrip1 SIN MLY");
        } catch (TravellerException e) {
            assertEquals("\tThe command 'newtestTrip1 SIN MLY' is not recognised.", e.getMessage());
        }
    }

    @Test
    public void parse_invalidCommand_exceptionThrown() {
        try {
            Parser.parse("bad command");
        } catch (TravellerException e) {
            assertEquals("\tThe command 'bad command' is not recognised.", e.getMessage());
        }
    }
}
