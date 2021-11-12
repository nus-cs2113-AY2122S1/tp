package happybit.parser;

import happybit.command.ListHabitsCommand;
import happybit.exception.HaBitParserException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ListHabitParserTest {

    private static final String ERROR_GOAL_INDEX_FORMAT = "The command is missing the 'g/' flag.";
    private static final String ERROR_GOAL_INDEX_NON_INTEGER = "The flag 'g/' has to be followed by a number.";
    private static final String ERROR_GOAL_INDEX_NEGATIVE_NUM =
            "The flag 'g/' has to be followed by a positive integer.";
    private static final String ERROR_GOAL_INDEX_ZERO_NUM =
            "The flag 'g/' has to be followed by a number greater than 0.";
    private static final String ERROR_FLAG_INDEX_MISSING_PARAMETER = "Index expected after '%1$s' flag missing.";


    @Test
    void parseListHabitCommand_validInput_success() throws HaBitParserException {
        ListHabitsCommand testCommand = (ListHabitsCommand) ListHabitParser.parseListHabitCommand(" g/1");
        assertEquals(0, testCommand.getGoalIndex());
    }

    @Test
    void parseListHabitCommand_missingParameters_exceptionThrown() {
        try {
            ListHabitParser.parseListHabitCommand(" ");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_INDEX_FORMAT, e.getMessage());
        }
    }

    @Test
    void parseListHabitCommand_missingGoalIndex_exceptionThrown() {
        try {
            ListHabitParser.parseListHabitCommand("g/");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(String.format(ERROR_FLAG_INDEX_MISSING_PARAMETER, "g/"), e.getMessage());
        }
    }

    @Test
    void parseListHabitCommand_nonIntegerGoalIndex_exceptionThrown() {
        try {
            ListHabitParser.parseListHabitCommand("g/A");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_INDEX_NON_INTEGER, e.getMessage());
        }

        try {
            ListHabitParser.parseListHabitCommand("g/$");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_INDEX_NON_INTEGER, e.getMessage());
        }
    }

    @Test
    void parseListHabitCommand_negativeOrZeroGoalIndex_exceptionThrown() {
        try {
            ListHabitParser.parseListHabitCommand("g/-1");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_INDEX_NEGATIVE_NUM, e.getMessage());
        }

        try {
            ListHabitParser.parseListHabitCommand("g/0");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_INDEX_ZERO_NUM, e.getMessage());
        }
    }
}
