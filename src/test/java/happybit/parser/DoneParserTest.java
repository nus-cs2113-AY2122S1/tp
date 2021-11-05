package happybit.parser;


import happybit.command.DoneHabitCommand;
import happybit.exception.HaBitParserException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class DoneParserTest {

    private static final String ERROR_GOAL_INDEX_FORMAT = "The command is missing the 'g/' flag";
    private static final String ERROR_GOAL_INDEX_NON_INTEGER = "The flag 'g/' has to be followed by a number";
    private static final String ERROR_HABIT_INDEX_FORMAT = "The flag 'h/' has to be followed by a number";
    private static final String ERROR_HABIT_INDEX_NON_INTEGER = "The flag 'h/' has to be followed by a number";


    @Test
    void parseDoneHabitCommand_validInput_success() throws HaBitParserException {
        DoneHabitCommand testCommand = (DoneHabitCommand) DoneParser.parseDoneHabitCommand(" g/1 h/1 ");
        assertEquals(0, testCommand.getGoalIndex());
        assertEquals(0, testCommand.getHabitIndex());
    }

    @Test
    void parseDoneHabitCommand_missingParameters_exceptionThrown() {
        try {
            DoneParser.parseDoneHabitCommand(" ");
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

        try {
            DoneParser.parseDoneHabitCommand("g/1 ");
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

    @Test
    void parseDoneHabitCommand_nullInput_exceptionThrown() {
        try {
            DoneParser.parseDoneHabitCommand(null);
            fail();
        } catch (HaBitParserException e) {
            assertEquals(Parser.ERROR_NO_PARAMS, e.getMessage());
        }
    }
}
