package happybit.parser;


import happybit.command.DoneHabitCommand;
import happybit.exception.HaBitParserException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DoneParserTest {

    @Test
    void parseDoneHabitCommand_input2Integers_habitIsDone() throws HaBitParserException {
        String inputCommand = "1 2";
        DoneHabitCommand testCommand = (DoneHabitCommand) DoneParser.parseDoneHabitCommand(inputCommand);
        assertEquals(0, testCommand.getGoalIndex());
        assertEquals(1, testCommand.getHabitIndex());
    }

    @Test
    void parseDoneHabitCommand_inputEmptyDescription_throwsException() throws HaBitParserException {
        String inputCommand = null;
        assertThrows(HaBitParserException.class, () -> DoneParser.parseDoneHabitCommand(inputCommand));
    }

    @Test
    void parseDoneHabitCommand_input1Integer_throwsException() throws HaBitParserException {
        String inputCommand = "1";
        assertThrows(HaBitParserException.class, () -> DoneParser.parseDoneHabitCommand(inputCommand));
    }

    @Test
    void parseDoneHabitCommand_input3Integers_throwsException() throws HaBitParserException {
        String inputCommand = "1 2 3";
        assertThrows(HaBitParserException.class, () -> DoneParser.parseDoneHabitCommand(inputCommand));
    }


    @Test
    void parseDoneHabitCommand_invalidDescriptions_throwsException() throws HaBitParserException {
        String inputCommand = "Daren 4.9";
        assertThrows(HaBitParserException.class, () -> DoneParser.parseDoneHabitCommand(inputCommand));
    }

}