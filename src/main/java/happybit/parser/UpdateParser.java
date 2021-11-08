package happybit.parser;

import happybit.command.Command;
import happybit.command.UpdateGoalCommand;
import happybit.command.UpdateHabitCommand;
import happybit.exception.HaBitParserException;
import happybit.goal.GoalType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;

public class UpdateParser extends Parser {

    private static final String ERROR_GOAL_END_DATE_FORMAT = "Use the e/ flag to set the new goal end date"
            + "Eg: e/31122021";
    private static final String ERROR_DATE_FORMAT = "Use the date format: 'ddMMyyyy'.";
    private static final String ERROR_INVALID_UPDATE_COMMAND = "There is no update command for goals in this format, "
            + "do check your parameters one more time.";
    private static final String ERROR_NO_REQUIRED_FLAGS_UPDATE_COMMAND = "Required flags for update command missing. "
            + "Please try again.";
    private static final String ERROR_INVALID_CHANGE_COMMAND = "There is no change command for habits in this format, "
            + "do check your parameters one more time.";
    private static final String ERROR_CHANGE_COMMAND_MISSING_INDEXES = "A goal index and a habit index has to be "
            + "provided using the g/ and h/ flags respectively for Change commands.";
    private static final String ERROR_UPDATE_START_DATE = "The start date cannot be updated once set. Start on time!";

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("ddMMyyyy");
    private static final int FLAG_LENGTH = 2;

    /**
     * Examines user input to extract which goal attribute(s) user wants to update.
     * Goal attributes that can be updated: Goal name, Goal Type, Goal End Date.
     *
     * @param input User input.
     * @return UpdateGoalCommand to specifically update the chosen goal attribute(s).
     * @throws HaBitParserException Thrown when user types an Update command  without a g/ flag.
     *
     */
    public static Command parseUpdateGoalCommands(String input) throws HaBitParserException {
        ArrayList<String> parameters = splitInput(input);

        // check if it contains n/, t/ and e/ flag
        String newGoalName = null;
        GoalType newGoalType = null;
        Date newGoalEndDate = null;

        ArrayList<Boolean> updateAttributes = getUpdateGoalAttributes(parameters);

        assert (input.contains(FLAG_GOAL_INDEX) && (input.contains(FLAG_NAME)
                || input.contains(FLAG_GOAL_TYPE) || input.contains(FLAG_END_DATE)));

        boolean isUpdateGoalName = updateAttributes.get(0);
        boolean isUpdateGoalType = updateAttributes.get(1);
        boolean isUpdateGoalEndDate = updateAttributes.get(2);
        if (isUpdateGoalName) {
            newGoalName = getName(parameters);
        }
        if (isUpdateGoalType) {
            newGoalType = getType(parameters);
        }
        if (isUpdateGoalEndDate) {
            newGoalEndDate = getDate(parameters);
        }
        int goalIndex = getIndex(parameters, FLAG_GOAL_INDEX);
        ArrayList<String> excessAttributes = getExcessGoalAttributes(parameters);
        return new UpdateGoalCommand(goalIndex, newGoalName, newGoalType, newGoalEndDate,
                updateAttributes, excessAttributes);
    }

    /**
     * Examines user input to extract which habit attribute(s) user wants to update.
     * Habit attributes that can be updated: Habit Name, Habit Interval.
     *
     * @param input User input.
     * @return UpdateHabitCommand to specifically update the chosen habit attribute(s).
     * @throws HaBitParserException Thrown when user types a Change command without g/ and h/ flags.
     */
    public static Command parseUpdateHabitCommands(String input) throws HaBitParserException {
        ArrayList<String> parameters = splitInput(input);

        String newHabitName = null;
        int newHabitInterval = 0;

        ArrayList<Boolean> updateAttributes = getUpdateHabitAttributes(parameters);

        assert (input.contains(FLAG_GOAL_INDEX) && input.contains(FLAG_HABIT_INDEX)
                && (input.contains(FLAG_NAME) || input.contains(FLAG_INTERVAL)));

        boolean isUpdateHabitName = updateAttributes.get(0);
        boolean isUpdateHabitInterval = updateAttributes.get(1);

        if (isUpdateHabitName) {
            newHabitName = getName(parameters);
        }
        if (isUpdateHabitInterval) {
            newHabitInterval = getUpdateInterval(parameters);
        }
        int goalIndex = getIndex(parameters, FLAG_GOAL_INDEX);
        int habitIndex = getIndex(parameters, FLAG_HABIT_INDEX);
        ArrayList<String> excessAttributes = getExcessHabitAttributes(parameters);
        return new UpdateHabitCommand(goalIndex, habitIndex, newHabitName, newHabitInterval,
                updateAttributes, excessAttributes);
    }

