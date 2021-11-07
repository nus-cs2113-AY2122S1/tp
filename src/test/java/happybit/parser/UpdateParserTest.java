package happybit.parser;

import happybit.command.UpdateGoalCommand;
//import happybit.command.UpdateGoalNameCommand;
//import happybit.command.UpdateGoalEndDateCommand;
//import happybit.command.UpdateGoalTypeCommand;
import happybit.command.UpdateHabitCommand;
//import happybit.command.UpdateHabitIntervalCommand;
//import happybit.command.UpdateHabitNameCommand;

import happybit.exception.HaBitParserException;
import happybit.goal.GoalType;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


class UpdateParserTest {

    /*
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
    private static final String ERROR_DATE_FORMAT = "Use the date format: 'ddMMyyyy'.";
    private static final String ERROR_GOAL_TYPE_FORMAT = "Use the 't/' flag to define the goal type. Exp: t/df";
    private static final String ERROR_GOAL_UPDATE_FORMAT = "Missing additional goal name, goal end date, or "
            + "goal type parameter needed for update.";
    private static final String ERROR_NO_DESCRIPTION = "Use a description of at least 1 character";
    private static final String ERROR_HABIT_INDEX_FORMAT = "The command is missing the 'h/' flag";
    private static final String ERROR_HABIT_INDEX_NON_INTEGER = "The flag 'h/' has to be followed by a number";
    private static final String ERROR_INTERVAL_FORMAT = "The flag 'i/' has to be followed by a number";
    private static final String ERROR_MISSING_INTERVAL = "The command is missing the 'i/' flag";
    private static final String ERROR_INTERVAL_NON_INTEGER = "The flag 'i/' has to be followed by a number";
    private static final String ERROR_GOAL_INDEX_NEGATIVE_NUM =
            "The flag 'g/' has to be followed by a positive integer";
    private static final String ERROR_GOAL_INDEX_ZERO_NUM =
            "The flag 'g/' has to be followed by a number greater than 0";
    private static final String ERROR_HABIT_INDEX_NEGATIVE_NUM =
            "The flag 'h/' has to be followed by a positive integer";
    private static final String ERROR_HABIT_INDEX_ZERO_NUM =
            "The flag 'h/' has to be followed by a number greater than 0";
    private static final String ERROR_INTERVAL_NEGATIVE_NUM =
            "The flag 'i/' has to be followed by a positive integer";
    private static final String ERROR_INTERVAL_ZERO_NUM =
            "The flag 'i/' has to be followed by a number greater than 0";
    private static final String ERROR_INTERVAL_TOO_LARGE = "Interval size is capped at 365 days.";

    private static final String DATE_FORMAT = "ddMMyyyy";
    private static final String INTERVAL_TOO_LARGE = "366";
    */
    private static final String ERROR_UPDATE_COMMAND_NO_GOAL_INDEX = "A goal index has to be provided with the "
            + "g/ flag for Update commands.";

    private static final String ERROR_INVALID_UPDATE_COMMAND = "There is no update command for goals in this format, "
            + "do check your parameters one more time.";

    private static final String ERROR_UPDATE_START_DATE = "The start date cannot be updated once set. Start on time!";

    private static final String ERROR_CHANGE_COMMAND_MISSING_INDEXES = "A goal index and a habit index has to be "
            + "provided using the g/ and h/ flags respectively for Change commands";

    private static final String ERROR_INVALID_CHANGE_COMMAND = "There is no change command for habits in this format, "
            + "do check your parameters one more time.";
    /*
    Tests for parseUpdateGoalCommands()
     */

