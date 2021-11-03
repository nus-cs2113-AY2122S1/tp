package happybit.parser;

import happybit.command.AddGoalCommand;
import happybit.command.AddHabitCommand;
import happybit.exception.HaBitParserException;
import happybit.goal.Goal;
import happybit.habit.Habit;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class AddParserTest {

    private static final String ERROR_GOAL_INDEX_FORMAT = "Use the 'g/' flag to define the goal index. Exp: g/2";
    private static final String ERROR_NAME_FORMAT = "Use the 'n/' flag to define the name. Exp: n/Foo";
    private static final String ERROR_GOAL_TYPE_FORMAT = "Use the 't/' flag to define the goal type. Exp: t/df";
    private static final String ERROR_INTERVAL_FORMAT = "Use the 'i/' flag to define the interval. Exp: i/7";
    private static final String ERROR_DATE_FORMAT = "Use the date format: 'ddMMyyyy'.";
    private static final String ERROR_END_DATE_FORMAT = "Use 'e/ddMMyyyy' to define the end date. Exp: e/25122021";
    private static final String ERROR_START_DATE_FORMAT = "Use 's/ddMMyyyy' to define the start date. Exp: s/25122021";
    private static final String ERROR_GOAL_INDEX_NON_INTEGER = "The goalIndex has to be a number.";
    private static final String ERROR_INTERVAL_NON_INTEGER = "The interval has to be a number.";
    private static final String ERROR_INTERVAL_NEGATIVE = "The interval has to be a positive integer.";
    private static final String ERROR_GOAL_TYPE_LABEL = "Use the following goal types: 'sl', 'fd', 'ex', 'sd', 'df'";
    private static final String ERROR_PAST_DATE = "All dates have to come after today's date";
    private static final String ERROR_CHRONOLOGICAL_DATE = "Start Date has to come before End Date.";

    /*
     * NOTE : ==================================================================
     * The following are tests for parseAddGoalCommand.
     * =========================================================================
     */

    @Test
    void parseAddGoalCommand_validInputAllParameters_success() throws HaBitParserException, ParseException {
        AddGoalCommand testCommand = (AddGoalCommand)
                AddParser.parseAddGoalCommand(" n/ Test t/sd s/31122021 e/31122022");
        Goal testGoal = testCommand.getGoal();
        assertEquals("Test", testGoal.getGoalName());
        assertEquals("[SD]", testGoal.getGoalTypeCharacter());
        assertEquals("31122021", testGoal.getStringStartDate());
        assertEquals(new SimpleDateFormat("ddMMyyyy").parse("31122022"), testGoal.getEndDate());
    }

    @Test
    void parseAddGoalCommand_validInputMinimalParameters_success() throws HaBitParserException, ParseException {

        AddGoalCommand testCommand = (AddGoalCommand)
                AddParser.parseAddGoalCommand(" n/ Test  e/31122022");
        Goal testGoal = testCommand.getGoal();
        assertEquals("Test", testGoal.getGoalName());
        assertEquals("[DF]", testGoal.getGoalTypeCharacter());
        assertEquals(LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyy")), testGoal.getStringStartDate());
        assertEquals(new SimpleDateFormat("ddMMyyyy").parse("31122022"), testGoal.getEndDate());
    }

    @Test
    void parseAddGoalCommand_validInputGoalTypes_success() throws HaBitParserException {

        AddGoalCommand testCommand;
        testCommand = (AddGoalCommand)
                AddParser.parseAddGoalCommand(" n/ Test t/sl e/31122022");
        assertEquals("[SL]", testCommand.getGoal().getGoalTypeCharacter());
        testCommand = (AddGoalCommand)
                AddParser.parseAddGoalCommand(" n/ Test t/fd e/31122022");
        assertEquals("[FD]", testCommand.getGoal().getGoalTypeCharacter());
        testCommand = (AddGoalCommand)
                AddParser.parseAddGoalCommand(" n/ Test t/ex e/31122022");
        assertEquals("[EX]", testCommand.getGoal().getGoalTypeCharacter());
        testCommand = (AddGoalCommand)
                AddParser.parseAddGoalCommand(" n/ Test t/sd e/31122022");
        assertEquals("[SD]", testCommand.getGoal().getGoalTypeCharacter());
        testCommand = (AddGoalCommand)
                AddParser.parseAddGoalCommand(" n/ Test t/df e/31122022");
        assertEquals("[DF]", testCommand.getGoal().getGoalTypeCharacter());
    }

    @Test
    void parseAddGoalCommand_invalidType_exceptionThrown() {
        try {
            AddParser.parseAddGoalCommand(" n/ Test t/ e/31122022");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_TYPE_FORMAT, e.getMessage());
        }

        try {
            AddParser.parseAddGoalCommand(" n/ Test t/xx e/31122022");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_TYPE_LABEL, e.getMessage());
        }
    }

    @Test
    void parseAddGoalCommand_invalidName_exceptionThrown() {
        try {
            AddParser.parseAddGoalCommand(" n/  t/ e/31122022");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_NAME_FORMAT, e.getMessage());
        }

        try {
            AddParser.parseAddGoalCommand("  t/ e/31122022");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_NAME_FORMAT, e.getMessage());
        }
    }

    @Test
    void parseAddGoalCommand_invalidDateFormat_exceptionThrown() {
        try {
            AddParser.parseAddGoalCommand(" n/ Test  e/31-12-2022");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_DATE_FORMAT, e.getMessage());
        }

        try {
            AddParser.parseAddGoalCommand(" n/ Test  e/31/12/2022");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_DATE_FORMAT, e.getMessage());
        }
    }

    @Test
    void parseAddGoalCommand_invalidStartDateFormat_exceptionThrown() {
        try {
            AddParser.parseAddGoalCommand("n/ Test s/ e/31122022");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_START_DATE_FORMAT, e.getMessage());
        }
    }

    @Test
    void parseAddGoalCommand_invalidEndDateFormat_exceptionThrown() {
        try {
            AddParser.parseAddGoalCommand("n/ Test ");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_END_DATE_FORMAT, e.getMessage());
        }

        try {
            AddParser.parseAddGoalCommand("n/ Test e/");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_END_DATE_FORMAT, e.getMessage());
        }
    }

    @Test
    void parseAddGoalCommand_invalidDateChronology_exceptionThrown() {
        try {
            AddParser.parseAddGoalCommand("n/ Test s/31122020 e/31122022");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_PAST_DATE, e.getMessage());
        }

        try {
            AddParser.parseAddGoalCommand("n/ Test s/31122021 e/31122020");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_PAST_DATE, e.getMessage());
        }

        try {
            AddParser.parseAddGoalCommand("n/ Test s/31122022 e/31122021");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_CHRONOLOGICAL_DATE, e.getMessage());
        }
    }

    @Test
    void parseAddGoalCommand_nullInput_exceptionThrown() {
        try {
            AddParser.parseAddGoalCommand(null);
            fail();
        } catch (HaBitParserException e) {
            assertEquals(Parser.ERROR_NO_PARAMS, e.getMessage());
        }
    }

    /*
     * NOTE : ==================================================================
     * The following are tests for parseAddHabitCommand.
     * =========================================================================
     */

    @Test
    void parseAddHabitCommand_validInput_success() throws HaBitParserException {
        AddHabitCommand testCommand = (AddHabitCommand)
                AddParser.parseAddHabitCommand(" n/ Test  g/1 i/3  ");
        Habit habit = testCommand.getHabit();
        assertEquals(0, testCommand.getGoalIndex());
        assertEquals("Test", habit.getHabitName());
        assertEquals(3, habit.getIntervalLength());
    }

    @Test
    void parseAddHabitCommand_invalidGoalIndex_exceptionThrown() {
        try {
            AddParser.parseAddHabitCommand(" n/ Test  g/$ i/3  ");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_INDEX_NON_INTEGER, e.getMessage());
        }

        try {
            AddParser.parseAddHabitCommand(" n/ Test  g/ i/3  ");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_INDEX_FORMAT, e.getMessage());
        }
    }

    @Test
    void parseAddHabitCommand_invalidInterval_exceptionThrown() {
        try {
            AddParser.parseAddHabitCommand(" n/ Test  g/1 i/A  ");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_INTERVAL_NON_INTEGER, e.getMessage());
        }

        try {
            AddParser.parseAddHabitCommand(" n/ Test  g/1 i/  ");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_INTERVAL_FORMAT, e.getMessage());
        }
    }

    @Test
    void parseAddHabitCommand_negativeInterval_exceptionThrown() {
        try {
            AddParser.parseAddHabitCommand(" n/ Test  g/1 i/-1  ");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_INTERVAL_NEGATIVE, e.getMessage());
        }
    }

}
