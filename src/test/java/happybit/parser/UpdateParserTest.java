package happybit.parser;

import happybit.command.UpdateGoalCommand;
import happybit.command.UpdateHabitCommand;

import happybit.exception.HaBitCommandException;
import happybit.exception.HaBitParserException;
import happybit.goal.Goal;
import happybit.goal.GoalList;
import happybit.goal.GoalType;
import happybit.storage.Storage;
import happybit.ui.PrintManager;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


class UpdateParserTest {

    private static final String ERROR_INVALID_UPDATE_COMMAND = "There is no update command for goals in this format, "
            + "do check your parameters one more time.";
    private static final String ERROR_INVALID_UPDATE_PARAMETERS = "Required flags for update command missing. "
            + "Please try again.";
    private static final String ERROR_INVALID_CHANGE_COMMAND = "There is no change command for habits in this "
            + "format, do check your parameters one more time.";
    private static final String ERROR_CHANGE_HABIT_INTERVAL_WITH_UPDATE_COMMAND = "There is no update command for "
            + "goals in this format, do check your parameters one more time.";
    private static final String ERROR_MISSING_REQUIRED_FLAGS = "Required flags for update command missing. "
            + "Please try again.";
    private static final String ERROR_UPDATE_GOAL_TYPE_WITH_CHANGE_COMMAND = "The command is missing the 'h/' flag.";
    private static final String ERROR_UPDATE_GOAL_END_DATE_WITH_CHANGE_COMMAND = "The command is missing the 'h/' "
            + "flag.";
    private static final String ERROR_INTEGER_FLAG_FORMAT = "The command is missing the '%1$s' flag.";
    private static final String ERROR_UPDATE_START_DATE = "The start date cannot be updated once set. Start on time!";
    private static final String ERROR_CHANGE_COMMAND_MISSING_INDEXES = "A goal index and a habit index has to be "
            + "provided using the g/ and h/ flags respectively for Change commands.";
    private static final String ERROR_NO_REQUIRED_FLAGS_UPDATE_COMMAND = "Required flags for update command missing. "
            + "Please try again.";
    private static final String ERROR_FLAG_INDEX_MISSING_PARAMETER = "Index expected after '%1$s' flag missing.";

    private static final String DATE_FORMAT = "ddMMyyyy";

    /*
    ===================================
    Tests for parseUpdateGoalCommands()
    ===================================
     */