    @Test
    void parseUpdateGoalCommands_validInputs_success() throws HaBitParserException {

        UpdateParser updateParser = new UpdateParser();
        String changeNameNoExcess = "g/1 n/change name";
        UpdateGoalCommand test = (UpdateGoalCommand) updateParser.parseUpdateGoalCommands(changeNameNoExcess);
        assertEquals(0, test.getGoalIndex());
        assertEquals("change name", test.getNewGoalName());

        String changeNameWithExcess = "g/1 n/change name i/3";
        UpdateGoalCommand test1 = (UpdateGoalCommand) updateParser.parseUpdateGoalCommands(changeNameWithExcess);
        assertEquals(0, test1.getGoalIndex());
        assertEquals("change name", test1.getNewGoalName());
        assertEquals("i/", test1.getExcessAttributes().get(0));

        String changeNameAndEndDateNoExcess = "g/1 n/change name e/31122021";
        UpdateGoalCommand test2 = (UpdateGoalCommand) updateParser.parseUpdateGoalCommands(
                changeNameAndEndDateNoExcess);
        assertEquals(0, test2.getGoalIndex());
        assertEquals("change name", test2.getNewGoalName());
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        Date expectedDate;
        try {
            expectedDate = dateFormat.parse("31122021");
        } catch (ParseException e) {
            throw new HaBitParserException(e.getMessage());
        }
        assertEquals(expectedDate, test2.getNewGoalEndDate());

        String changeNameAndEndDateWithExcess = "g/1 n/change name e/31122021 h/1 i/5";
        UpdateGoalCommand test3 = (UpdateGoalCommand) updateParser.parseUpdateGoalCommands(
                changeNameAndEndDateWithExcess);
        assertEquals(0, test3.getGoalIndex());
        assertEquals("change name", test3.getNewGoalName());
        Date expectedDate1;
        try {
            expectedDate1 = dateFormat.parse("31122021");
        } catch (ParseException e) {
            throw new HaBitParserException(e.getMessage());
        }
        assertEquals(expectedDate1, test3.getNewGoalEndDate());
        assertEquals("h/", test3.getExcessAttributes().get(0));
        assertEquals("i/", test3.getExcessAttributes().get(1));

        String changeNameAndEndDateAndGoalTypeWithExcess = "g/1 n/change name e/31122021 t/ex i/3 h/2";
        UpdateGoalCommand test4 = (UpdateGoalCommand) updateParser.parseUpdateGoalCommands(
                changeNameAndEndDateAndGoalTypeWithExcess);
        assertEquals(0, test4.getGoalIndex());
        assertEquals("change name", test4.getNewGoalName());
        Date expectedDate2;
        try {
            expectedDate2 = dateFormat.parse("31122021");
        } catch (ParseException e) {
            throw new HaBitParserException(e.getMessage());
        }
        assertEquals(expectedDate2, test4.getNewGoalEndDate());
        GoalType expectedGoalType = GoalType.EXERCISE;
        assertEquals(expectedGoalType, test4.getNewGoalType());
        assertEquals("h/", test4.getExcessAttributes().get(0));
        assertEquals("i/", test4.getExcessAttributes().get(1));

    }

    @Test
    void parseUpdateGoalCommands_invalidInputs_exceptionThrown() throws HaBitParserException {

        try {
            UpdateParser.parseUpdateGoalCommands(" i/3 h/3");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_UPDATE_COMMAND_NO_GOAL_INDEX, e.getMessage());
        }

