package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Junit testing of the Parser class.
 */
public class ParserTest {

    private static final String WRONG_ADD_DUPLICATE_PREFIX
            = "add botuan /cn 9487 /f SQ123 /cn 5678 /a hotel-one /t aus-a";
    public static final String WRONG_ADD_MISSING_NAME = "add /cn 1234 /f SQ123 /a intercontinental /t jpn-a";
    private static final String WRONG_ADD_MISSING_PREFIX_ONE = "add botuan /f SQ1234 /a panpac";
    public static final String WRONG_ADD_MISSING_PREFIX_TWO = "add botuan /cn 1234 /f MH123 /t fra-b ";
    public static final String CORRECT_ADD_ONE = "add botuan /cn 9487 /f SQ123 /a hotel-one /t aus-a";
    public static final String CORRECT_ADD_TWO = "   add botuan /cn   9487 /f SQ123 /a hotel-one /t aus-a";
    public static final String CORRECT_CLEAR = "clear";
    public static final String CORRECT_LIST = "list";
    public static final String CORRECT_CUT = "cut 0";
    public static final String CORRECT_BYE = "bye";
    public static final String WRONG_LIST = "list blah";
    public static final String WRONG_CLEAR = "clear blahblah";
    public static final String WRONG_BYE = "bye blah blah";


    /**
     * Asserts that add command is correctly parsed and returns an AddCommand object.
     *
     * @throws TourPlannerException if there are general exceptions to user's input
     */
    @Test
    void parse_addCommand_correctCommandCreated() throws TourPlannerException {
        parseAndAssertCommandType(CORRECT_ADD_ONE, AddCommand.class);

        //whitespaces are allowed
        parseAndAssertCommandType(CORRECT_ADD_TWO, AddCommand.class);
    }

    /**
     * Asserts that clear command is correctly parsed and returns a ClearCommand object.
     *
     * @throws TourPlannerException if there are general exceptions to user's input
     */
    @Test
    void parse_clearCommand_correctCommandCreated() throws TourPlannerException {
        parseAndAssertCommandType(CORRECT_CLEAR, ClearCommand.class);
    }

    /**
     * Asserts that list command is correctly parsed and returns a ListCommand object.
     *
     * @throws TourPlannerException if there are general exceptions to user's input
     */
    @Test
    void parse_listCommand_correctCommandCreated() throws TourPlannerException {
        parseAndAssertCommandType(CORRECT_LIST, ListCommand.class);
    }

    /**
     * Asserts that cut command is correctly parsed and returns a CutCommand object.
     *
     * @throws TourPlannerException if there are general exceptions to user's input
     */
    @Test
    void parse_cutCommand_correctCommandCreated() throws TourPlannerException {
        parseAndAssertCommandType(CORRECT_CUT, CutCommand.class);

    }

    /**
     * Asserts that bye command is correctly parsed and returns a ByeCommand object.
     *
     * @throws TourPlannerException if there are general exceptions to user's input
     */
    @Test
    void parse_byeCommand_correctCommandCreated() throws TourPlannerException {
        parseAndAssertCommandType(CORRECT_BYE, ByeCommand.class);

    }

    /**
     * Asserts that parsing duplicates in params during execution of add command will cause an exception to be thrown.
     */
    @Test
    void parse_duplicatePrefixes_failure() {
        assertParseFailure(WRONG_ADD_DUPLICATE_PREFIX, Parser.ERROR_DUPLICATE_PREFIXES);
    }

    /**
     * Asserts that missing prefixes in params during execution of add will cause an exception to be thrown.
     */
    @Test
    void parse_missingPrefixes_failure() {
        //missing "/a"
        assertParseFailure(WRONG_ADD_MISSING_PREFIX_TWO, Parser.ERROR_MISSING_PREFIXES);

        //missing both "/cn" and "/t"
        assertParseFailure(WRONG_ADD_MISSING_PREFIX_ONE, Parser.ERROR_MISSING_PREFIXES);
    }

    /**
     * Asserts that missing name in params string during execution of add will cause an exception to be thrown.
     */
    @Test
    void parse_missingName_failure() {
        assertParseFailure(WRONG_ADD_MISSING_NAME, Parser.ERROR_MISSING_NAME);
    }

    /**
     * Asserts that superfluous inputs other than the command itself for list, clear, bye will throw an exception.
     */
    @Test
    void parse_superfluousInput_failure() {
        assertParseFailure(WRONG_LIST, Parser.ERROR_EXTRA_INPUT);
        assertParseFailure(WRONG_CLEAR, Parser.ERROR_EXTRA_INPUT);
        assertParseFailure(WRONG_BYE, Parser.ERROR_EXTRA_INPUT);
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