    @Test
    void parseUpdateGoalCommands_validInputs_success() throws HaBitParserException {

        String changeNameNoExcess = "g/1 n/change name";
        UpdateGoalCommand test = (UpdateGoalCommand) UpdateParser.parseUpdateGoalCommands(changeNameNoExcess);
        assertEquals(0, test.getGoalIndex());
        assertEquals("change name", test.getNewGoalName());

        String changeNameWithExcess = "g/1 n/change name i/3";
        UpdateGoalCommand test1 = (UpdateGoalCommand) UpdateParser.parseUpdateGoalCommands(changeNameWithExcess);
        assertEquals(0, test1.getGoalIndex());
        assertEquals("change name", test1.getNewGoalName());
        assertEquals("i/", test1.getExcessAttributes().get(0));

        String changeNameAndEndDateNoExcess = "g/1 n/change name e/31122021";
        UpdateGoalCommand test2 = (UpdateGoalCommand) UpdateParser.parseUpdateGoalCommands(
                changeNameAndEndDateNoExcess);
        assertEquals(0, test2.getGoalIndex());
        assertEquals("change name", test2.getNewGoalName());
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        dateFormat.setLenient(false);
        Date expectedDate = null;

        try {
            expectedDate = dateFormat.parse("31122021");
        } catch (ParseException e) {
            fail();
        }

        assertEquals(expectedDate, test2.getNewGoalEndDate());

        String changeNameAndEndDateWithExcess = "g/1 n/change name e/31122021 h/1 i/5";
        UpdateGoalCommand test3 = (UpdateGoalCommand) UpdateParser.parseUpdateGoalCommands(
                changeNameAndEndDateWithExcess);
        assertEquals(0, test3.getGoalIndex());
        assertEquals("change name", test3.getNewGoalName());
        Date expectedDate1 = null;

        try {
            expectedDate1 = dateFormat.parse("31122021");
        } catch (ParseException e) {
            fail();
        }

        assertEquals(expectedDate1, test3.getNewGoalEndDate());
        assertEquals("h/", test3.getExcessAttributes().get(0));
        assertEquals("i/", test3.getExcessAttributes().get(1));

        String changeNameAndEndDateAndGoalTypeWithExcess = "g/1 n/change name e/31122021 t/ex i/3 h/2";
        UpdateGoalCommand test4 = (UpdateGoalCommand) UpdateParser.parseUpdateGoalCommands(
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
    void parseUpdateGoalCommands_invalidInputs_exceptionThrown() {

        try {
            UpdateParser.parseUpdateGoalCommands("dilly dally do 1 2 3");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(String.format(ERROR_INTEGER_FLAG_FORMAT, "g/"), e.getMessage());
        }

        try {
            UpdateParser.parseUpdateGoalCommands(" i/3 h/3");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(String.format(ERROR_INTEGER_FLAG_FORMAT, "g/"), e.getMessage());
        }

        try {
            UpdateParser.parseUpdateGoalCommands("g/1 i/3 h/3");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_INVALID_UPDATE_COMMAND, e.getMessage());
        }

        try {
            UpdateParser.parseUpdateGoalCommands("g/");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(String.format(ERROR_FLAG_INDEX_MISSING_PARAMETER, "g/"), e.getMessage());
        }

        try {
            UpdateParser.parseUpdateGoalCommands("g/5");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_NO_REQUIRED_FLAGS_UPDATE_COMMAND, e.getMessage());
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
        String changeNameAndIntervalNoExcess = "g/1 h/1 n/change name i/3";
        UpdateHabitCommand test = (UpdateHabitCommand) UpdateParser.parseUpdateHabitCommands(
                changeNameAndIntervalNoExcess);
        assertEquals(0, test.getGoalIndex());
        assertEquals(0, test.getHabitIndex());
        assertEquals("change name", test.getNewHabitName());
        assertEquals(3, test.getNewHabitInterval());

        String changeNameAndIntervalWithExcess = "g/1 h/1 n/change name i/3 t/ex e/31122021";
        UpdateHabitCommand test1 = (UpdateHabitCommand) UpdateParser.parseUpdateHabitCommands(
                changeNameAndIntervalWithExcess);
        assertEquals(0, test1.getGoalIndex());
        assertEquals(0, test1.getHabitIndex());
        assertEquals("change name", test1.getNewHabitName());
        assertEquals(3, test1.getNewHabitInterval());
        assertEquals("e/", test1.getExcessAttributes().get(0));
        assertEquals("t/", test1.getExcessAttributes().get(1));
    }

    @Test
    void parseUpdateHabitCommands_invalidInputs_exceptionThrown() {
        try {
            UpdateParser.parseUpdateHabitCommands("g/ h/");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(String.format(ERROR_FLAG_INDEX_MISSING_PARAMETER, "g/"), e.getMessage());
        }

        try {
            UpdateParser.parseUpdateHabitCommands("g/2 h/");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(String.format(ERROR_FLAG_INDEX_MISSING_PARAMETER, "h/"), e.getMessage());
        }

        try {
            UpdateParser.parseUpdateHabitCommands("g/2 h/5");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_NO_REQUIRED_FLAGS_UPDATE_COMMAND, e.getMessage());
        }

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

    @Test
    void updateGoalCommand_validInput_success() throws HaBitParserException, HaBitCommandException {
        GoalList goalList = new GoalList();
        PrintManager printManager = new PrintManager();
        Storage storage = new Storage();

        Goal goal = new Goal("Original name", GoalType.DEFAULT, stringToDateForTest("11112021"),
                stringToDateForTest("25122021"));
        goalList.addGoal(goal);
        UpdateGoalCommand updateGoalCommand = (UpdateGoalCommand) UpdateParser.parseUpdateGoalCommands(
                "g/1 n/change name e/31122021 t/ex");
        updateGoalCommand.runCommand(goalList, printManager, storage);
    }

    /*
    ===================================================================
    Test if able to assert handling of incorrect variables within flag.
    ===================================================================
     */

    @Test
    void parseUpdateGoalCommands_invalidParameterCombination_exceptionThrown() {
        String inputChangeHabitIntervalCommand = "g/6 h/2 i/1";
        String inputInvalidUpdateCommand = "g/5";

        try {
            UpdateParser.parseUpdateGoalCommands(inputChangeHabitIntervalCommand);
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_CHANGE_HABIT_INTERVAL_WITH_UPDATE_COMMAND, e.getMessage());
        }

        try {
            UpdateParser.parseUpdateGoalCommands(inputInvalidUpdateCommand);
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_INVALID_UPDATE_PARAMETERS, e.getMessage());
        }

    }

    /*
    ====================================
    Tests for parseUpdateHabitCommands()
    ====================================
     */

    @Test
    void parseUpdateHabitsCommands_invalidParameterCombination_exceptionThrown() {
        String inputUpdateGoalNameCommand = "g/2 n/new goal name";
        String inputUpdateGoalEndDateCommand = "g/6 e/25122050";
        String inputUpdateGoalTypeCommand = "g/3 t/sl";
        String inputInvalidChangeCommand = "g/1 h/2";

        try {
            UpdateParser.parseUpdateHabitCommands(inputUpdateGoalNameCommand);
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_CHANGE_COMMAND_MISSING_INDEXES, e.getMessage());
        }

        try {
            UpdateParser.parseUpdateHabitCommands(inputUpdateGoalEndDateCommand);
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_UPDATE_GOAL_END_DATE_WITH_CHANGE_COMMAND, e.getMessage());
        }

        try {
            UpdateParser.parseUpdateHabitCommands(inputUpdateGoalTypeCommand);
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_UPDATE_GOAL_TYPE_WITH_CHANGE_COMMAND, e.getMessage());
        }

        try {
            UpdateParser.parseUpdateHabitCommands(inputInvalidChangeCommand);
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_MISSING_REQUIRED_FLAGS, e.getMessage());
        }
    }

    /**
     * Converts a string formatted date into a Date object.
     * Original method stringToDate() is in UpdateParser. This method is used for testing.
     *
     * @param strDate Date written in String format
     * @return Date object of strDate
     */
    private static Date stringToDateForTest(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        formatter.setLenient(false);
        try {
            return formatter.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}