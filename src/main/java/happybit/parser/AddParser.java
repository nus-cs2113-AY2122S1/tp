package happybit.parser;

import happybit.command.AddGoalCommand;
import happybit.command.AddHabitCommand;
import happybit.command.Command;
import happybit.exception.HaBitParserException;
import happybit.goal.Goal;
import happybit.goal.GoalType;
import happybit.habit.Habit;

import java.nio.file.LinkPermission;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class AddParser {

    public static Command parseAddGoalCommand(String commandInstruction) throws HaBitParserException {
        checkNoDescription(commandInstruction);
        GoalType goalType = getTypeFlag(commandInstruction);
        Date[] dates = getDates(commandInstruction);
        Date startDate = dates[0];
        Date endDate = dates[1];
        String goalName = getGoalDescription(commandInstruction);
        Goal goal = new Goal(goalName, goalType, startDate, endDate);
        return new AddGoalCommand(goal);
    }

    public static Command parseAddHabitCommand(String commandInstruction) throws HaBitParserException {
        checkNoDescription(commandInstruction);
        int goalIndex = getGoalIndex(commandInstruction);
        String habitName = getHabitDescription(commandInstruction);
        Habit habit = new Habit(habitName);
        return new AddHabitCommand(habit, goalIndex);
    }

    /*
     * NOTE : ==================================================================
     * The following are private methods that are used to implement SLAP for the
     * above public methods. These methods are positioned at the bottom to better
     * visualise the actual methods that can be called from outside this class.
     * =========================================================================
     */

    /**
     * Checks if the input is null.
     *
     * @param input String of the user input.
     * @throws HaBitParserException If the user input is null (blank).
     */
    private static void checkNoDescription(String input) throws HaBitParserException {
        if (input == null) {
            throw new HaBitParserException("Command cannot be called without parameters.");
        }
    }

    // Section 1: Add Goal Methods

    // Section 1.1: Add Goal - GoalType Methods

    /**
     * Checks for flag errors before returning the GoalType based on user input.
     *
     * @param input String of the user input.
     * @return GoalType corresponding to flag in user input.
     * @throws HaBitParserException If there are syntax errors in the flag provided.
     */
    private static GoalType getTypeFlag(String input) throws HaBitParserException {
        checkMultipleFlags(input);
        checkImproperFlags(input);
        return checkTypeFlag(input);
    }

    /**
     * Checks for flag in user input and returns the GoalType.
     *
     * @param input String of the user input.
     * @return GoalType corresponding to flag in user input.
     */
    private static GoalType checkTypeFlag(String input) {
        if (input.contains(" -sl ")) {
            return GoalType.SLEEP;
        } else if (input.contains(" -fd ")) {
            return GoalType.FOOD;
        } else if (input.contains(" -ex ")) {
            return GoalType.EXERCISE;
        } else if (input.contains(" -sd ")) {
            return GoalType.STUDY;
        }
        return GoalType.DEFAULT;
    }

    /**
     * Checks if there are multiple flags in the user input.
     *
     * @param input String of the user input.
     * @throws HaBitParserException If there are multiple flags within the user input.
     */
    private static void checkMultipleFlags(String input) throws HaBitParserException {
        String[] flagArray = {"-sl", "-fd", "-ex", "-sd", "-df"};
        if (countInstancesInString(input, flagArray) > 1) {
            throw new HaBitParserException("Multiple goal flags detected.");
        }
    }

    /**
     * Checks the syntax of flags used in the user input.
     *
     * @param input String of the user input.
     * @throws HaBitParserException If there are errors in the flag syntax used.
     */
    private static void checkImproperFlags(String input) throws HaBitParserException {
        String[] improperFlagArray = {" -sl", " -fd", " -ex", " -sd", " -df",
                "-sl ", "-fd ", "-ex ", "-sd ", "-df ",
                "-sl", "-fd", "-ex", "-sd", "-df"};
        if (countInstancesInString(input, improperFlagArray) != 0) {
            throw new HaBitParserException("Flags should be defined with a space before and after.");
        }
    }

    /**
     * Counts the number of elements in the String array that appears in a given String.
     *
     * @param input         String to be checked.
     * @param instanceArray String array containing the String elements.
     * @return Integer number of String elements that appeared in the given String.
     */
    private static int countInstancesInString(String input, String[] instanceArray) {
        int count = 0;
        for (String s : instanceArray) {
            if (input.contains(s)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Checks if a given string has any flags.
     *
     * @param input         String to be checked.
     * @return Boolean value of whether any elements in the String array exists within the checked String.
     */
    private static boolean doesFlagExist(String input) {
        String[] flagArray = {"-sl", "-fd", "-ex", "-sd", "-df"};
        int count = 0;
        for (String s : flagArray) {
            if (input.contains(s)) {
                count++;
            }
        }
        return count != 0;
    }

    // Section 1.2: Add Goal - Date Methods

    /**
     * Checks for date errors before returning the dates based on user input.
     *
     * @param input String of the user input.
     * @return Date[] containing 2 dates: start and end date.
     * @throws HaBitParserException If there are errors in parsing the date.
     */
    private static Date[] getDates(String input) throws HaBitParserException {
        Date[] dates = new Date[2];
        if (numberOfDates(input) == 1) {
            dates = getDatesIfOneDate(input);
        } else if (numberOfDates(input) == 2) {
            dates = getDatesIfTwoDates(input);
        }
        checkDateNotBeforeToday(dates[0]);
        checkDateNotBeforeToday(dates[1]);
        checkStartDateBeforeOrEqualEndDate(dates[0], dates[1]);
        return dates;
    }

    /**
     * Find the number of dates given in the input.
     *
     * @param input String of the user input.
     * @return Integer number of dates given.
     * @throws HaBitParserException If number of dates < 1 or > 2.
     */
    private static int numberOfDates(String input) throws HaBitParserException {
        if (input.matches("(.*) /(.*) /(.*) /(.*)")) {
            throw new HaBitParserException("Too many dates provided.");
        } else if (input.matches("(.*) /(.*) /(.*)")) {
            return 2;
        } else if (input.matches("(.*) /(.*)")) {
            return 1;
        }
        throw new HaBitParserException("No dates provided.");
    }

    /**
     * Obtains the date from user input if only one date is specified.
     *
     * @param input String of the user input.
     * @return Date[] containing 2 dates: start and end date.
     * @throws HaBitParserException If there are errors in parsing the date.
     */
    private static Date[] getDatesIfOneDate(String input) throws HaBitParserException {
        Date[] dates = new Date[2];
        dates[0] = new Date();
        dates[1] = getFirstDate(input);
        return dates;
    }

    /**
     * Obtains the date from user input if only one date is specified.
     *
     * @param input String of the user input.
     * @return Date[] containing 2 dates: start and end date.
     * @throws HaBitParserException If there are errors in parsing the date.
     */
    private static Date[] getDatesIfTwoDates(String input) throws HaBitParserException {
        Date[] dates = new Date[2];
        dates[0] = getFirstDate(input);
        dates[1] = getSecondDate(input);
        return dates;
    }

    /**
     * Gets the first date specified in the user input.
     *
     * @param input String of the user input.
     * @return Date which is the first date specified.
     * @throws HaBitParserException If there are issues with the date formatting.
     */
    private static Date getFirstDate(String input) throws HaBitParserException {
        String strDate;
        try {
            int firstDateStartIndex = input.indexOf('/') + 1;
            strDate = input.substring(firstDateStartIndex, firstDateStartIndex + 8);
        } catch (StringIndexOutOfBoundsException e) {
            throw new HaBitParserException("Invalid date format. Use ddMMyyyy e.g, 28102021.");
        }
        return stringToDate(strDate);
    }

    /**
     * Gets the second date specified in the user input.
     *
     * @param input String of the user input.
     * @return Date which is the second date specified.
     * @throws HaBitParserException If there are issues with the date formatting.
     */
    private static Date getSecondDate(String input) throws HaBitParserException {
        String strDate;
        try {
            int secondDateStartIndex = input.indexOf('/', input.indexOf('/') + 1) + 1;
            strDate = input.substring(secondDateStartIndex, secondDateStartIndex + 8);
        } catch (StringIndexOutOfBoundsException e) {
            throw new HaBitParserException("Invalid date format. Use ddMMyyyy e.g, 28102021.");
        }
        return stringToDate(strDate);
    }

    /**
     * Converts a String into a Date.
     *
     * @param strDate String representation of a start or end date.
     * @return Date formatted from a string in the dd-MMM-yyyy format e.g, 07-Oct-2021.
     */
    private static Date stringToDate(String strDate) throws HaBitParserException {
        Date date;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
            date = formatter.parse(strDate);
        } catch (ParseException parseException) {
            throw new HaBitParserException("Invalid date format. Use ddMMyyyy e.g, 28102021.");
        }
        return date;
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
            throw new HaBitParserException("Specified date has to come after today's date");
        }
    }

    /**
     * Checks if the start date is before or equal to the end date.
     *
     * @throws HaBitParserException If start date is after the end date.
     */
    private static void checkStartDateBeforeOrEqualEndDate(Date startDate, Date endDate) throws HaBitParserException {
        if (startDate.compareTo(endDate) > 0) {
            throw new HaBitParserException("Start Date has to come before End Date.");
        }
    }

    // Section 1.3: Add Goal - Description Methods

    /**
     * Checks for description errors before returning the description based on user input.
     *
     * @param input String of the user input.
     * @return String containing description of the goal.
     */
    private static String getGoalDescription(String input) throws HaBitParserException {
        String description = input.substring(0, findGoalDescriptionEndIndex(input)).strip();
        if (description.isEmpty()) {
            throw new HaBitParserException("Description cannot be empty.");
        }
        return description;
    }

    /**
     * Finds the index + 1 of the last character of the description.
     *
     * @param input String of the user input.
     * @return Integer of the description's last character + 1.
     */
    private static int findGoalDescriptionEndIndex(String input) {
        int endIndex;
        if (doesFlagExist(input)) {
            endIndex = input.indexOf(" -");
        } else {
            endIndex = input.indexOf(" /");
        }
        return endIndex;
    }

    // Section 2: Add Habit Methods

    /**
     * Checks for goal index errors before returning the goal index based on user input.
     *
     * @param input String of the user input.
     * @return Integer index of the goal that the habit should be added to.
     * @throws HaBitParserException If the goal number was not specified or is not numeric.
     */
    private static int getGoalIndex(String input) throws HaBitParserException {
        String[] words = input.split(" ");
        int goalNumber;
        try {
            goalNumber = Integer.parseInt(words[0]);
        } catch (NumberFormatException e) {
            throw new HaBitParserException("Goal number has to be specified.");
        }
        return goalNumber - 1;
    }

    /**
     * Checks for description errors before returning the description based on user input.
     *
     * @param input String of the user input.
     * @return String description of the habit.
     * @throws HaBitParserException If habit description (name) is empty.
     */
    private static String getHabitDescription(String input) throws HaBitParserException {
        String description;
        try {
            description = input.substring(input.indexOf(' '));
        } catch (StringIndexOutOfBoundsException e) {
            throw new HaBitParserException("Habit name cannot be empty.");
        }
        if (description.isEmpty()) {
            throw new HaBitParserException("Habit name cannot be empty.");
        }
        return description;
    }

}
