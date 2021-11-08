package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.commands.clientpackages.ListClientPackageCommand;
import seedu.duke.commands.clients.AddClientCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.clients.ListClientCommand;
import seedu.duke.commands.clients.SortClientCommand;
import seedu.duke.commands.flights.AddFlightCommand;
import seedu.duke.commands.flights.ListFlightCommand;
import seedu.duke.commands.flights.SortFlightCommand;
import seedu.duke.commands.tours.AddTourCommand;
import seedu.duke.commands.tours.ListTourCommand;
import seedu.duke.commands.tours.SortTourCommand;

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
    private static final String WRONG_ADD_MISSING_IDENTIFIER = "add -o c001 /n botuan /cn 91234567 /m john@mail.com";
    private static final String WRONG_SORT_MISSING_IDENTIFIER = "sort /d";
    private static final String CORRECT_SORT_TOUR = "sort -t /p";
    private static final String CORRECT_SORT_CLIENT = "sort -c /n";
    private static final String CORRECT_SORT_FLIGHT = "sort -f /d";
    public static final String CORRECT_LIST_FLIGHT = "list -f";
    public static final String CORRECT_LIST_CLIENT = "list -c";
    public static final String CORRECT_LIST_TOUR = "list -t";
    public static final String CORRECT_LIST_PACKAGE = "list -p";
    private static final String WRONG_ADD_FLIGHT_DATETIME_FORMAT =
            "add -f SQ-JPN1 /d JPN /r SG /dd 23/10/21 25:00 /rd 27/10/21 02:00";
    private static final String WRONG_ADD_FLIGHT_LOGIC_ERROR =
            "add -f SQ-JPN1 /d JPN /r SG /dd 23/11/21 21:00 /rd 19/10/21 02:00";
    public static final String WRONG_MISSING_IDENTIFIER_ONE = "list /f";
    public static final String WRONG_IDENTIFIER_GIVEN = "sort -o /n";
    public static final String WRONG_MISSING_IDENTIFIER_TWO = "cut c001";


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
    void parse_sortCommand_correctCommand() throws TourPlannerException {
        parseAndAssertCommandType(CORRECT_SORT_TOUR, SortTourCommand.class);
        parseAndAssertCommandType(CORRECT_SORT_CLIENT, SortClientCommand.class);
        parseAndAssertCommandType(CORRECT_SORT_FLIGHT, SortFlightCommand.class);
    }

    @Test
    void parse_listCommand_correctCommand() throws TourPlannerException {
        parseAndAssertCommandType(CORRECT_LIST_FLIGHT, ListFlightCommand.class);
        parseAndAssertCommandType(CORRECT_LIST_CLIENT, ListClientCommand.class);
        parseAndAssertCommandType(CORRECT_LIST_TOUR, ListTourCommand.class);
        parseAndAssertCommandType(CORRECT_LIST_PACKAGE, ListClientPackageCommand.class);
    }

    @Test
    void parse_missingIdentifier_failure() {
        assertParseFailure(WRONG_MISSING_IDENTIFIER_ONE, Parser.ERROR_MISSING_IDENTIFIER);
        assertParseFailure(WRONG_IDENTIFIER_GIVEN, Parser.ERROR_MISSING_IDENTIFIER);
        assertParseFailure(WRONG_MISSING_IDENTIFIER_TWO, Parser.ERROR_MISSING_IDENTIFIER);
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
        assertParseFailure(WRONG_ADDCLIENT_MISSING_ID, Parser.ERROR_MISSING_FIELDS);
        assertParseFailure(WRONG_ADDFLIGHT_MISSING_ID, Parser.ERROR_MISSING_FIELDS);
        assertParseFailure(WRONG_ADDTOUR_MISSING_ID, Parser.ERROR_MISSING_FIELDS);
    }

    @Test
    void parse_duplicatePrefixes_failure() {
        assertParseFailure(WRONG_ADDCLIENT_DUPLICATE_PREFIX, Parser.ERROR_DUPLICATE_PREFIXES);
        assertParseFailure(WRONG_ADDTOUR_DUPLICATE_PREFIX, Parser.ERROR_DUPLICATE_PREFIXES);
        assertParseFailure(WRONG_ADDFLIGHT_DUPLICATE_PREFIX, Parser.ERROR_DUPLICATE_PREFIXES);
    }

    @Test
    void parse_wrongOrMissingIdentifier_failure() {
        assertParseFailure(WRONG_ADD_MISSING_IDENTIFIER, Parser.ERROR_MISSING_IDENTIFIER);
        assertParseFailure(WRONG_SORT_MISSING_IDENTIFIER, Parser.ERROR_MISSING_IDENTIFIER);
    }

    @Test
    void parse_wrongDateTimeEntry_failure() {
        assertParseFailure(WRONG_ADD_FLIGHT_DATETIME_FORMAT, Parser.ERROR_FLIGHT_TIME_FORMAT);
        assertParseFailure(WRONG_ADD_FLIGHT_LOGIC_ERROR, Parser.ERROR_FLIGHT_TIME_INVERT);
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
