package happybit.parser;


import happybit.command.DoneHabitCommand;
import happybit.exception.HaBitParserException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class DoneParserTest {
    private static final String ERROR_NO_PARAMETER = "Please provide the goal and habit index"
            + " of the habit to be marked as done";
    private static final String ERROR_EXTRA_PARAMETERS = "Please provide only 2 integers";
    private static final String ERROR_MISSING_PARAMETERS = "The habit index is missing.";
    private static final String ERROR_INVALID_GOAL_INDEX = "Please enter a valid integer for the goal index";
    private static final String ERROR_INVALID_HABIT_INDEX = "Please enter a valid integer for the habit index";

    private static final String ERROR_GOAL_INDEX_FORMAT = "Use the 'g/' flag to define the goal index. Eg: g/1";
    private static final String ERROR_GOAL_INDEX_NON_INTEGER = "The goal index has to be a number.";
    private static final String ERROR_HABIT_INDEX_FORMAT = "Use the 'h/' flag to define the habit index. Eg: h/1";
    private static final String ERROR_HABIT_INDEX_NON_INTEGER = "The habit index has to be a number.";

    @Test
    void parseDoneHabitCommand_validInput_success() throws HaBitParserException {
        DoneHabitCommand testCommand = (DoneHabitCommand) DoneParser.parseDoneHabitCommand(" g/1 h/1 ");
        assertEquals(0, testCommand.getGoalIndex());
        assertEquals(0, testCommand.getHabitIndex());
    }

    @Test
    void parseDoneHabitCommand_missingParameters_exceptionThrown() {
        try {
            DoneParser.parseDoneHabitCommand("");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_INDEX_FORMAT, e.getMessage());
        }
    }

    @Test
    void parseDoneHabitCommand_missingGoalIndex_exceptionThrown() {
        try {
            DoneParser.parseDoneHabitCommand("g/");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_INDEX_FORMAT, e.getMessage());
        }
    }

    @Test
    void parseDoneHabitCommand_nonIntegerGoalIndex_exceptionThrown() {
        try {
            DoneParser.parseDoneHabitCommand("g/A");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_INDEX_NON_INTEGER, e.getMessage());
        }

        try {
            DoneParser.parseDoneHabitCommand("g/$");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_INDEX_NON_INTEGER, e.getMessage());
        }
    }

    @Test
    void parseDoneHabitCommand_missingHabitIndex_exceptionThrown() {
        try {
            DoneParser.parseDoneHabitCommand("g/1 h/");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_HABIT_INDEX_FORMAT, e.getMessage());
        }
    }

    @Test
    void parseDoneHabitCommand_nonIntegerHabitIndex_exceptionThrown() {
        try {
            DoneParser.parseDoneHabitCommand("g/1 h/a");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_HABIT_INDEX_NON_INTEGER, e.getMessage());
        }

        try {
            DoneParser.parseDoneHabitCommand("g/1 h/$");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_HABIT_INDEX_NON_INTEGER, e.getMessage());
        }
    }
}
