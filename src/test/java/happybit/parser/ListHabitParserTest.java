package happybit.parser;

import happybit.command.ListHabitsCommand;
import happybit.exception.HaBitParserException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ListHabitParserTest {

    private static final String ERROR_GOAL_INDEX_FORMAT = "Use the 'g/' flag to define the goal index. Eg: g/1";
    private static final String ERROR_GOAL_INDEX_NON_INTEGER = "The goal index has to be a number.";

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
            assertEquals(ERROR_GOAL_INDEX_FORMAT, e.getMessage());
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
    void parseListHabitCommand_nullInput_exceptionThrown() {
        try {
            ListHabitParser.parseListHabitCommand(null);
            fail();
        } catch (HaBitParserException e) {
            assertEquals(Parser.ERROR_NO_PARAMS, e.getMessage());
        }
    }
}
