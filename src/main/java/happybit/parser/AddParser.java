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

    private static final String ERROR_GOAL_INDEX_FORMAT = "Use the 'g/' flag to define the goal index. Exp: g/2";
    private static final String ERROR_NAME_FORMAT = "Use the 'n/' flag to define the name. Exp: n/Foo";
    private static final String ERROR_GOAL_TYPE_FORMAT = "Use the 't/' flag to define the goal type. Exp: t/df";
    private static final String ERROR_INTERVAL_FORMAT = "Use the 'i/' flag to define the interval. Exp: i/7";
    private static final String ERROR_DATE_FORMAT = "Use the date format: 'ddMMyyyy'.";
    private static final String ERROR_END_DATE_FORMAT = "Use 'e/ddMMyyyy' to define the end date. Exp: e/25122021";
    private static final String ERROR_START_DATE_FORMAT = "Use 's/ddMMyyyy' to define the start date. Exp: s/25122021";
    private static final String ERROR_GOAL_INDEX_NON_INTEGER = "The goalIndex has to be a number.";
    private static final String ERROR_INTERVAL_NON_INTEGER = "The interval has to be a number.";
    private static final String ERROR_GOAL_TYPE_LABEL = "Use the following goal types: 'sl', 'fd', 'ex', 'sd', 'df'";
    private static final String ERROR_PAST_DATE = "All dates have to come after today's date";
    private static final String ERROR_CHRONOLOGICAL_DATE = "Start Date has to come before End Date.";
    private static final String SLEEP_LABEL = "sl";
    private static final String FOOD_LABEL = "fd";
    private static final String EXERCISE_LABEL = "ex";
    private static final String STUDY_LABEL = "sd";
    private static final String DEFAULT_LABEL = "df";

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
        int goalIndex = getGoalIndex(commandInstruction);
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
        int interval = getInterval(parameters);
        return new Habit(habitName, interval);
    }

    /**
     * Gets the goal index from user input.
     *
     * @param input User input.
     * @return Goal index.
     * @throws HaBitParserException If the goal index flag or goal index is absent, or non-integer goal index.
     */
    private static int getGoalIndex(String input) throws HaBitParserException {
        String[] parameters = splitInput(input);
        String goalIndex = getAndCheckParameter(parameters, FLAG_GOAL_INDEX, ERROR_GOAL_INDEX_FORMAT);
        return stringToInt(goalIndex.substring(2), ERROR_GOAL_INDEX_NON_INTEGER) - 1;
    }

    /**
     * Gets the name of the goal/habit.
     *
     * @param parameters String array of command parameters.
     * @return Name parameter.
     * @throws HaBitParserException If the name flag is absent, or used without fielding a name.
     */
    private static String getName(String[] parameters) throws HaBitParserException {
        String name = getAndCheckParameter(parameters, FLAG_NAME, ERROR_NAME_FORMAT);
        return name.substring(FLAG_LENGTH);
    }

    /**
     * Gets the goal type.
     *
     * @param parameters String array of command parameters.
     * @return Goal type parameter.
     * @throws HaBitParserException If the goal type flag is used without fielding a proper goal type.
     */
    private static GoalType getType(String[] parameters) throws HaBitParserException {
        String flag = getParameter(parameters, FLAG_GOAL_TYPE);
        if (flag == null) {
            return GoalType.DEFAULT;
        } else if (flag.equals(FLAG_GOAL_TYPE)) {
            throw new HaBitParserException(ERROR_GOAL_TYPE_FORMAT);
        }
        return getGoalType(flag.substring(FLAG_LENGTH));
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

    /**
     * Gets the interval of the habit.
     *
     * @param parameters String array of command parameters.
     * @return Interval parameter.
     * @throws HaBitParserException If the interval flag is used without fielding an interval, or non-integer interval.
     */
    private static int getInterval(String[] parameters) throws HaBitParserException {
        String interval = getAndCheckParameter(parameters, FLAG_INTERVAL, ERROR_INTERVAL_FORMAT);
        return stringToInt(interval.substring(FLAG_LENGTH), ERROR_INTERVAL_NON_INTEGER);
    }

    // The following are sub-methods of the main 'extractor' methods.

    /**
     * Gets the parameter from the parameter array and check its validity.
     *
     * @param parameters   String array of command parameters.
     * @param flag         Command flag.
     * @param errorMessage Error message to call if input parameter is invalid.
     * @return Parameter.
     * @throws HaBitParserException If parameter is absent.
     */
    private static String getAndCheckParameter(String[] parameters, String flag, String errorMessage)
            throws HaBitParserException {
        String parameter = getParameter(parameters, flag);
        if (parameter == null || parameter.equals(flag)) {
            throw new HaBitParserException(errorMessage);
        }
        return parameter;
    }

    /**
     * Converts a string to an integer.
     *
     * @param strInt       String representation of an integer.
     * @param errorMessage Error message to call if string fails to parse.
     * @return Integer parsed from the string.
     * @throws HaBitParserException If the string fails to parse.
     */
    private static int stringToInt(String strInt, String errorMessage) throws HaBitParserException {
        try {
            return Integer.parseInt(strInt);
        } catch (NumberFormatException e) {
            throw new HaBitParserException(errorMessage);
        }
    }

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
     * Gets Goal Type from a string label.
     *
     * @param label String containing label of goal type.
     * @return Goal type corresponding to string label.
     * @throws HaBitParserException If an invalid label is used.
     */
    private static GoalType getGoalType(String label) throws HaBitParserException {
        switch (label) {
        case SLEEP_LABEL:
            return GoalType.SLEEP;
        case FOOD_LABEL:
            return GoalType.FOOD;
        case EXERCISE_LABEL:
            return GoalType.EXERCISE;
        case STUDY_LABEL:
            return GoalType.STUDY;
        case DEFAULT_LABEL:
            return GoalType.DEFAULT;
        default:
            throw new HaBitParserException(ERROR_GOAL_TYPE_LABEL);
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
