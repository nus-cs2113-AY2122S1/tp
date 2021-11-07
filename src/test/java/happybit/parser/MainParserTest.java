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

class MainParserTest {

    @Test
    void parse_emptyInput_exceptionThrown() {
        try {
            MainParser.parse("");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(MainParser.ERROR_NO_INPUT, e.getMessage());
        }
        try {
            MainParser.parse("    ");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(MainParser.ERROR_NO_INPUT, e.getMessage());
        }
    }

    @Test
    void parse_invalidInput_exceptionThrown() {
        try {
            MainParser.parse("   aaa@@@aaa  ");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(MainParser.ERROR_INVALID_INPUT, e.getMessage());
        }
    }

    @Test
    void parse_validSetInput_expectAddGoalCommand() throws HaBitParserException {
        assertEquals(AddGoalCommand.class, MainParser.parse("set n/Reduce spending e/31122022").getClass());
    }

    @Test
    void parse_validAddInput_expectAddHabitCommand() throws HaBitParserException {
        assertEquals(AddHabitCommand.class, MainParser.parse("add n/Don't drink BBT g/1 i/3").getClass());
    }

    @Test
    void parse_validGoalInput_expectAddHabitCommand() throws HaBitParserException {
        assertEquals(SetCommand.class, MainParser.parse("goal g/1").getClass());
    }

    @Test
    void parse_validListInput_expectListGoalsCommand() throws HaBitParserException {
        assertEquals(ListGoalsCommand.class, MainParser.parse("  list  ").getClass());
    }

    @Test
    void parse_validViewInput_expectListHabitsCommand() throws HaBitParserException {
        assertEquals(ListHabitsCommand.class, MainParser.parse("view g/1").getClass());
    }

    @Test
    void parse_validRemoveInput_expectDeleteGoalCommand() throws HaBitParserException {
        assertEquals(DeleteGoalCommand.class, MainParser.parse("remove g/1").getClass());
    }

    @Test
    void parse_validDeleteInput_expectDeleteHabitCommand() throws HaBitParserException {
        assertEquals(DeleteHabitCommand.class, MainParser.parse("delete g/1 h/1").getClass());
    }

    @Test
    void parse_validDoneInput_expectDoneHabitCommand() throws HaBitParserException {
        assertEquals(DoneHabitCommand.class, MainParser.parse("done g/1 h/1").getClass());
    }

    @Test
    void parse_validUpdateInput_expectUpdateGoalCommand() throws HaBitParserException {
        assertEquals(UpdateGoalCommand.class, MainParser.parse("update g/1 n/Read more").getClass());
    }

    @Test
    void parse_validChangeInput_expectUpdateHabitCommand() throws HaBitParserException {
        assertEquals(UpdateHabitCommand.class, MainParser.parse("change g/2 h/1 i/7").getClass());
    }

    @Test
    void parse_validExitInput_expectExitCommand() throws HaBitParserException {
        assertEquals(ExitCommand.class, MainParser.parse("  exit aaa ").getClass());
    }

    @Test
    void parse_validReturnInput_expectReturnCommand() throws HaBitParserException {
        assertEquals(ReturnCommand.class, MainParser.parse("  return  aaa  ").getClass());

    }

    @Test
    void parse_validHelpInput_expectHelpCommand() throws HaBitParserException {
        assertEquals(HelpCommand.class, MainParser.parse(" help aaa  ").getClass());
        assertEquals(HelpCommand.class, MainParser.parse(" aaa ").getClass());
    }
}
