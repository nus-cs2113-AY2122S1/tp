package happybit.parser;

import happybit.command.AddGoalCommand;
import happybit.command.AddHabitCommand;
import happybit.command.Command;
import happybit.exception.HaBitParserException;
import happybit.goal.Goal;
import happybit.goal.GoalType;
import happybit.habit.Habit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class AddParser extends Parser {

    private static final String ERROR_DATE_FORMAT = "Use the date format: 'ddMMyyyy'.";
    private static final String ERROR_END_DATE_FORMAT = "Use 'e/ddMMyyyy' to define the end date. Exp: e/25122021";
    private static final String ERROR_START_DATE_FORMAT = "Use 's/ddMMyyyy' to define the start date. Exp: s/25122021";
    private static final String ERROR_PAST_DATE = "All dates have to come after today's date";
    private static final String ERROR_CHRONOLOGICAL_DATE = "Start Date has to come before End Date.";

    private static final int START_DATE = 0;
    private static final int END_DATE = 1;
    private static final int FLAG_LENGTH = 2;

    /**
     * Parses user input to add a goal.
     *
     * @param commandInstruction User input containing command parameters.
     * @return Command to add goal.
     * @throws HaBitParserException If command parameters are not defined, or defined improperly.
     */
    public static Command parseAddGoalCommand(String commandInstruction) throws HaBitParserException {
        checkNoDescription(commandInstruction);
        Goal goal = getGoal(commandInstruction);
        return new AddGoalCommand(goal);
    }

    /**
     * Parses user input to add a habit.
     *
     * @param commandInstruction User input containing command parameters.
     * @return Command to add habit.
     * @throws HaBitParserException If command parameters are not defined, or defined improperly.
     */
    public static Command parseAddHabitCommand(String commandInstruction) throws HaBitParserException {
        checkNoDescription(commandInstruction);
        String[] parameters = splitInput(commandInstruction);
        int goalIndex = getNumber(parameters, FLAG_GOAL_INDEX) - 1;
        Habit habit = getHabit(commandInstruction);
        return new AddHabitCommand(habit, goalIndex);
    }

    /*
     * NOTE : ==================================================================
     * The following are private methods that are used to implement SLAP for the
     * above public methods. These methods are positioned at the bottom to better
     * visualise the actual methods that can be called from outside this class.
     * =========================================================================
     */

    // The following are parameter 'extractor' methods.

    /**
     * Gets the goal from user input.
     *
     * @param input User input.
     * @return Goal with details from user input.
     * @throws HaBitParserException If command parameters are not defined, or defined improperly.
     */
    private static Goal getGoal(String input) throws HaBitParserException {
        String[] parameters = splitInput(input);
        String goalName = getName(parameters).trim();
        GoalType goalType = getType(parameters);
        Date[] dates = getDate(parameters);
        Date startDate = dates[START_DATE];
        Date endDate = dates[END_DATE];
        return new Goal(goalName, goalType, startDate, endDate);
    }

    /**
     * Gets the habit from user input.
     * Habit is initialised with a null endDate first,
     * and altered in AddHabitCommand where there will be access to the goalList.
     *
     * @param input User input.
     * @return Habit with details from user input.
     * @throws HaBitParserException If command parameters are not defined, or defined improperly.
     */
    private static Habit getHabit(String input) throws HaBitParserException {
        String[] parameters = splitInput(input);
        String habitName = getName(parameters).trim();
        int interval = getNumber(parameters, FLAG_INTERVAL);
        if (interval > MAX_INTERVAL) {
            throw new HaBitParserException(ERROR_INTERVAL_TOO_LARGE);
        }
        return new Habit(habitName, interval);
    }

    /**
     * Gets the dates of the goal.
     *
     * @param parameters String array of command parameters.
     * @return Date array containing start and end dates.
     * @throws HaBitParserException If the date flag is used without fielding a date.
     */
    private static Date[] getDate(String[] parameters) throws HaBitParserException {
        Date[] dates = new Date[2];
        dates[START_DATE] = getStartDate(parameters);
        dates[END_DATE] = getEndDate(parameters);

        assert dates[START_DATE] != null;
        assert dates[END_DATE] != null;

        checkDateNotBeforeToday(dates[START_DATE]);
        checkDateNotBeforeToday(dates[END_DATE]);
        checkStartDateBeforeOrEqualEndDate(dates[START_DATE], dates[END_DATE]);
        return dates;
    }

    // The following are sub-methods of the main 'extractor' methods.

    /**
     * Converts a string to a date.
     *
     * @param strDate String representation of a date.
     * @return Date parsed from the string.
     * @throws HaBitParserException If the string fails to parse.
     */
    private static Date stringToDate(String strDate) throws HaBitParserException {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        formatter.setLenient(false);
        try {
            return formatter.parse(strDate);
        } catch (ParseException e) {
            throw new HaBitParserException(ERROR_DATE_FORMAT);
        }
    }

    /**
     * Gets start date from parameters.
     *
     * @param parameters String array of command parameters.
     * @return Start date.
     * @throws HaBitParserException If string fails to convert into a date.
     */
    private static Date getStartDate(String[] parameters) throws HaBitParserException {
        String strStartDate = getParameter(parameters, FLAG_START_DATE);
        if (strStartDate == null) {
            return new Date();
        } else if (strStartDate.equals(FLAG_START_DATE)) {
            throw new HaBitParserException(ERROR_START_DATE_FORMAT);
        }
        return stringToDate(strStartDate.substring(FLAG_LENGTH));
    }

    /**
     * Gets end date from parameters.
     *
     * @param parameters String array of command parameters.
     * @return End date.
     * @throws HaBitParserException If string fails to convert into a date.
     */
    private static Date getEndDate(String[] parameters) throws HaBitParserException {
        String strEndDate = getParameter(parameters, FLAG_END_DATE);
        if (strEndDate == null || strEndDate.equals(FLAG_END_DATE)) {
            throw new HaBitParserException(ERROR_END_DATE_FORMAT);
        }
        return stringToDate(strEndDate.substring(FLAG_LENGTH));
    }

    /**
     * Checks that the compared date does not fall before the current date.
     *
     * @throws HaBitParserException If the compared date falls before the current date.
     */
    private static void checkDateNotBeforeToday(Date compareDate) throws HaBitParserException {
        Instant now = Instant.now();
        Instant anHourBeforeNow = now.minus(1, ChronoUnit.HOURS);
        Date currentDate = Date.from(anHourBeforeNow);
        if (compareDate.compareTo(currentDate) < 0) {
            throw new HaBitParserException(ERROR_PAST_DATE);
        }
    }

    /**
     * Checks if the start date is before or equal to the end date.
     *
     * @throws HaBitParserException If start date is after the end date.
     */
    private static void checkStartDateBeforeOrEqualEndDate(Date startDate, Date endDate) throws HaBitParserException {
        if (startDate.compareTo(endDate) > 0) {
            throw new HaBitParserException(ERROR_CHRONOLOGICAL_DATE);
        }
    }

}
