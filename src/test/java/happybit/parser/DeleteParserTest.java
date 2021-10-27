package happybit.parser;

import happybit.command.DeleteGoalCommand;
import happybit.command.DeleteHabitCommand;
import happybit.exception.HaBitParserException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class DeleteParserTest {

    private static final String ERROR_GOAL_INDEX_FORMAT = "Use the 'g/' flag to define the goal index. Eg: g/1";
    private static final String ERROR_GOAL_INDEX_NON_INTEGER = "The goal index has to be a number.";
    private static final String ERROR_HABIT_INDEX_FORMAT = "Use the 'h/' flag to define the habit index. Eg: h/1";
    private static final String ERROR_HABIT_INDEX_NON_INTEGER = "The habit index has to be a number.";

    /*
     * NOTE : ==================================================================
     * The following are tests for parseDeleteGoalCommand.
     * =========================================================================
     */

    @Test
    void parseDeleteGoalCommand_validInput_success() throws HaBitParserException {
        DeleteGoalCommand testCommand = (DeleteGoalCommand) DeleteParser.parseDeleteGoalCommand(" g/1 ");
        assertEquals(0, testCommand.getGoalIndex());
    }

    @Test
    void parseDeleteGoalCommand_missingParameters_exceptionThrown() {
        try {
            DeleteParser.parseDeleteGoalCommand("");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_INDEX_FORMAT, e.getMessage());
        }
    }

    @Test
    void parseDeleteGoalCommand_missingGoalIndex_exceptionThrown() {
        try {
            DeleteParser.parseDeleteGoalCommand("g/");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_INDEX_FORMAT, e.getMessage());
        }
    }

    @Test
    void parseDeleteGoalCommand_nonIntegerGoalIndex_exceptionThrown() {
        try {
            DeleteParser.parseDeleteGoalCommand("g/A");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_INDEX_NON_INTEGER, e.getMessage());
        }

        try {
            DeleteParser.parseDeleteGoalCommand("g/$");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_INDEX_NON_INTEGER, e.getMessage());
        }
    }

    @Test
    void parseDeleteGoalCommand_nullInput_exceptionThrown() {
        try {
            DeleteParser.parseDeleteGoalCommand(null);
            fail();
        } catch (HaBitParserException e) {
            assertEquals(Parser.ERROR_NO_PARAMS, e.getMessage());
        }
    }

    /*
     * NOTE : ==================================================================
     * The following are tests for parseDeleteHabitCommand.
     * =========================================================================
     */

    @Test
    void parseDeleteHabitCommand_validInput_success() throws HaBitParserException {
        DeleteHabitCommand testCommand = (DeleteHabitCommand) DeleteParser.parseDeleteHabitCommand(" g/1 h/1 ");
        assertEquals(0, testCommand.getGoalIndex());
        assertEquals(0, testCommand.getHabitIndex());
    }

    @Test
    void parseDeleteHabitCommand_missingParameters_exceptionThrown() {
        try {
            DeleteParser.parseDeleteHabitCommand("");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_INDEX_FORMAT, e.getMessage());
        }
    }

    @Test
    void parseDeleteHabitCommand_missingGoalIndex_exceptionThrown() {
        try {
            DeleteParser.parseDeleteHabitCommand("g/");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_INDEX_FORMAT, e.getMessage());
        }
    }

    @Test
    void parseDeleteHabitCommand_nonIntegerGoalIndex_exceptionThrown() {
        try {
            DeleteParser.parseDeleteHabitCommand("g/A");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_INDEX_NON_INTEGER, e.getMessage());
        }

        try {
            DeleteParser.parseDeleteHabitCommand("g/$");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_INDEX_NON_INTEGER, e.getMessage());
        }
    }

    @Test
    void parseDeleteHabitCommand_missingHabitIndex_exceptionThrown() {
        try {
            DeleteParser.parseDeleteHabitCommand("g/1 h/");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_HABIT_INDEX_FORMAT, e.getMessage());
        }

        try {
            DeleteParser.parseDeleteHabitCommand("g/1 ");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_HABIT_INDEX_FORMAT, e.getMessage());
        }
    }

    @Test
    void parseDeleteHabitCommand_nonIntegerHabitIndex_exceptionThrown() {
        try {
            DeleteParser.parseDeleteHabitCommand("g/1 h/a");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_HABIT_INDEX_NON_INTEGER, e.getMessage());
        }

        try {
            DeleteParser.parseDeleteHabitCommand("g/1 h/$");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_HABIT_INDEX_NON_INTEGER, e.getMessage());
        }
    }

    @Test
    void parseDeleteHabitCommand_nullInput_exceptionThrown() {
        try {
            DeleteParser.parseDeleteHabitCommand(null);
            fail();
        } catch (HaBitParserException e) {
            assertEquals(Parser.ERROR_NO_PARAMS, e.getMessage());
        }
    }
}
