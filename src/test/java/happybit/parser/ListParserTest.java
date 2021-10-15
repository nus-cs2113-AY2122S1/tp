package happybit.parser;

import happybit.command.ListHabitsCommand;
import happybit.exception.HaBitParserException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ListParserTest {

    @Test
    void parseListHabitCommand_index2_goalIndex() throws HaBitParserException {
        String testIndex = "2";
        ListHabitsCommand expectedOutput = new ListHabitsCommand(1);
        ListHabitsCommand actualOutput = (ListHabitsCommand) ListParser.parseListHabitCommand(testIndex);
        assertEquals(expectedOutput.getGoalIndex(), actualOutput.getGoalIndex());
    }

    @Test
    void parseListHabitCommand_invalidIndexString_exceptionThrown() throws HaBitParserException {
        String testIndex = "Daren is a beast";
        assertThrows(HaBitParserException.class, () -> ListParser.parseListHabitCommand(testIndex));
    }

    @Test
    void parseListHabitCommand_invalidIndexDouble_exceptionThrown() throws HaBitParserException {
        String testIndex = "8.0";
        assertThrows(HaBitParserException.class, () -> ListParser.parseListHabitCommand(testIndex));
    }
}