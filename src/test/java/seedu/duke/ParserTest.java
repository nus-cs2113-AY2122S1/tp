package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.commands.clients.AddClientCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.flights.AddFlightCommand;
import seedu.duke.commands.tours.AddTourCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {

    private static final String CORRECT_ADDCLIENT_ONE = "add -c john /cn 91234567 /m johndoe@gmail.com";
    private static final String CORRECT_ADDCLIENT_TWO = "   add -c   john  /cn   91234567 /m    johndoe@gmail.com";
    private static final String CORRECT_ADDCLIENT_THREE = "add -c john /m johndoe@gmail.com /cn 91234567";
    private static final String CORRECT_ADDFLIGHT_ONE =
            "add -f SQ-JPN1 /t JPN /f SG /dt 23/10/21 1300 /df 27/10/21 0200";
    private static final String CORRECT_ADDFLIGHT_TWO =
            "add    -f SQ-JPN1    /t JPN    /f SG /dt   23/10/21 1300 /df 27/10/21 0200";
    private static final String CORRECT_ADDFLIGHT_THREE =
            "add -f SQ-JPN1 /f SG /t JPN /df 27/10/21 0200 /dt 23/10/21 1300";
    private static final String CORRECT_ADDTOUR_ONE = "add -t johndoe /n australiaromance /p 1300";
    private static final String CORRECT_ADDTOUR_TWO = "add -t johndoe  /n     australiaromance /p    1300";
    private static final String CORRECT_ADDTOUR_THREE = "add -t johndoe /p 1300 /n australiaromance";
    private static final String WRONG_ADDCLIENT_MISSING_PREFIX = "add -c john /m johndoe@gmail.com";
    private static final String WRONG_ADDTOUR_MISSING_PREFIX = "add -t johndoe /n australiaromance";
    private static final String WRONG_ADDFLIGHT_MISSING_PREFIX = "add -f SQ-JPN1 /t JPN /f SG";
    private static final String WRONG_ADDCLIENT_MISSING_NAME = "add -c /cn 91234567 /m johndoe@gmail.com";
    private static final String WRONG_ADDFLIGHT_MISSING_NAME =
            "add -f /t JPN /f SG /dt 23/10/21 1300 /df 27/10/21 0200";
    private static final String WRONG_ADDTOUR_MISSING_NAME = "add -t /n australiaromance /p 1300";

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
        //missing /cn
        assertParseFailure(WRONG_ADDCLIENT_MISSING_PREFIX, Parser.ERROR_MISSING_PREFIXES);

        //missing /p
        assertParseFailure(WRONG_ADDTOUR_MISSING_PREFIX, Parser.ERROR_MISSING_PREFIXES);

        //missing /dt and /df
        assertParseFailure(WRONG_ADDFLIGHT_MISSING_PREFIX, Parser.ERROR_MISSING_PREFIXES);
    }

    @Test
    void parse_missingIdOrName_failure() {
        assertParseFailure(WRONG_ADDCLIENT_MISSING_NAME, Parser.ERROR_MISSING_NAME);
        assertParseFailure(WRONG_ADDFLIGHT_MISSING_NAME, Parser.ERROR_MISSING_NAME);
        assertParseFailure(WRONG_ADDTOUR_MISSING_NAME, Parser.ERROR_MISSING_NAME);
    }

    private <T extends Command> T parseAndAssertCommandType(String input, Class<T> expectedCommandClass)
            throws TourPlannerException {
        final Command result = Parser.parse(input);
        assertTrue(result.getClass().isAssignableFrom(expectedCommandClass));
        return (T) result;
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
