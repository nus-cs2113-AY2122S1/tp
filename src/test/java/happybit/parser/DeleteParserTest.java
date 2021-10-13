package happybit.parser;

import happybit.command.DeleteGoalCommand;
import happybit.command.ListHabitsCommand;
import happybit.exception.HaBitParserException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteParserTest {

    @Test
    void parseDeleteGoalCommand_emptyCommandInstruction_exceptionThrown() {
        String emptyText = null;
        assertThrows(HaBitParserException.class, () -> DeleteParser.parseDeleteGoalCommand(emptyText));
    }

    @Test
    void parseDeleteHabitCommand_emptyCommandInstruction_exceptionThrown() {
        String emptyText = null;
        assertThrows(HaBitParserException.class, () -> DeleteParser.parseDeleteHabitCommand(emptyText));
    }
}