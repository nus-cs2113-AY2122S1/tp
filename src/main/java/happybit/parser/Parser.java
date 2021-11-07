package happybit.parser;

import happybit.exception.HaBitParserException;
import happybit.goal.GoalType;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    protected static final String LABEL_SYNTAX = "(?<=\\s|^)[a-zA-Z]/.*?((?=\\s+[a-zA-Z]/)|$)";
    protected static final String DATE_FORMAT = "ddMMyyyy";

    protected static final String FLAG_GOAL_INDEX = "g/";
    protected static final String FLAG_NAME = "n/";
    protected static final String FLAG_GOAL_TYPE = "t/";
    protected static final String FLAG_INTERVAL = "i/";
    protected static final String FLAG_START_DATE = "s/";
    protected static final String FLAG_END_DATE = "e/";
    protected static final String FLAG_HABIT_INDEX = "h/";

    private static final String SLEEP_LABEL = "sl";
    private static final String FOOD_LABEL = "fd";
    private static final String EXERCISE_LABEL = "ex";
    private static final String STUDY_LABEL = "sd";
    private static final String DEFAULT_LABEL = "df";

    private static final String ERROR_NAME_FORMAT = "Use the 'n/' flag to define the name. Exp: n/Foo";
    private static final String ERROR_GOAL_END_DATE_FORMAT = "Use the e/ flag to set the new goal end dateEg: ";
    private static final String ERROR_GOAL_TYPE_FORMAT = "Use the 't/' flag to define the goal type. Exp: t/df";
    private static final String ERROR_GOAL_UPDATE_FORMAT = "Missing additional goal name, goal end date, or "
            + "goal type parameter needed for update.";
    private static final String ERROR_INTEGER_FLAG_FORMAT = "The command is missing the '%1$s' flag";
    private static final String ERROR_CONVERT_NUM = "The flag '%1$s' has to be followed by a number";
    private static final String ERROR_UNDEFINED_GOAL_TYPE_LABEL =
            "Use the following goal types: 'sl', 'fd', 'ex', 'sd', 'df'";
    private static final String ERROR_NEGATIVE_NUM = "The flag '%1$s' has to be followed by a positive integer";
    private static final String ERROR_ZERO_NUM = "The flag '%1$s' has to be followed by a number greater than 0";
    protected static final String ERROR_INTERVAL_TOO_LARGE = "Interval size is capped at 365 days.";
    private static final String ERROR_NO_DESCRIPTION = "Use a description of at least 1 character";
    private static final String ERROR_LONG_DESCRIPTION = "Use a description no more than 50 characters "
            + "(current: %1$s characters)";

    private static final int FLAG_LENGTH = 2;
    private static final int MAX_NAME_LENGTH = 50;
    protected static final int MAX_INTERVAL = 365;

    /**
     * Splits the input into the various parameters.
     *
     * @param input User input.
     * @return String array containing the parameters.
     */
    protected static ArrayList<String> splitInput(String input) {
        Pattern pattern = Pattern.compile(LABEL_SYNTAX);
        Matcher matcher = pattern.matcher(input);
        ArrayList<String> listMatches = new ArrayList<>();
        while (matcher.find()) {
            listMatches.add(matcher.group());
        }
        return trimParameters(listMatches);
    }

    /**
     * Finds the parameters corresponding to the given label and returns it.
     *
     * @param parameters String array of command parameters.
     * @param label      Label of a parameter.
     * @return Parameter if it exists, null otherwise.
     */
    protected static String getParameter(ArrayList<String> parameters, String label) {
        for (String parameter : parameters) {
            if (parameter.contains(label)) {
                return parameter;
            }
        }
        return null;
    }

    /**
     * 'Type-casting' a Date to a LocalDate.
     *
     * @param date Date to be 'type-casted'.
     * @return LocalDate that has been 'type-casted' from Date.
     */
    protected static LocalDate convertDateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * 'Type-casting' a LocalDate to a Date.
     *
     * @param localDate LocalDate to be 'type-casted'.
     * @return Date that has been 'type-casted' from LocalDate.
     */
    protected static Date convertLocalDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Gets the name of a goal/habit from the user input.
     *
     * @param parameters String array of command parameters.
     * @return Name of a goal/habit.
     * @throws HaBitParserException If the name cannot be obtained from the user input.
     */
    protected static String getName(ArrayList<String> parameters) throws HaBitParserException {
        String nameWithFlag = getAndCheckParameter(parameters, FLAG_NAME, ERROR_NAME_FORMAT);
        String name = nameWithFlag.substring(FLAG_LENGTH).trim();
        checkStringLength(name);
        return name;
    }

    /**
     * Gets the number of a goal/habit from the user input.
     *
     * @param parameters String array of command parameters.
     * @param flag       Goal/habit flag.
     * @return Index of a goal/habit.
     * @throws HaBitParserException If the number cannot be obtained from the user input.
     */
    protected static int getNumber(ArrayList<String> parameters, String flag) throws HaBitParserException {
        String indexWithFlag = getAndCheckParameter(parameters, flag, String.format(ERROR_INTEGER_FLAG_FORMAT, flag));
        String index = indexWithFlag.substring(FLAG_LENGTH).trim();
        return stringToInt(index, flag);
    }

    /**
     * Gets the goal type.
     *
     * @param parameters String array of command parameters.
     * @return Goal type parameter.
     * @throws HaBitParserException If the goal type flag is used without fielding a proper goal type.
     */
    protected static GoalType getType(ArrayList<String> parameters) throws HaBitParserException {
        String flag = getParameter(parameters, FLAG_GOAL_TYPE);
        if (flag == null) {
            return GoalType.DEFAULT;
        } else if (flag.equals(FLAG_GOAL_TYPE)) {
            throw new HaBitParserException(ERROR_GOAL_TYPE_FORMAT);
        }
        return getGoalType(flag.substring(FLAG_LENGTH));
    }

    /**
     * Gets the index for goal / habit.
     * Checks more than or equal to 0.
     *
     * @param parameters String array of command parameters.
     * @param flag Flag of parameter being checked.
     * @return Integer of goal / habit user wanted.
     * @throws HaBitParserException If index entered by user is less than or equal to 0.
     */
    protected static int getIndex(ArrayList<String> parameters, String flag) throws HaBitParserException {
        int number = getNumber(parameters, flag);
        if (number == 0) {
            throw new HaBitParserException(String.format(ERROR_ZERO_NUM, flag));
        }
        return number - 1;
    }

    /**
     * Gets interval when user wants to update the interval of a habit.
     * Checks more than or equal to zero to update as interval cannot be 0 for update.
     * When adding new habit, interval can be 0.
     *
     * @param parameters String array of command parameters.
     * @param flag Flag of parameter being checked.
     * @return New interval to be changed to.
     * @throws HaBitParserException If interval is less than or equal to 0.
     */
    protected static int getUpdateInterval(ArrayList<String> parameters, String flag) throws HaBitParserException {
        int interval = getNumber(parameters, flag);
        if (interval == 0) {
            throw new HaBitParserException(String.format(ERROR_ZERO_NUM, flag));
        }
        return interval;
    }

    /*
     * NOTE : ==================================================================
     * The following are private methods that are used to implement SLAP for the
     * above public methods. These methods are positioned at the bottom to better
     * visualise the actual methods that can be called from outside this class.
     * =========================================================================
     */

    /**
     * Removes leading and trailing whitespaces if any.
     *
     * @param parameters String array of command parameters.
     * @return String array of command parameters that have been trimmed of leading/trailing whitespaces.
     */
    private static ArrayList<String> trimParameters(ArrayList<String> parameters) {
        for (int i = 0; i < parameters.size(); ++i) {
            String parameter = parameters.get(i);
            String trimmedParameter = parameter.substring(0,2).trim() + parameter.substring(2).trim();
            parameters.set(i, trimmedParameter);
        }
        return parameters;
    }

    /**
     * Gets the parameter from the parameter array and check its validity.
     *
     * @param parameters   String array of command parameters.
     * @param flag         Command flag.
     * @param errorMessage Error message to call if input parameter is invalid.
     * @return Parameter.
     * @throws HaBitParserException If parameter is absent.
     */
    private static String getAndCheckParameter(ArrayList<String> parameters, String flag, String errorMessage)
            throws HaBitParserException {
        String parameter = getParameter(parameters, flag);
        if (parameter == null) {
            throw new HaBitParserException(errorMessage);
        } else if (parameter.equals(FLAG_NAME) || parameter.equals(FLAG_INTERVAL)) {
            return parameter;
        } else if (parameter.equals(flag)) {
            throw new HaBitParserException(errorMessage);
        } else {
            return parameter;
        }
    }

    /**
     * Checks if the input is too long.
     *
     * @param input String of the goal/habit description.
     * @throws HaBitParserException If the input is more than 50 characters.
     */
    private static void checkStringLength(String input) throws HaBitParserException {
        if (input.isEmpty()) {
            throw new HaBitParserException(ERROR_NO_DESCRIPTION);
        } else if (input.length() > MAX_NAME_LENGTH) {
            throw new HaBitParserException(String.format(ERROR_LONG_DESCRIPTION, input.length()));
        }
    }

    /**
     * Checks if the input can be converted to an integer and is greater than or equal to 0.
     *
     * @param input Index as a string data type.
     * @return Index as an integer data type.
     * @throws HaBitParserException If the string cannot be converted to an integer, or integer greater than 0.
     */
    private static int stringToInt(String input, String flag) throws HaBitParserException {
        int number;
        try {
            number = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new HaBitParserException(String.format(ERROR_CONVERT_NUM, flag));
        }
        if (number < 0) {
            throw new HaBitParserException(String.format(ERROR_NEGATIVE_NUM, flag));
        }
        return number;
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
            throw new HaBitParserException(ERROR_UNDEFINED_GOAL_TYPE_LABEL);
        }
    }

}
