package happybit.parser;

import happybit.command.UpdateGoalNameCommand;
import happybit.command.UpdateGoalEndDateCommand;
import happybit.command.UpdateGoalTypeCommand;
import happybit.command.UpdateHabitIntervalCommand;
import happybit.command.UpdateHabitNameCommand;

import happybit.exception.HaBitParserException;
import happybit.goal.GoalType;
import org.junit.jupiter.api.Test;

import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


class UpdateParserTest {

    private static final String ERROR_INVALID_UPDATE_COMMAND = "There is no update command for goals in this format, "
            + "do check your parameters one more time.\nDo not include more or less parameters than necessary.";
    private static final String ERROR_INVALID_CHANGE_COMMAND = "There is no change command for habits in this format, "
            + "do check your parameters one more time.\nDo not include more or less parameters than necessary.";
    private static final String ERROR_CHANGE_HABIT_NAME_WITH_UPDATE_COMMAND = "Are you perhaps trying to change a "
            + "habit name? Please use the 'change' command instead.";
    private static final String ERROR_CHANGE_HABIT_INTERVAL_WITH_UPDATE_COMMAND = "Are you perhaps trying to change a "
            + "habit interval? Please use the 'change' command instead.";
    private static final String ERROR_UPDATE_GOAL_NAME_WITH_CHANGE_COMMAND = "Are you perhaps trying to change a "
            + "goal name? Please use the 'update' command instead.";
    private static final String ERROR_UPDATE_GOAL_TYPE_WITH_CHANGE_COMMAND = "Are you perhaps trying to change a "
            + "goal type? Please use the 'update' command instead.";
    private static final String ERROR_UPDATE_GOAL_END_DATE_WITH_CHANGE_COMMAND = "Are you perhaps trying to change a "
            + "goal end date? Please use the 'update' command instead.";

    private static final String ERROR_GOAL_INDEX_FORMAT = "The command is missing the 'g/' flag";
    private static final String ERROR_GOAL_INDEX_NON_INTEGER = "The flag 'g/' has to be followed by a number";
    private static final String ERROR_GOAL_NAME_FORMAT = "Use the 'n/' flag to define the name. Exp: n/Foo";
    private static final String ERROR_GOAL_END_DATE_FORMAT = "Use the e/ flag to set the new goal end dateEg: "
            + "e/31122021";
    private static final String ERROR_GOAL_END_DATE_NON_DATE = "Enter your date in the format DDMMYYYY";
    private static final String ERROR_GOAL_TYPE_FORMAT = "Use the 't/' flag to define the goal type. Exp: t/df";
    private static final String ERROR_GOAL_UPDATE_FORMAT = "Missing additional goal name, goal end date, or "
            + "goal type parameter needed for update.";
    private static final String ERROR_HABIT_INDEX_FORMAT = "The command is missing the 'h/' flag";
    private static final String ERROR_HABIT_INDEX_NON_INTEGER = "The flag 'h/' has to be followed by a number";
    private static final String ERROR_INTERVAL_FORMAT = "The command is missing the 'i/' flag";
    private static final String ERROR_INTERVAL_NON_INTEGER = "The flag 'i/' has to be followed by a number";
    private static final String DATE_FORMAT = "ddMMyyyy";

    /*
    Tests for parseUpdateGoalCommands()
     */

    @Test
    void parseUpdateGoalCommands_userUpdateGoalAttribute_success() throws HaBitParserException {
        UpdateParser updateParser = new UpdateParser();

        String inputUpdateGoalName = "g/1 n/new goal name";
        String inputUpdateGoalEndDate = "g/2 e/25122050";
        String inputUpdateGoalType = "g/3 t/ex";

        UpdateGoalNameCommand updateGoalNameCommand =
                new UpdateGoalNameCommand(1, "new goal name");
        UpdateGoalEndDateCommand updateGoalEndDateCommand =
                new UpdateGoalEndDateCommand(2, stringToDateForTest("25122050"));
        UpdateGoalTypeCommand updateGoalTypeCommand =
                new UpdateGoalTypeCommand(3, GoalType.EXERCISE);

        UpdateGoalNameCommand actualUpdateGoalNameCommand =
                (UpdateGoalNameCommand) updateParser.parseUpdateGoalCommands(inputUpdateGoalName);
        UpdateGoalEndDateCommand actualUpdateGoalEndDateCommand =
                (UpdateGoalEndDateCommand) updateParser.parseUpdateGoalCommands(inputUpdateGoalEndDate);
        UpdateGoalTypeCommand actualUpdateGoalTypeCommand =
                (UpdateGoalTypeCommand) updateParser.parseUpdateGoalCommands(inputUpdateGoalType);

        assertEquals(updateGoalNameCommand.getNewGoalName(), actualUpdateGoalNameCommand.getNewGoalName());
        assertEquals(updateGoalEndDateCommand.getNewDate(), actualUpdateGoalEndDateCommand.getNewDate());
        assertEquals(updateGoalTypeCommand.getNewGoalType(), actualUpdateGoalTypeCommand.getNewGoalType());

    }

