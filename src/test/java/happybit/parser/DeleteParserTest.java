package happybit.parser;

import happybit.command.DeleteGoalCommand;
import happybit.command.DeleteHabitCommand;
import happybit.exception.HaBitParserException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class DeleteParserTest {

    private static final String ERROR_GOAL_INDEX_FORMAT = "The command is missing the 'g/' flag.";
    private static final String ERROR_GOAL_INDEX_NON_INTEGER = "The flag 'g/' has to be followed by a number.";
    private static final String ERROR_HABIT_INDEX_FORMAT = "The command is missing the 'h/' flag.";
    private static final String ERROR_HABIT_INDEX_NON_INTEGER = "The flag 'h/' has to be followed by a number.";
    private static final String ERROR_GOAL_INDEX_NEGATIVE_NUM =
            "The flag 'g/' has to be followed by a positive integer.";
    private static final String ERROR_GOAL_INDEX_ZERO_NUM =
            "The flag 'g/' has to be followed by a number greater than 0.";
    private static final String ERROR_HABIT_INDEX_NEGATIVE_NUM =
            "The flag 'h/' has to be followed by a positive integer.";
    private static final String ERROR_HABIT_INDEX_ZERO_NUM =
            "The flag 'h/' has to be followed by a number greater than 0.";
    private static final String ERROR_FLAG_INDEX_MISSING_PARAMETER = "Index expected after '%1$s' flag missing.";


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
            assertEquals(String.format(ERROR_FLAG_INDEX_MISSING_PARAMETER, "g/"), e.getMessage());
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
    void parseDeleteGoalCommand_negativeOrZeroGoalIndex_exceptionThrown() {
        try {
            DeleteParser.parseDeleteGoalCommand("g/-1");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_INDEX_NEGATIVE_NUM, e.getMessage());
        }

        try {
            DeleteParser.parseDeleteGoalCommand("g/0");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_INDEX_ZERO_NUM, e.getMessage());
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
            assertEquals(String.format(ERROR_FLAG_INDEX_MISSING_PARAMETER, "g/"), e.getMessage());
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
            assertEquals(String.format(ERROR_FLAG_INDEX_MISSING_PARAMETER, "h/"), e.getMessage());
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
    void parseDeleteHabitCommand_negativeOrZeroHabitIndex_exceptionThrown() {
        try {
            DeleteParser.parseDeleteHabitCommand("g/1 h/-1");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_HABIT_INDEX_NEGATIVE_NUM, e.getMessage());
        }

        try {
            DeleteParser.parseDeleteHabitCommand("g/1 h/0");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_HABIT_INDEX_ZERO_NUM, e.getMessage());
        }
    }
}
