package happybit.parser;

import happybit.command.Command;
import happybit.command.UpdateGoalCommand;
import happybit.command.UpdateGoalEndDateCommand;
import happybit.command.UpdateGoalNameCommand;
import happybit.command.UpdateGoalTypeCommand;
import happybit.command.UpdateHabitCommand;
import happybit.command.UpdateHabitIntervalCommand;
import happybit.command.UpdateHabitNameCommand;
import happybit.exception.HaBitParserException;
import happybit.goal.GoalType;

import javax.lang.model.type.ArrayType;
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
    private static final String ERROR_INVALID_CHANGE_COMMAND = "There is no change command for habits in this format, "
            + "do check your parameters one more time.";
    private static final String ERROR_UPDATE_COMMAND_NO_GOAL_INDEX = "A goal index has to be provided with the "
            + "g/ flag for Update commands.";
    private static final String ERROR_CHANGE_COMMAND_MISSING_INDEXES = "A goal index and a habit index has to be "
            + "provided using the g/ and h/ flags respectively for Change commands";
    private static final String ERROR_UPDATE_START_DATE = "The start date cannot be updated once set. Start on time!";

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

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("ddMMyyyy");
    private static final int FLAG_LENGTH = 2;

    /**
     * Examines user input to decide which goal attribute user wants to update.
     *
     * @param input User input.
     * @return Parse command specifically for updating the chosen goal attribute.
     * @throws HaBitParserException Thrown when parameters given are for changing habit rather than updating goal
     *                              or when parameters are not in the expected format.
     *
     */
    public static Command parseUpdateGoalCommands(String input) throws HaBitParserException {
        ArrayList<String> parameters = splitInput(input);
        // check if contains FLAG_GOAL_INDEX
        if (!isContainFlag(parameters, FLAG_GOAL_INDEX)) {
            throw new HaBitParserException(ERROR_UPDATE_COMMAND_NO_GOAL_INDEX);
        }
        assert (input.contains(FLAG_GOAL_INDEX));
        int goalIndex = getIndex(parameters, FLAG_GOAL_INDEX);
        // check if it contains n/, t/ and e/ flag
        // if yes, mark as 1 in int array
        String newGoalName = null;
        GoalType newGoalType = null;
        Date newGoalEndDate = null;
        int[] updateAttributes = getUpdateGoalAttributes(parameters);
        if (updateAttributes[0] == 1) {
            newGoalName = getName(parameters);
        }
        if (updateAttributes[1] == 1) {
            newGoalType = getType(parameters);
        }
        if (updateAttributes[2] == 1) {
            newGoalEndDate = getDate(parameters);
        }
        ArrayList<String> excessAttributes = getExcessGoalAttributes(parameters);
        return new UpdateGoalCommand(goalIndex, newGoalName, newGoalType, newGoalEndDate,
                updateAttributes, excessAttributes);
    }

    /**
     * Examines user input to decide which goal attribute user wants to update.
     *
     * @param input User input.
     * @return Parse command specifically for updating the chosen goal attribute.
     * @throws HaBitParserException Thrown when parameters given are for changing habit rather than updating goal
     *                              or when parameters are not in the expected format.
     *
    public static Command parseUpdateGoalCommands(String input) throws HaBitParserException {
        ArrayList<String> parameters = splitInput(input); 
        assert (input.contains(FLAG_GOAL_INDEX));
        assert (!input.isBlank());
        if (isUpdateGoalName(parameters)) {
            return parseUpdateGoalNameCommand(input);
        }
        if (isUpdateGoalType(parameters)) {
            return parseUpdateGoalTypeCommand(input);
        }
        if (isUpdateGoalEndDate(parameters)) {
            return parseUpdateGoalEndDateCommand(input);
        }

        if (isChangeHabitName(parameters)) {
            throw new HaBitParserException(ERROR_CHANGE_HABIT_NAME_WITH_UPDATE_COMMAND);
        } else if (isChangeHabitInterval(parameters)) {
            throw new HaBitParserException(ERROR_CHANGE_HABIT_INTERVAL_WITH_UPDATE_COMMAND);
        } else {
            throw new HaBitParserException(ERROR_INVALID_UPDATE_COMMAND);
        }
    }
    */

    public static Command parseUpdateHabitCommands(String input) throws HaBitParserException {
        ArrayList<String> parameters = splitInput(input);
        if (!isContainFlag(parameters, FLAG_GOAL_INDEX) || !isContainFlag(parameters, FLAG_HABIT_INDEX)) {
            throw new HaBitParserException(ERROR_CHANGE_COMMAND_MISSING_INDEXES);
        }
        assert (input.contains(FLAG_GOAL_INDEX));
        assert (input.contains(FLAG_HABIT_INDEX));
        int goalIndex = getIndex(parameters, FLAG_GOAL_INDEX);
        int habitIndex = getIndex(parameters, FLAG_HABIT_INDEX);
        String newHabitName = null;
        int newHabitInterval = 0;
        int[] updateAttributes = getUpdateHabitAttributes(parameters);
        if (updateAttributes[0] == 1) {
            newHabitName = getName(parameters);
        }
        if (updateAttributes[1] == 1) {
            newHabitInterval = getUpdateInterval(parameters, FLAG_INTERVAL);
        }
        ArrayList<String> excessAttributes = getExcessHabitAttributes(parameters);
        return new UpdateHabitCommand(goalIndex, habitIndex, newHabitName, newHabitInterval,
                updateAttributes, excessAttributes);
    }


    /**
     * Examines user input to decide which habit attribute user wants to change.
     *
     * @param input User input.
     * @return Parse command specifically for updating the chosen habit attribute.
     * @throws HaBitParserException Thrown when parameters given are for updating goal rather than changing habits
     *                              or when parameters are not the same
     *
    public static Command parseUpdateHabitCommands(String input) throws HaBitParserException {
        ArrayList<String> parameters = splitInput(input);
        assert (input.contains(FLAG_GOAL_INDEX));
        assert (!input.isBlank());
        if (isChangeHabitName(parameters)) {
            return parseUpdateHabitNameCommand(input);
        }
        if (isChangeHabitInterval(parameters)) {
            return parseUpdateHabitIntervalCommand(input);
        }

        if (isUpdateGoalName(parameters)) {
            throw new HaBitParserException(ERROR_UPDATE_GOAL_NAME_WITH_CHANGE_COMMAND);
        } else if (isUpdateGoalType(parameters)) {
            throw new HaBitParserException(ERROR_UPDATE_GOAL_TYPE_WITH_CHANGE_COMMAND);
        } else if (isUpdateGoalEndDate(parameters)) {
            throw new HaBitParserException(ERROR_UPDATE_GOAL_END_DATE_WITH_CHANGE_COMMAND);
        } else {
            throw new HaBitParserException(ERROR_INVALID_CHANGE_COMMAND);
        }
    }
    */

    /**
     * Parses detail from user into goalIndex and newGoalName (information) to create a new Command.
     *
     * @param input Input typed by the user.
     * @return A Command class with the goalIndex and newGoalName.
     * @throws HaBitParserException If command parameters are not defined, or defined improperly.
     */
    public static Command parseUpdateGoalNameCommand(String input) throws HaBitParserException {
        ArrayList<String> parameters = splitInput(input);
        int goalIndex = getIndex(parameters, FLAG_GOAL_INDEX);
        String newGoalName = getName(parameters);
        return new UpdateGoalNameCommand(goalIndex, newGoalName);
    }

    public static Command parseUpdateGoalEndDateCommand(String input) throws HaBitParserException {
        ArrayList<String> parameters = splitInput(input);
        int goalIndex = getIndex(parameters, FLAG_GOAL_INDEX);
        Date newDate = getDate(parameters);
        return new UpdateGoalEndDateCommand(goalIndex, newDate);
    }

    /**
     * Parses detail from user into goalIndex and newGoalName (information) to create a new Command.
     *
     * @param input Input typed by the user.
     * @return A Command class with the goalIndex and newGoalName.
     * @throws HaBitParserException If command parameters are not defined, or defined improperly.
     */
    public static Command parseUpdateGoalTypeCommand(String input) throws HaBitParserException {
        ArrayList<String> parameters = splitInput(input);
        int goalIndex = getIndex(parameters, FLAG_GOAL_INDEX);
        GoalType newGoalType = getType(parameters);
        return new UpdateGoalTypeCommand(goalIndex, newGoalType);
    }

    /**
     * Parses detail from user into goalIndex, habitIndex, and newHabitName (information) to create a new Command.
     *
     * @param input Input typed by the user.
     * @return A Command class with the goalIndex, habitIndex, and newHabitName.
     * @throws HaBitParserException If command parameters are not defined, or defined improperly.
     */
    public static Command parseUpdateHabitNameCommand(String input) throws HaBitParserException {
        ArrayList<String> parameters = splitInput(input);
        int goalIndex = getIndex(parameters, FLAG_GOAL_INDEX);
        int habitIndex = getIndex(parameters, FLAG_HABIT_INDEX);
        String newHabitName = getName(parameters);
        return new UpdateHabitNameCommand(goalIndex, habitIndex, newHabitName);
    }

    /**
     * Parser to parse user's command of updating interval.
     *
     * @param commandInstruction Input from user.
     * @return A Command instance for UpdateHabitIntervalCommand with the goalIndex, habitIndex and interval
     * @throws HaBitParserException If command parameters are not defined, or defined improperly
     */
    public static Command parseUpdateHabitIntervalCommand(String commandInstruction) throws HaBitParserException {
        ArrayList<String> parameters = splitInput(commandInstruction);
        int goalIndex = getIndex(parameters, FLAG_GOAL_INDEX);
        int habitIndex = getIndex(parameters, FLAG_HABIT_INDEX);
        int interval = getUpdateInterval(parameters, FLAG_INTERVAL);
        if (interval > MAX_INTERVAL) {
            throw new HaBitParserException(ERROR_INTERVAL_TOO_LARGE);
        }
        assert (goalIndex >= 0);
        assert (habitIndex >= 0);
        assert (interval > 0);
        return new UpdateHabitIntervalCommand(goalIndex, habitIndex, interval);
    }

    /*
     * NOTE : ==================================================================
     * The following are private methods that are used to implement SLAP for the
     * above public methods. These methods are positioned at the bottom to better
     * visualise the actual methods that can be called from outside this class.
     * =========================================================================
     */

    private static int[] getUpdateGoalAttributes(ArrayList<String> parameters) throws HaBitParserException {
        int[] updateAttributes = new int[3];
        // check updateGoalName
        if (isContainFlag(parameters, FLAG_NAME)) {
            updateAttributes[0] = 1;
        }
        if (isContainFlag(parameters, FLAG_GOAL_TYPE)) {
            updateAttributes[1] = 1;
        }
        if (isContainFlag(parameters, FLAG_END_DATE)) {
            updateAttributes[2] = 1;
        }
        if (nothingToUpdate(updateAttributes)) {
            throw new HaBitParserException(ERROR_INVALID_UPDATE_COMMAND);
        }
        return updateAttributes;
    }

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

    private static int[] getUpdateHabitAttributes(ArrayList<String> parameters) throws HaBitParserException {
        int[] updateAttributes = new int[2];
        if (isContainFlag(parameters, FLAG_NAME)) {
            updateAttributes[0] = 1;
        }
        if (isContainFlag(parameters, FLAG_INTERVAL)) {
            updateAttributes[1] = 1;
        }
        if (nothingToUpdate(updateAttributes)) {
            throw new HaBitParserException(ERROR_INVALID_CHANGE_COMMAND);
        }
        return updateAttributes;
    }

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

    private static boolean nothingToUpdate(int[] updateAttributes) {
        for (int attribute : updateAttributes) {
            if (attribute == 1) {
                return false;
            }
        }
        return true;
    }

    private static boolean isContainFlag(ArrayList<String> parameters, String flag) {
        for (String param : parameters) {
            if (param.contains(flag)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isUpdateGoalName(ArrayList<String> parameters) {
        return isContainFlag(parameters, FLAG_GOAL_INDEX) && isContainFlag(parameters, FLAG_NAME)
                && isContainNoOtherFlag(parameters, FLAG_GOAL_INDEX, FLAG_NAME);
    }

    private static boolean isUpdateGoalType(ArrayList<String> parameters) {
        return isContainFlag(parameters, FLAG_GOAL_INDEX) && isContainFlag(parameters, FLAG_GOAL_TYPE)
                && isContainNoOtherFlag(parameters, FLAG_GOAL_INDEX, FLAG_GOAL_TYPE);
    }

    private static boolean isUpdateGoalEndDate(ArrayList<String> parameters) {
        return isContainFlag(parameters, FLAG_GOAL_INDEX) && isContainFlag(parameters, FLAG_END_DATE)
                && isContainNoOtherFlag(parameters, FLAG_GOAL_INDEX, FLAG_END_DATE);
    }

    private static boolean isChangeHabitName(ArrayList<String> parameters) {
        return isContainFlag(parameters, FLAG_GOAL_INDEX) && isContainFlag(parameters, FLAG_HABIT_INDEX)
                && isContainFlag(parameters, FLAG_NAME);
    }

    private static boolean isChangeHabitInterval(ArrayList<String> parameters) {
        return isContainFlag(parameters, FLAG_GOAL_INDEX) && isContainFlag(parameters, FLAG_HABIT_INDEX)
                && isContainFlag(parameters, FLAG_INTERVAL);
    }



    //todo can consider overloading to accommodate three flags rather than just two
    /**
     * Ensures no excess parameters are given.
     * Excess parameters given can happen when user wrongly uses a command.
     * e.g. mixing up "update" command for "change" command, adding an extra habit index parameter.
     * firstFlag and secondFlag are the two flags that is expected from the user.
     *
     * @param parameters Array with parameters from user.
     * @param firstFlag First expected flag from user.
     * @param secondFlag Second expected flag from user.
     * @return True if there is no excess parameters in user input. False otherwise.
     */
    private static boolean isContainNoOtherFlag(ArrayList<String> parameters, String firstFlag, String secondFlag) {
        for (String param : parameters) {
            if (param.contains(FLAG_NAME) && isExcessFlag(FLAG_NAME, firstFlag, secondFlag)) {
                return false;
            } else if (param.contains(FLAG_GOAL_TYPE) && isExcessFlag(FLAG_GOAL_TYPE, firstFlag, secondFlag)) {
                return false;
            } else if (param.contains(FLAG_INTERVAL) && isExcessFlag(FLAG_INTERVAL, firstFlag, secondFlag)) {
                return false;
            } else if (param.contains(FLAG_START_DATE) && isExcessFlag(FLAG_START_DATE, firstFlag, secondFlag)) {
                return false;
            } else if (param.contains(FLAG_END_DATE) && isExcessFlag(FLAG_END_DATE, firstFlag, secondFlag)) {
                return false;
            } else if (param.contains(FLAG_HABIT_INDEX) && isExcessFlag(FLAG_HABIT_INDEX, firstFlag, secondFlag)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Compares a reference flag to two other flags to see if reference flag is identical to either of the two flags.
     *
     * @param referenceFlag Flag used to compare to firstFlag and secondFlag.
     * @param firstFlag First expected flag from user.
     * @param secondFlag Second expected flag from user.
     * @return True if referenceFlag is not equal to the 2 expected flags. False otherwise.
     */
    private static boolean isExcessFlag(String referenceFlag, String firstFlag, String secondFlag) {
        return !referenceFlag.equals(firstFlag) && !referenceFlag.equals(secondFlag);
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
            parsedDate = LocalDate.parse(strDate, dateTimeFormatter);
            return convertLocalDateToDate(parsedDate);
        } catch (DateTimeParseException e) {
            throw new HaBitParserException(ERROR_DATE_FORMAT);
        }
    }



}