    /**
     * Converts a string formatted date into a Date object.
     * Original method stringToDate() is in UpdateParser. This method is used for testing.
     *
     * @param strDate Date written in String format
     * @return Date object of strDate
     * @throws ParseException If the String Date fails to be parsed
     */
    private static Date stringToDateForTest(String strDate) throws HaBitParserException {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        formatter.setLenient(false);
        try {
            return formatter.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Test
    void parseUpdateGoalCommands_invalidParameterCombination_exceptionThrown() {
        String inputChangeHabitNameCommand = "g/2 h/5 n/new habit name";
        String inputChangeHabitIntervalCommand = "g/6 h/2 i/1";
        String inputInvalidUpdateCommand = "g/5";
        String inputUpdateGoalNameWithExcessFlagsCommand = "g/1 n/new goal name e/25122021";

        try {
            UpdateParser.parseUpdateGoalCommands(inputChangeHabitNameCommand);
        } catch (HaBitParserException e) {
            assertEquals(ERROR_CHANGE_HABIT_NAME_WITH_UPDATE_COMMAND, e.getMessage());
        }

        try {
            UpdateParser.parseUpdateGoalCommands(inputChangeHabitIntervalCommand);
        } catch (HaBitParserException e) {
            assertEquals(ERROR_CHANGE_HABIT_INTERVAL_WITH_UPDATE_COMMAND, e.getMessage());
        }

        try {
            UpdateParser.parseUpdateGoalCommands(inputInvalidUpdateCommand);
        } catch (HaBitParserException e) {
            assertEquals(ERROR_INVALID_UPDATE_COMMAND, e.getMessage());
        }

        try {
            UpdateParser.parseUpdateGoalCommands(inputUpdateGoalNameWithExcessFlagsCommand);
        } catch (HaBitParserException e) {
            assertEquals(ERROR_INVALID_UPDATE_COMMAND, e.getMessage());
        }


    }

    /*
    Tests for parseUpdateHabitCommands()
     */

    @Test
    void parseUpdateHabitCommands_userUpdateHabitAttribute_success() throws HaBitParserException {
        UpdateParser updateParser = new UpdateParser();

        String inputUpdateHabitName = "g/1 h/1 n/new habit name";
        String inputUpdateHabitInterval = "g/2 h/2 i/365";

        UpdateHabitNameCommand updateHabitNameCommand =
                new UpdateHabitNameCommand(1, 1, "new habit name");
        UpdateHabitIntervalCommand updateHabitIntervalCommand =
                new UpdateHabitIntervalCommand(2, 2, 365);

        UpdateHabitNameCommand actualUpdateHabitNameCommand =
                (UpdateHabitNameCommand) updateParser.parseUpdateHabitCommands(inputUpdateHabitName);
        UpdateHabitIntervalCommand actualUpdateHabitIntervalCommand =
                (UpdateHabitIntervalCommand)  updateParser.parseUpdateHabitCommands(inputUpdateHabitInterval);

        assertEquals(updateHabitNameCommand.getNewHabitName(), actualUpdateHabitNameCommand.getNewHabitName());
        assertEquals(updateHabitIntervalCommand.getNewInterval(), actualUpdateHabitIntervalCommand.getNewInterval());
    }

    @Test
    void parseUpdateHabitsCommands_invalidParameterCombination_exceptionThrown() {
        String inputUpdateGoalNameCommand = "g/2 n/new goal name";
        String inputUpdateGoalEndDateCommand = "g/6 e/25122050";
        String inputUpdateGoalTypeCommand = "g/3 t/sl";
        String inputInvalidChangeCommand = "g/1 h/2";

        try {
            UpdateParser.parseUpdateHabitCommands(inputUpdateGoalNameCommand);
        } catch (HaBitParserException e) {
            assertEquals(ERROR_UPDATE_GOAL_NAME_WITH_CHANGE_COMMAND, e.getMessage());
        }

        try {
            UpdateParser.parseUpdateHabitCommands(inputUpdateGoalEndDateCommand);
        } catch (HaBitParserException e) {
            assertEquals(ERROR_UPDATE_GOAL_END_DATE_WITH_CHANGE_COMMAND, e.getMessage());
        }

        try {
            UpdateParser.parseUpdateHabitCommands(inputUpdateGoalTypeCommand);
        } catch (HaBitParserException e) {
            assertEquals(ERROR_UPDATE_GOAL_TYPE_WITH_CHANGE_COMMAND, e.getMessage());
        }

        try {
            UpdateParser.parseUpdateHabitCommands(inputInvalidChangeCommand);
        } catch (HaBitParserException e) {
            assertEquals(ERROR_INVALID_CHANGE_COMMAND, e.getMessage());
        }
    }

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
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
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

    @Test
    void parseUpdateGoalEndDateCommand_invalidStrDateFormat_exceptionThrown() {
        String inputWithAlphabet  = "CS2113T";
        String inputWithSpecialCharacters = "25122021!";

        try {
            UpdateParser.parseUpdateGoalEndDateCommand("g/1 e/" + inputWithAlphabet);
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_END_DATE_NON_DATE, e.getMessage());
        }

        try {
            UpdateParser.parseUpdateGoalEndDateCommand("g/1 e/" + inputWithSpecialCharacters);
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_END_DATE_NON_DATE, e.getMessage());
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
            assertEquals(ERROR_GOAL_TYPE_FORMAT, e.getMessage());
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

    /*
    Tests for remaining methods
     */


}
