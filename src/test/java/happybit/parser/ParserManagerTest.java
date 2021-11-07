package happybit.parser;

import happybit.command.AddGoalCommand;
import happybit.command.AddHabitCommand;
import happybit.command.DeleteGoalCommand;
import happybit.command.DeleteHabitCommand;
import happybit.command.DoneHabitCommand;
import happybit.command.ExitCommand;
import happybit.command.HelpCommand;
import happybit.command.ListGoalsCommand;
import happybit.command.ListHabitsCommand;
import happybit.command.ReturnCommand;
import happybit.command.SetCommand;
import happybit.command.UpdateGoalCommand;
import happybit.command.UpdateHabitCommand;
import happybit.exception.HaBitParserException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ParserManagerTest {

    @Test
    void parse_emptyInput_exceptionThrown() {
        try {
            ParserManager.parse("");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ParserManager.ERROR_NO_INPUT, e.getMessage());
        }
        try {
            ParserManager.parse("    ");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ParserManager.ERROR_NO_INPUT, e.getMessage());
        }
    }

    @Test
    void parse_invalidInput_exceptionThrown() {
        try {
            ParserManager.parse("   aaa@@@aaa  ");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ParserManager.ERROR_INVALID_INPUT, e.getMessage());
        }
    }

    @Test
    void parse_validSetInput_expectAddGoalCommand() throws HaBitParserException {
        assertEquals(AddGoalCommand.class, ParserManager.parse("set n/Reduce spending e/31122022").getClass());
    }

    @Test
    void parse_validAddInput_expectAddHabitCommand() throws HaBitParserException {
        assertEquals(AddHabitCommand.class, ParserManager.parse("add n/Don't drink BBT g/1 i/3").getClass());
    }

    @Test
    void parse_validGoalInput_expectAddHabitCommand() throws HaBitParserException {
        assertEquals(SetCommand.class, ParserManager.parse("goal g/1").getClass());
    }

    @Test
    void parse_validListInput_expectListGoalsCommand() throws HaBitParserException {
        assertEquals(ListGoalsCommand.class, ParserManager.parse("  list  ").getClass());
    }

    @Test
    void parse_validViewInput_expectListHabitsCommand() throws HaBitParserException {
        assertEquals(ListHabitsCommand.class, ParserManager.parse("view g/1").getClass());
    }

    @Test
    void parse_validRemoveInput_expectDeleteGoalCommand() throws HaBitParserException {
        assertEquals(DeleteGoalCommand.class, ParserManager.parse("remove g/1").getClass());
    }

    @Test
    void parse_validDeleteInput_expectDeleteHabitCommand() throws HaBitParserException {
        assertEquals(DeleteHabitCommand.class, ParserManager.parse("delete g/1 h/1").getClass());
    }

    @Test
    void parse_validDoneInput_expectDoneHabitCommand() throws HaBitParserException {
        assertEquals(DoneHabitCommand.class, ParserManager.parse("done g/1 h/1").getClass());
    }

    @Test
    void parse_validUpdateInput_expectUpdateGoalCommand() throws HaBitParserException {
        assertEquals(UpdateGoalCommand.class, ParserManager.parse("update g/1 n/Read more").getClass());
    }

    @Test
    void parse_validChangeInput_expectUpdateHabitCommand() throws HaBitParserException {
        assertEquals(UpdateHabitCommand.class, ParserManager.parse("change g/2 h/1 i/7").getClass());
    }

    @Test
    void parse_validExitInput_expectExitCommand() throws HaBitParserException {
        assertEquals(ExitCommand.class, ParserManager.parse("  exit aaa ").getClass());
    }

    @Test
    void parse_validReturnInput_expectReturnCommand() throws HaBitParserException {
        assertEquals(ReturnCommand.class, ParserManager.parse("  return  aaa  ").getClass());

    }

    @Test
    void parse_validHelpInput_expectHelpCommand() throws HaBitParserException {
        assertEquals(HelpCommand.class, ParserManager.parse(" help aaa  ").getClass());
        assertEquals(HelpCommand.class, ParserManager.parse(" aaa ").getClass());
    }
}
