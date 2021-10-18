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

    @Test
    void parseInvalidInput_noDescription_exceptionThrown() {
        try {
            DoneParser.parseDoneHabitCommand(null);
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_NO_PARAMETER, e.getMessage());
        }

        try {
            DoneParser.parseDoneHabitCommand("");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_NO_PARAMETER, e.getMessage());
        }
    }

    @Test
    void parseInvalidInput_extraParameters_exceptionThrown() {
        try {
            DoneParser.parseDoneHabitCommand("1 2 3");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_EXTRA_PARAMETERS, e.getMessage());
        }
    }

    @Test
    void parseInvalidInput_missingParameters_exceptionThrown() {
        try {
            DoneParser.parseDoneHabitCommand("1");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_MISSING_PARAMETERS, e.getMessage());
        }
    }

    @Test
    void parseInvalidInput_invalidGoalIndex_exceptionThrown() {
        try {
            DoneParser.parseDoneHabitCommand("a 1");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_INVALID_GOAL_INDEX, e.getMessage());
        }
    }

    @Test
    void parseInvalidInput_invalidHabitIndex_exceptionThrown() {
        try {
            DoneParser.parseDoneHabitCommand("1 a");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_INVALID_HABIT_INDEX, e.getMessage());
        }
    }

    @Test
    void parseValidInput_twoIntegersSpaced_success() throws HaBitParserException {
        DoneHabitCommand testCommand = (DoneHabitCommand) DoneParser.parseDoneHabitCommand("1 2");
        assertEquals(0, testCommand.getGoalIndex());
        assertEquals(1, testCommand.getHabitIndex());
    }

}