    /*
     * NOTE : ==================================================================
     * The following are private methods that are used to implement SLAP for the
     * above public methods. These methods are positioned at the bottom to better
     * visualise the actual methods that can be called from outside this class.
     * =========================================================================
     */

    /**
     * Confirms what attributes exist in the user input.
     * Finds the flags for those attributes (goal name, goal type, and goal end date).
     * Sets the updateAttributes Boolean ArrayList to true if and only if it finds the flag,
     * signalling what goal attributes the user wants to update.
     *
     * @param parameters ArrayList of parameters of user input.
     * @return ArrayList of boolean values that represent the presence or absence of a flag.
     * @throws HaBitParserException Thrown if user input does not contain required information.
     */
    private static ArrayList<Boolean> getUpdateGoalAttributes(ArrayList<String> parameters)
            throws HaBitParserException {
        ArrayList<Boolean> updateAttributes = new ArrayList<>();
        // check updateGoalName
        updateAttributes.add(isContainFlag(parameters, FLAG_NAME));
        updateAttributes.add(isContainFlag(parameters, FLAG_GOAL_TYPE));
        updateAttributes.add(isContainFlag(parameters, FLAG_END_DATE));

        if (nothingToUpdate(updateAttributes)) {
            int goalIndex = getIndex(parameters, FLAG_GOAL_INDEX);
            if (isContainFlag(parameters, FLAG_HABIT_INDEX) && isContainFlag(parameters, FLAG_INTERVAL)) {
                throw new HaBitParserException(ERROR_INVALID_UPDATE_COMMAND);
            } else if (isContainFlag(parameters, FLAG_GOAL_INDEX + (goalIndex + 1))) {
                throw new HaBitParserException(ERROR_NO_REQUIRED_FLAGS_UPDATE_COMMAND);
            }
        }
        return updateAttributes;
    }

    /**
     * Confirms what excess attributes exist in the user input.
     * Find excess flags and their attributes that user may have included accidentally.
     * Saves the excess flags in an ArrayList for printing to user later.
     *
     * @param parameters ArrayList of parameters of user input.
     * @return ArrayList containing (if any) excess flags from user input.
     * @throws HaBitParserException Thrown if user attempts to update start date.
     */
    private static ArrayList<String> getExcessGoalAttributes(ArrayList<String> parameters) throws HaBitParserException {
        ArrayList<String> excess = new ArrayList<>();
        if (isContainFlag(parameters, FLAG_HABIT_INDEX)) {
            excess.add(FLAG_HABIT_INDEX);
        }
        if (isContainFlag(parameters, FLAG_INTERVAL)) {
            excess.add(FLAG_INTERVAL);
        }
        if (isContainFlag(parameters, FLAG_START_DATE)) {
            throw new HaBitParserException(ERROR_UPDATE_START_DATE);
        }
        return excess;
    }

