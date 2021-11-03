package happybit.parser;

import happybit.command.UpdateGoalNameCommand;
import happybit.command.UpdateHabitIntervalCommand;
import happybit.exception.HaBitParserException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class UpdateParserTest {

    private static final String ERROR_GOAL_INDEX_FORMAT = "Use the 'g/' flag to define the goal index. Eg: g/1";
    private static final String ERROR_GOAL_INDEX_NON_INTEGER = "The goal index has to be a number.";
    private static final String ERROR_GOAL_NAME_FORMAT = "Use the 'n/' flag set the new goal name. "
            + "Eg: n/Reach for the stars";
    private static final String ERROR_HABIT_INDEX_FORMAT = "Use the 'h/' flag to define the goal index. Eg: h/1";
    private static final String ERROR_HABIT_INDEX_NON_INTEGER = "The habit index has to be a number.";
    private static final String ERROR_HABIT_NAME_FORMAT = "Use the 'n/' flag set the new habit name. ";
    private static final String ERROR_INTERVAL_FORMAT = "Use the i/ flag to define the interval for the habit. Eg i/1";
    private static final String ERROR_INTERVAL_NON_INTEGER = "The interval has to be a number";
    private static final String ERROR_INTERVAL_NEGATIVE = "The interval has to be a positive integer.";
    private static final String ERROR_INTERVAL_ZERO = "You cannot update a habit to have no interval.";


    @Test
    void parseUpdateGoalNameCommand_validInput_success() throws HaBitParserException {
        UpdateGoalNameCommand testCommand =
                (UpdateGoalNameCommand) UpdateParser.parseUpdateGoalNameCommand(" g/1 n/ New ");
        assertEquals(0, testCommand.getGoalIndex());
        assertEquals("New", testCommand.getNewGoalName());
    }

    @Test
    void parseUpdateGoalNameCommand_missingParameters_exceptionThrown() {
        try {
            UpdateParser.parseUpdateGoalNameCommand(" ");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_INDEX_FORMAT, e.getMessage());
        }
    }

    @Test
    void parseUpdateGoalNameCommand_missingGoalIndex_exceptionThrown() {
        try {
            UpdateParser.parseUpdateGoalNameCommand("g/");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_INDEX_FORMAT, e.getMessage());
        }
    }

    @Test
    void parseUpdateGoalNameCommand_nonIntegerGoalIndex_exceptionThrown() {
        try {
            UpdateParser.parseUpdateGoalNameCommand("g/A");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_INDEX_NON_INTEGER, e.getMessage());
        }

        try {
            UpdateParser.parseUpdateGoalNameCommand("g/$");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_INDEX_NON_INTEGER, e.getMessage());
        }
    }

    @Test
    void parseUpdateGoalNameCommand_missingGoalName_exceptionThrown() {
        try {
            UpdateParser.parseUpdateGoalNameCommand("g/1 n/ ");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_NAME_FORMAT, e.getMessage());
        }

        try {
            UpdateParser.parseUpdateGoalNameCommand("g/1 ");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_NAME_FORMAT, e.getMessage());
        }
    }

    @Test
    void parseUpdateGoalNameCommand_nullInput_exceptionThrown() {
        try {
            UpdateParser.parseUpdateGoalNameCommand(null);
            fail();
        } catch (HaBitParserException e) {
            assertEquals(Parser.ERROR_NO_PARAMS, e.getMessage());
        }
    }

    @Test
    void parseUpdateHabitIntervalCommand_validInput_success() throws HaBitParserException {
        UpdateHabitIntervalCommand testCommand =
                (UpdateHabitIntervalCommand) UpdateParser.parseUpdateHabitIntervalCommand("g/1 h/1 i/3");
        assertEquals(0, testCommand.getGoalIndex());
        assertEquals(0, testCommand.getHabitIndex());
        assertEquals(3, testCommand.getNewInterval());
    }

    @Test
    void parseUpdateHabitIntervalCommand_missingGoalIndex_exceptionThrown() {
        try {
            UpdateParser.parseUpdateHabitIntervalCommand("g/");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_INDEX_FORMAT, e.getMessage());
        }
    }

    @Test
    void parseUpdateHabitIntervalCommand_nonIntegerGoalIndex_exceptionThrown() {
        try {
            UpdateParser.parseUpdateHabitIntervalCommand("g/A");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_INDEX_NON_INTEGER, e.getMessage());
        }

        try {
            UpdateParser.parseUpdateHabitIntervalCommand("g/$");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_INDEX_NON_INTEGER, e.getMessage());
        }
    }

    @Test
    void parseUpdateHabitIntervalCommand_missingHabitIndex_exceptionThrown() {
        try {
            UpdateParser.parseUpdateHabitIntervalCommand("g/1 h/ ");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_HABIT_INDEX_FORMAT, e.getMessage());
        }

        try {
            UpdateParser.parseUpdateHabitIntervalCommand("g/1 n/ ");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_HABIT_INDEX_FORMAT, e.getMessage());
        }

        try {
            UpdateParser.parseUpdateHabitIntervalCommand("g/1 ");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_HABIT_INDEX_FORMAT, e.getMessage());
        }
    }

    @Test
    void parseUpdateHabitIntervalCommand_nonIntegerHabitIndex_exceptionThrown() {
        try {
            UpdateParser.parseUpdateHabitIntervalCommand("g/1 h/A");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_HABIT_INDEX_NON_INTEGER, e.getMessage());
        }

        try {
            UpdateParser.parseUpdateHabitIntervalCommand("g/1 h/$");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_HABIT_INDEX_NON_INTEGER, e.getMessage());
        }
    }

    @Test
    void parseUpdateHabitIntervalCommand_missingInterval_exceptionThrown() {
        try {
            UpdateParser.parseUpdateHabitIntervalCommand("g/1 h/1 i/ ");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_INTERVAL_FORMAT, e.getMessage());
        }

        try {
            UpdateParser.parseUpdateHabitIntervalCommand("g/1 h/1 n/ ");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_INTERVAL_FORMAT, e.getMessage());
        }

        try {
            UpdateParser.parseUpdateHabitIntervalCommand("g/1 h/1 ");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_INTERVAL_FORMAT, e.getMessage());
        }
    }

    @Test
    void parseUpdateHabitIntervalCommand_nonIntegerInterval_exceptionThrown() {
        try {
            UpdateParser.parseUpdateHabitIntervalCommand("g/1 h/1 i/A");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_INTERVAL_NON_INTEGER, e.getMessage());
        }

        try {
            UpdateParser.parseUpdateHabitIntervalCommand("g/1 h/1 i/$");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_INTERVAL_NON_INTEGER, e.getMessage());
        }
    }

    @Test
    void parseUpdateHabitIntervalCommand_negativeOrZeroInterval_exceptionThrown() {
        try {
            UpdateParser.parseUpdateHabitIntervalCommand("g/1 h/1 i/-100");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_INTERVAL_NEGATIVE, e.getMessage());
        }

        try {
            UpdateParser.parseUpdateHabitIntervalCommand("g/1 h/1 i/0");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_INTERVAL_ZERO, e.getMessage());
        }
    }

    @Test
    void parseUpdateGHabitIntervalCommand_nullInput_exceptionThrown() {
        try {
            UpdateParser.parseUpdateHabitIntervalCommand(null);
            fail();
        } catch (HaBitParserException e) {
            assertEquals(Parser.ERROR_NO_PARAMS, e.getMessage());
        }
    }

}