        try {
            UpdateParser.parseUpdateGoalCommands("g/1 i/3 h/3");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_INVALID_UPDATE_COMMAND, e.getMessage());
        }

        try {
            UpdateParser.parseUpdateGoalCommands("g/1 n/test i/3 h/3 s/31122021");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_UPDATE_START_DATE, e.getMessage());
        }
    }

    @Test
    void parseUpdateHabitCommands_validInputs_success() throws HaBitParserException {
        UpdateParser updateParser = new UpdateParser();
        String changeNameAndIntervalNoExcess = "g/1 h/1 n/change name i/3";
        UpdateHabitCommand test = (UpdateHabitCommand) updateParser.parseUpdateHabitCommands(
                changeNameAndIntervalNoExcess);
        assertEquals(0, test.getGoalIndex());
        assertEquals(0, test.getHabitIndex());
        assertEquals("change name", test.getNewHabitName());
        assertEquals(3, test.getNewHabitInterval());

        String changeNameAndIntervalWithExcess = "g/1 h/1 n/change name i/3 t/ex e/31122021";
        UpdateHabitCommand test1 = (UpdateHabitCommand) updateParser.parseUpdateHabitCommands(
                changeNameAndIntervalWithExcess);
        assertEquals(0, test1.getGoalIndex());
        assertEquals(0, test1.getHabitIndex());
        assertEquals("change name", test1.getNewHabitName());
        assertEquals(3, test1.getNewHabitInterval());
        assertEquals("e/", test1.getExcessAttributes().get(0));
        assertEquals("t/", test1.getExcessAttributes().get(1));
    }

    @Test
    void parseUpdateHabitCommands_invalidInputs_exceptionThrown() throws HaBitParserException {
        try {
            UpdateParser.parseUpdateHabitCommands("t/ex e/31122021");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_CHANGE_COMMAND_MISSING_INDEXES, e.getMessage());
        }

        try {
            UpdateParser.parseUpdateHabitCommands("g/1 h/1 t/ex e/31122021");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_INVALID_CHANGE_COMMAND, e.getMessage());
        }

        try {
            UpdateParser.parseUpdateGoalCommands("g/1 h/1 t/ex e/31122021 s/31112021");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_UPDATE_START_DATE, e.getMessage());
        }
    }

    /*
    Able to assert handling of incorrect variables within flag due to tests below
     */

    /*
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
     * /
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
     * /

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
     * /

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
    void parseUpdateGoalNameCommand_negativeOrZeroGoalIndex_exceptionThrown() {
        try {
            UpdateParser.parseUpdateHabitIntervalCommand("g/-1 n/test");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_INDEX_NEGATIVE_NUM, e.getMessage());
        }

        try {
            UpdateParser.parseUpdateHabitIntervalCommand("g/0 n/test");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_INDEX_ZERO_NUM, e.getMessage());
        }
    }

    @Test
    void parseUpdateGoalNameCommand_missingGoalName_exceptionThrown() {
        try {
            UpdateParser.parseUpdateGoalNameCommand("g/1 n/ ");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_NO_DESCRIPTION, e.getMessage());
        }

        try {
            UpdateParser.parseUpdateGoalNameCommand("g/1 ");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_NAME_FORMAT, e.getMessage());
        }
    }

    /*
    Tests for parseUpdateGoalEndDateCommand()
     * /

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
    void parseUpdateGoalEndDateCommand_invalidStrDateFormat_exceptionThrown() {
        String inputWithAlphabet = "CS2113T";
        String inputWithSpecialCharacters = "25122021!";

        try {
            UpdateParser.parseUpdateGoalEndDateCommand("g/1 e/" + inputWithAlphabet);
        } catch (HaBitParserException e) {
            assertEquals(ERROR_DATE_FORMAT, e.getMessage());
        }

        try {
            UpdateParser.parseUpdateGoalEndDateCommand("g/1 e/" + inputWithSpecialCharacters);
        } catch (HaBitParserException e) {
            assertEquals(ERROR_DATE_FORMAT, e.getMessage());
        }
    }

    /*
    Tests for parseUpdateGoalTypeCommand()
     * /

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

    /*
    Tests for parseUpdateHabitNameCommand()
     * /

    @Test
    void parseUpdateHabitNameCommand_validInput_success() throws HaBitParserException {

    }

    /*
    Tests for parseUpdateHabitIntervalCommand()
     * /

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
    void parseUpdateHabitIntervalCommand_negativeOrZeroGoalIndex_exceptionThrown() {
        try {
            UpdateParser.parseUpdateHabitIntervalCommand("g/-1 h/1");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_INDEX_NEGATIVE_NUM, e.getMessage());
        }

        try {
            UpdateParser.parseUpdateHabitIntervalCommand("g/0 h/2");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_INDEX_ZERO_NUM, e.getMessage());
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
    void parseUpdateHabitIntervalCommand_negativeOrZeroHabitIndex_exceptionThrown() {
        try {
            UpdateParser.parseUpdateHabitIntervalCommand("g/1 h/-1");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_HABIT_INDEX_NEGATIVE_NUM, e.getMessage());
        }

        try {
            UpdateParser.parseUpdateHabitIntervalCommand("g/1 h/0");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_HABIT_INDEX_ZERO_NUM, e.getMessage());
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
            assertEquals(ERROR_MISSING_INTERVAL, e.getMessage());
        }

        try {
            UpdateParser.parseUpdateHabitIntervalCommand("g/1 h/1 ");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_MISSING_INTERVAL, e.getMessage());
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
    void parseUpdateHabitIntervalCommand_negativeOrZeroInterval_exceptionThrown() {
        try {
            UpdateParser.parseUpdateHabitIntervalCommand("g/1 h/1 i/-100");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_INTERVAL_NEGATIVE_NUM, e.getMessage());
        }

        try {
            UpdateParser.parseUpdateHabitIntervalCommand("g/1 h/1 i/0");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_INTERVAL_ZERO_NUM, e.getMessage());
        }
    }

    @Test
    void parseUpdateHabitIntervalCommand_intervalTooLarge_exceptionThrown() {
        try {
            UpdateParser.parseUpdateHabitIntervalCommand("g/1 h/1 i/" + INTERVAL_TOO_LARGE);
        } catch (HaBitParserException e) {
            assertEquals(ERROR_INTERVAL_TOO_LARGE, e.getMessage());
        }
    }
    */

    /*
    Tests for remaining methods
     */

}