    /**
     * Confirms what attributes exist in the user input.
     * Finds the flags for those attributes (habit name, habit interval).
     * Updates the Boolean ArrayList updateAttributes if it finds the flag,
     * signalling what habit attributes the user wants to update.
     *
     * @param parameters ArrayList of parameters of user input.
     * @return ArrayList of Boolean that represents the presence or absence of a flag.
     * @throws HaBitParserException Thrown if user input does not contain any flags.
     */
    private static ArrayList<Boolean> getUpdateHabitAttributes(ArrayList<String> parameters)
            throws HaBitParserException {
        ArrayList<Boolean> updateAttributes = new ArrayList<>();
        updateAttributes.add(isContainFlag(parameters, FLAG_NAME));
        updateAttributes.add(isContainFlag(parameters, FLAG_INTERVAL));

        if (nothingToUpdate(updateAttributes)) {

            if (!isContainFlag(parameters, FLAG_GOAL_INDEX) && !isContainFlag(parameters, FLAG_HABIT_INDEX)) {
                throw new HaBitParserException(ERROR_CHANGE_COMMAND_MISSING_INDEXES);
            }

            int goalIndex = getIndex(parameters, FLAG_GOAL_INDEX);
            int habitIndex = getIndex(parameters, FLAG_HABIT_INDEX);

            if (isContainFlag(parameters, FLAG_GOAL_TYPE) || isContainFlag(parameters, FLAG_END_DATE)) {
                throw new HaBitParserException(ERROR_INVALID_CHANGE_COMMAND);
            } else if (isContainFlag(parameters, FLAG_GOAL_INDEX + (goalIndex + 1))
                    && isContainFlag(parameters, FLAG_HABIT_INDEX + (habitIndex + 1))) {
                throw new HaBitParserException(ERROR_NO_REQUIRED_FLAGS_UPDATE_COMMAND);
            } else {
                throw new HaBitParserException(ERROR_INVALID_CHANGE_COMMAND);
            }

        }

        if (!isContainFlag(parameters, FLAG_GOAL_INDEX) || !isContainFlag(parameters, FLAG_HABIT_INDEX)) {
            throw new HaBitParserException(ERROR_CHANGE_COMMAND_MISSING_INDEXES);
        }
        
        return updateAttributes;
    }

    /**
     * Confirms what excess attributes exist in the user input.
     * Find excess flags and their attributes that user may have included accidentally.
     * Saves the excess flags in an ArrayList for printing to user later.
     *
     * @param parameters ArrayList of parameters of user input.
     * @return ArrayList containing (if any) excess flags from user input.
     * @throws HaBitParserException Thrown if user attempts to update start date.
     */
    private static ArrayList<String> getExcessHabitAttributes(ArrayList<String> parameters)
            throws HaBitParserException {
        ArrayList<String> excess = new ArrayList<>();
        if (isContainFlag(parameters, FLAG_END_DATE)) {
            excess.add(FLAG_END_DATE);
        }
        if (isContainFlag(parameters, FLAG_GOAL_TYPE)) {
            excess.add(FLAG_GOAL_TYPE);
        }
        if (isContainFlag(parameters, FLAG_START_DATE)) {
            throw new HaBitParserException(ERROR_UPDATE_START_DATE);
        }
        return excess;
    }

    /**
     * Checks the Boolean ArrayList to find if any attributes the user wishes to update.
     *
     * @param updateAttributes Boolean ArrayList containing that represents the presence or absence of a flag.
     * @return True if there exists at least one attribute to be updated. False otherwise.
     */
    private static boolean nothingToUpdate(ArrayList<Boolean> updateAttributes) {
        for (boolean isUpdate : updateAttributes) {
            if (isUpdate) {
                return false;
            }
        }
        return true;
    }

    /**
     * Scans the ArrayList to ascertain whether a certain flag is in it.
     *
     * @param parameters ArrayList of parameters of user input.
     * @param flag Flag that prefixes a parameter to denote what type of parameter is entered.
     * @return True if there exists said flag in the ArrayList, false otherwise.
     */
    private static boolean isContainFlag(ArrayList<String> parameters, String flag) {
        for (String param : parameters) {
            if (param.contains(flag)) {
                return true;
            }
        }
        return false;
    }

    private static Date getDate(ArrayList<String> parameters) throws HaBitParserException {
        String strEndDate = getParameter(parameters, FLAG_END_DATE);
        if (strEndDate == null || strEndDate.equals(FLAG_END_DATE)) {
            throw new HaBitParserException(ERROR_GOAL_END_DATE_FORMAT);
        }
        assert (strEndDate.length() > FLAG_LENGTH);
        return stringToDate(strEndDate.substring(FLAG_LENGTH));
    }

    /**
     * Converts a string formatted date into a Date object.
     *
     * @param strDate Date written in String format
     * @return Date object of strDate
     * @throws HaBitParserException If the String Date fails to be parsed
     */
    private static Date stringToDate(String strDate) throws HaBitParserException {
        LocalDate parsedDate;
        try {
            parsedDate = LocalDate.parse(strDate, DATE_TIME_FORMATTER);
            return convertLocalDateToDate(parsedDate);
        } catch (DateTimeParseException e) {
            throw new HaBitParserException(ERROR_DATE_FORMAT);
        }
    }
}
