package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.commands.clients.AddClientCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.flights.AddFlightCommand;
import seedu.duke.commands.tours.AddTourCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {

    private static final String CORRECT_ADDCLIENT_ONE = "add -c j12 /n john /cn 91234567 /m johndoe@gmail.com";
    private static final String CORRECT_ADDCLIENT_TWO = " add -c j12 /n  john  /cn   91234567 /m    johndoe@gmail.com";
    private static final String CORRECT_ADDCLIENT_THREE = "add -c j12 /n john /m johndoe@gmail.com /cn 91234567";
    private static final String CORRECT_ADDFLIGHT_ONE =
            "add -f SQ-JPN1 /d JPN /r SG /dd 23/10/21 13:00 /rd 27/10/21 02:00";
    private static final String CORRECT_ADDFLIGHT_TWO =
            "add -f    SQ-JPN1 /d    JPN /r SG /dd    23/10/21 13:00 /rd   27/10/21 02:00";
    private static final String CORRECT_ADDFLIGHT_THREE =
            "add -f SQ-JPN1 /d JPN /dd 23/10/21 13:00 /r SG /rd 27/10/21 02:00";
    private static final String CORRECT_ADDTOUR_ONE = "add -t aus1369 /n australiaromance /p 1300";
    private static final String CORRECT_ADDTOUR_TWO = "add  -t     aus1369 /n   australiaromance  /p 1300";
    private static final String CORRECT_ADDTOUR_THREE = "add -t aus1369 /p 1300 /n australiaromance";
    private static final String WRONG_ADDCLIENT_MISSING_PREFIX = "add -c j12 /n john /m johndoe@gmail.com";
    private static final String WRONG_ADDTOUR_MISSING_PREFIX = "add -t aus1369 /p 1300";
    private static final String WRONG_ADDFLIGHT_MISSING_PREFIX = "add -f SQ-JPN1 /d JPN /r SG /dd 23/10/21 13:00";
    private static final String WRONG_ADDCLIENT_MISSING_ID = "add -c /n botuan /cn 91234567 /m johndoe@gmail.com";
    private static final String WRONG_ADDFLIGHT_MISSING_ID =
            "add -f /d JPN /r SG /dd 23/10/21 13:00 /rd 27/10/21 02:00";
    private static final String WRONG_ADDTOUR_MISSING_ID = "add -t /n australiaromance /p 1300";
    private static final String WRONG_ADDCLIENT_DUPLICATE_PREFIX =
            "add -c c001 /n Bo Tuan /cn 91234567 /m botuan@gmail.com /cn 13465789";
    private static final String WRONG_ADDTOUR_DUPLICATE_PREFIX = "add -t aus1369 /n australiaromance /p 1300 /p 2400";
    private static final String WRONG_ADDFLIGHT_DUPLICATE_PREFIX =
            "add -f SQ-JPN1 /d JPN /d JAPAN /r SG /dd 23/10/21 13:00 /rd 27/10/21 02:00";

    @Test
    void parse_addClientCommand_correctCommandCreated() throws TourPlannerException {
        parseAndAssertCommandType(CORRECT_ADDCLIENT_ONE, AddClientCommand.class);

        //whitespaces are allowed
        parseAndAssertCommandType(CORRECT_ADDCLIENT_TWO, AddClientCommand.class);

        //order of prefixes does not matter
        parseAndAssertCommandType(CORRECT_ADDCLIENT_THREE, AddClientCommand.class);
    }

    @Test
    void parse_addFlightCommand_correctFlightCreated() throws TourPlannerException {
        parseAndAssertCommandType(CORRECT_ADDFLIGHT_ONE, AddFlightCommand.class);

        //whitespaces are allowed
        parseAndAssertCommandType(CORRECT_ADDFLIGHT_TWO, AddFlightCommand.class);

        //order of prefixes does not matter
        parseAndAssertCommandType(CORRECT_ADDFLIGHT_THREE, AddFlightCommand.class);
    }

    @Test
    void parse_addTourCommand_correctTourCreated() throws TourPlannerException {
        parseAndAssertCommandType(CORRECT_ADDTOUR_ONE, AddTourCommand.class);

        //whitespaces are allowed
        parseAndAssertCommandType(CORRECT_ADDTOUR_TWO, AddTourCommand.class);

        //order of prefixes does not matter
        parseAndAssertCommandType(CORRECT_ADDTOUR_THREE, AddTourCommand.class);
    }

    @Test
    void parse_missingPrefixes_failure() {
        //missing contact number
        assertParseFailure(WRONG_ADDCLIENT_MISSING_PREFIX, Parser.ERROR_MISSING_PREFIXES);

        //missing tour name
        assertParseFailure(WRONG_ADDTOUR_MISSING_PREFIX, Parser.ERROR_MISSING_PREFIXES);

        //missing return flight date
        assertParseFailure(WRONG_ADDFLIGHT_MISSING_PREFIX, Parser.ERROR_MISSING_PREFIXES);
    }

    @Test
    void parse_missingIdOrName_failure() {
        assertParseFailure(WRONG_ADDCLIENT_MISSING_ID, Parser.ERROR_MISSING_NAME_ID);
        assertParseFailure(WRONG_ADDFLIGHT_MISSING_ID, Parser.ERROR_MISSING_NAME_ID);
        assertParseFailure(WRONG_ADDTOUR_MISSING_ID, Parser.ERROR_MISSING_NAME_ID);
    }

    @Test
    void parse_duplicatePrefixes_failure() {
        assertParseFailure(WRONG_ADDCLIENT_DUPLICATE_PREFIX, Parser.ERROR_DUPLICATE_PREFIXES);
        assertParseFailure(WRONG_ADDTOUR_DUPLICATE_PREFIX, Parser.ERROR_DUPLICATE_PREFIXES);
        assertParseFailure(WRONG_ADDFLIGHT_DUPLICATE_PREFIX, Parser.ERROR_DUPLICATE_PREFIXES);
    }


    private <T extends Command> void parseAndAssertCommandType(String input, Class<T> expectedCommandClass)
            throws TourPlannerException {
        final Command result = Parser.parse(input);
        assertTrue(result.getClass().isAssignableFrom(expectedCommandClass));
    }

    private void assertParseFailure(String input, String expectedMessage) {
        try {
            Parser.parse(input);
            throw new AssertionError("The expected exception was not thrown");
        } catch (TourPlannerException e) {
            assertEquals(expectedMessage, e.getMessage());
        }
    }
}
