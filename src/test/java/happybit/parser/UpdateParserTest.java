package happybit.parser;

import happybit.command.UpdateGoalEndDateCommand;
import happybit.command.UpdateGoalNameCommand;
import happybit.command.UpdateGoalTypeCommand;
import happybit.command.UpdateHabitIntervalCommand;
import happybit.exception.HaBitParserException;
import happybit.goal.GoalType;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class UpdateParserTest {

    private static final String ERROR_GOAL_INDEX_FORMAT = "The command is missing the 'g/' flag";
    private static final String ERROR_GOAL_INDEX_NON_INTEGER = "The flag 'g/' has to be followed by a number";
    private static final String ERROR_GOAL_NAME_FORMAT = "Use the 'n/' flag to define the name. Exp: n/Foo";
    private static final String ERROR_GOAL_END_DATE_FORMAT = "Use the e/ flag to set the new goal end dateEg:"
        + " e/31122021";
    private static final String ERROR_HABIT_INDEX_FORMAT = "The command is missing the 'h/' flag";
    private static final String ERROR_HABIT_INDEX_NON_INTEGER = "The flag 'h/' has to be followed by a number";
    private static final String ERROR_INTERVAL_FORMAT = "The command is missing the 'i/' flag";
    private static final String ERROR_INTERVAL_NON_INTEGER = "The flag 'i/' has to be followed by a number";

    /*
    Tests for parseUpdateGoalCommands()
     */

    /*
    Tests for parseUpdateHabitCommands()
     */

    /*
    Tests for parseUpdateGoalNameCommand()
     */

    @Test
    void parseUpdateGoalNameCommand_validInput_success() throws HaBitParserException {
        UpdateGoalNameCommand testCommand =
                (UpdateGoalNameCommand) UpdateParser.parseUpdateGoalNameCommand(" g/1 n/ New ");
        assertEquals(0, testCommand.getGoalIndex());
        assertEquals("New", testCommand.getNewGoalName());
    }

    @Test
    void parseUpdateGoalNameCommand_missingParameters_exceptionThrown() {
        try {
            UpdateParser.parseUpdateGoalNameCommand(" ");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_INDEX_FORMAT, e.getMessage());
        }
    }

    @Test
    void parseUpdateGoalNameCommand_missingGoalIndex_exceptionThrown() {
        try {
            UpdateParser.parseUpdateGoalNameCommand("g/");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_INDEX_FORMAT, e.getMessage());
        }
    }

    @Test
    void parseUpdateGoalNameCommand_nonIntegerGoalIndex_exceptionThrown() {
        try {
            UpdateParser.parseUpdateGoalNameCommand("g/A");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_INDEX_NON_INTEGER, e.getMessage());
        }

        try {
            UpdateParser.parseUpdateGoalNameCommand("g/$");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_INDEX_NON_INTEGER, e.getMessage());
        }
    }

    @Test
    void parseUpdateGoalNameCommand_missingGoalName_exceptionThrown() {
        try {
            UpdateParser.parseUpdateGoalNameCommand("g/1 n/ ");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_NAME_FORMAT, e.getMessage());
        }

        try {
            UpdateParser.parseUpdateGoalNameCommand("g/1 ");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_NAME_FORMAT, e.getMessage());
        }
    }

    @Test
    void parseUpdateGoalNameCommand_nullInput_exceptionThrown() {
        try {
            UpdateParser.parseUpdateGoalNameCommand(null);
            fail();
        } catch (HaBitParserException e) {
            assertEquals(Parser.ERROR_NO_PARAMS, e.getMessage());
        }
    }

    /*
    Tests for parseUpdateGoalEndDateCommand()
     */

    @Test
    void parseUpdateGoalEndDateCommand_validInput_success() throws HaBitParserException {
        UpdateGoalEndDateCommand testCommand =
                (UpdateGoalEndDateCommand) UpdateParser.parseUpdateGoalEndDateCommand(" g/1 e/25122021");
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
        formatter.setLenient(false);
        Date expectedDate = null;
        try {
            expectedDate = formatter.parse("25122021");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assertEquals(expectedDate, testCommand.getNewDate());
    }

    @Test
    void parseUpdateGoalEndDateCommand_missingParameters_exceptionThrown() {
        try {
            UpdateParser.parseUpdateGoalEndDateCommand(" ");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_INDEX_FORMAT, e.getMessage());
        }
    }

    @Test
    void parseUpdateGoalEndDateCommand_missingGoalIndex_exceptionThrown() {
        try {
            UpdateParser.parseUpdateGoalEndDateCommand("g/");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_INDEX_FORMAT, e.getMessage());
        }
    }

    @Test
    void parseUpdateGoalEndDateCommand_nonIntegerGoalIndex_exceptionThrown() {
        try {
            UpdateParser.parseUpdateGoalEndDateCommand("g/gg");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_INDEX_NON_INTEGER, e.getMessage());
        }

        try {
            UpdateParser.parseUpdateGoalEndDateCommand("g/.!");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_INDEX_NON_INTEGER, e.getMessage());
        }
    }

    @Test
    void parseUpdateGoalEndDateCommand_missingGoalEndDate_exceptionThrown() {
        try {
            UpdateParser.parseUpdateGoalEndDateCommand("g/1 e/ ");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_END_DATE_FORMAT, e.getMessage());
        }

        try {
            UpdateParser.parseUpdateGoalEndDateCommand("g/1 ");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_END_DATE_FORMAT, e.getMessage());
        }
    }

    @Test
    void parseUpdateGoalEndDateCommand_nullInput_exceptionThrown() {
        try {
            UpdateParser.parseUpdateGoalEndDateCommand(null);
            fail();
        } catch (HaBitParserException e) {
            assertEquals(Parser.ERROR_NO_PARAMS, e.getMessage());
        }
    }

    /*
    Tests for parseUpdateGoalTypeCommand()
     */

    @Test
    void parseUpdateGoalTypeCommand_validInput_success() throws HaBitParserException {
        UpdateGoalTypeCommand testCommand =
                (UpdateGoalTypeCommand) UpdateParser.parseUpdateGoalTypeCommand("g/5 t/ex");
        GoalType expectedType = GoalType.EXERCISE;
        assertEquals(expectedType, testCommand.getNewGoalType());
    }

    @Test
    void parseUpdateGoalTypeCommand_missingParameters_exceptionThrown() {
        try {
            UpdateParser.parseUpdateGoalTypeCommand(" ");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_INDEX_FORMAT, e.getMessage());
        }
    }

    @Test
    void parseUpdateGoalTypeCommand_missingGoalIndex_exceptionThrown() {
        try {
            UpdateParser.parseUpdateGoalTypeCommand("g/");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_INDEX_FORMAT, e.getMessage());
        }
    }

    @Test
    void parseUpdateGoalTypeCommand_nonIntegerGoalIndex_exceptionThrown() {
        try {
            UpdateParser.parseUpdateGoalTypeCommand("g/E");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_INDEX_NON_INTEGER, e.getMessage());
        }

        try {
            UpdateParser.parseUpdateGoalTypeCommand("g//");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_INDEX_NON_INTEGER, e.getMessage());
        }
    }

    @Test
    void parseUpdateGoalTypeCommand_missingGoalType_exceptionThrown() {
        try {
            UpdateParser.parseUpdateGoalTypeCommand("g/1 t/ ");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_NAME_FORMAT, e.getMessage());
        }

        try {
            UpdateParser.parseUpdateGoalTypeCommand("g/1 ");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_NAME_FORMAT, e.getMessage());
        }
    }

    @Test
    void parseUpdateGoalTypeCommand_nullInput_exceptionThrown() {
        try {
            UpdateParser.parseUpdateGoalTypeCommand(null);
            fail();
        } catch (HaBitParserException e) {
            assertEquals(Parser.ERROR_NO_PARAMS, e.getMessage());
        }
    }

    /*
    Tests for parseUpdateHabitNameCommand()
     */

    @Test
    void parseUpdateHabitNameCommand_validInput_success() throws HaBitParserException {

    }

    /*
    Tests for parseUpdateHabitIntervalCommand()
     */

    @Test
    void parseUpdateHabitIntervalCommand_validInput_success() throws HaBitParserException {
        UpdateHabitIntervalCommand testCommand =
                (UpdateHabitIntervalCommand) UpdateParser.parseUpdateHabitIntervalCommand("g/1 h/1 i/3");
        assertEquals(0, testCommand.getGoalIndex());
        assertEquals(0, testCommand.getHabitIndex());
        assertEquals(3, testCommand.getNewInterval());
    }

    @Test
    void parseUpdateHabitIntervalCommand_missingGoalIndex_exceptionThrown() {
        try {
            UpdateParser.parseUpdateHabitIntervalCommand("g/");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_INDEX_FORMAT, e.getMessage());
        }
    }

    @Test
    void parseUpdateHabitIntervalCommand_nonIntegerGoalIndex_exceptionThrown() {
        try {
            UpdateParser.parseUpdateHabitIntervalCommand("g/A");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_INDEX_NON_INTEGER, e.getMessage());
        }

        try {
            UpdateParser.parseUpdateHabitIntervalCommand("g/$");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_INDEX_NON_INTEGER, e.getMessage());
        }
    }

    @Test
    void parseUpdateHabitIntervalCommand_missingHabitIndex_exceptionThrown() {
        try {
            UpdateParser.parseUpdateHabitIntervalCommand("g/1 h/ ");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_HABIT_INDEX_FORMAT, e.getMessage());
        }

        try {
            UpdateParser.parseUpdateHabitIntervalCommand("g/1 n/ ");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_HABIT_INDEX_FORMAT, e.getMessage());
        }

        try {
            UpdateParser.parseUpdateHabitIntervalCommand("g/1 ");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_HABIT_INDEX_FORMAT, e.getMessage());
        }
    }

    @Test
    void parseUpdateHabitIntervalCommand_nonIntegerHabitIndex_exceptionThrown() {
        try {
            UpdateParser.parseUpdateHabitIntervalCommand("g/1 h/A");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_HABIT_INDEX_NON_INTEGER, e.getMessage());
        }

        try {
            UpdateParser.parseUpdateHabitIntervalCommand("g/1 h/$");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_HABIT_INDEX_NON_INTEGER, e.getMessage());
        }
    }

    @Test
    void parseUpdateHabitIntervalCommand_missingInterval_exceptionThrown() {
        try {
            UpdateParser.parseUpdateHabitIntervalCommand("g/1 h/1 i/ ");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_INTERVAL_FORMAT, e.getMessage());
        }

        try {
            UpdateParser.parseUpdateHabitIntervalCommand("g/1 h/1 n/ ");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_INTERVAL_FORMAT, e.getMessage());
        }

        try {
            UpdateParser.parseUpdateHabitIntervalCommand("g/1 h/1 ");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_INTERVAL_FORMAT, e.getMessage());
        }
    }

    @Test
    void parseUpdateHabitIntervalCommand_nonIntegerInterval_exceptionThrown() {
        try {
            UpdateParser.parseUpdateHabitIntervalCommand("g/1 h/1 i/A");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_INTERVAL_NON_INTEGER, e.getMessage());
        }

        try {
            UpdateParser.parseUpdateHabitIntervalCommand("g/1 h/1 i/$");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_INTERVAL_NON_INTEGER, e.getMessage());
        }
    }

    @Test
    void parseUpdateHabitIntervalCommand_nullInput_exceptionThrown() {
        try {
            UpdateParser.parseUpdateHabitIntervalCommand(null);
            fail();
        } catch (HaBitParserException e) {
            assertEquals(Parser.ERROR_NO_PARAMS, e.getMessage());
        }
    }


}
