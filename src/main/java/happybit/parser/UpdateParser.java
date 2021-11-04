package happybit.parser;

import happybit.command.Command;
import happybit.command.UpdateGoalEndDateCommand;
import happybit.command.UpdateGoalNameCommand;
import happybit.command.UpdateGoalTypeCommand;
import happybit.command.UpdateHabitIntervalCommand;
import happybit.command.UpdateHabitNameCommand;
import happybit.exception.HaBitParserException;
import happybit.goal.GoalType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateParser extends Parser {

    private static final String ERROR_GOAL_END_DATE_FORMAT = "Use the e/ flag to set the new goal end date"
            + "Eg: e/31122021";
    private static final String ERROR_GOAL_END_DATE_NON_DATE = "Enter your date in the format DDMMYYYY";

    private static final String ERROR_INVALID_UPDATE_COMMAND = "There is no update command for goals in this format, "
            + "do check your parameters one more time.";
    private static final String ERROR_INVALID_CHANGE_COMMAND = "There is no change command for habits in this format, "
            + "do check your parameters one more time.";

    private static final int FLAG_LENGTH = 2;

    //todo S L A P more in the future; refer to AddParser

    public static Command parseUpdateGoalCommands(String input) throws HaBitParserException {
        checkNoDescription(input);
        String[] parameters = splitInput(input);
        if (isUpdateGoalName(parameters)) {
            return parseUpdateGoalNameCommand(input);
        }
        if (isUpdateGoalType(parameters)) {
            return parseUpdateGoalTypeCommand(input);
        }
        if (isUpdateGoalEndDate(parameters)) {
            return parseUpdateGoalEndDate(input);
        }

        throw new HaBitParserException(ERROR_INVALID_UPDATE_COMMAND);
    }

    public static Command parseUpdateHabitCommands(String input) throws HaBitParserException {
        checkNoDescription(input);
        String[] parameters = splitInput(input);

        if (isChangeHabitName(parameters)) {
            return parseUpdateHabitNameCommand(input);
        }

        if (isChangeHabitInterval(parameters)) {
            return parseUpdateHabitIntervalCommand(input);
        }

        throw new HaBitParserException(ERROR_INVALID_CHANGE_COMMAND);
    }

    /**
     * Parses detail from user into goalIndex and newGoalName (information) to create a new Command.
     *
     * @param input Input typed by the user.
     * @return A Command class with the goalIndex and newGoalName.
     * @throws HaBitParserException If command parameters are not defined, or defined improperly.
     */
    public static Command parseUpdateGoalNameCommand(String input) throws HaBitParserException {
        checkNoDescription(input);
        String[] parameters = splitInput(input);
        int goalIndex = getNumber(parameters, FLAG_GOAL_INDEX) - 1;
        String newGoalName = getName(parameters);
        return new UpdateGoalNameCommand(goalIndex, newGoalName);
    }

    public static Command parseUpdateGoalEndDate(String input) throws HaBitParserException {
        checkNoDescription(input);
        String[] parameters = splitInput(input);
        int goalIndex = getNumber(parameters, FLAG_GOAL_INDEX) - 1;
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
        checkNoDescription(input);
        String[] parameters = splitInput(input);
        int goalIndex = getNumber(parameters, FLAG_GOAL_INDEX) - 1;
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
        checkNoDescription(input);
        String[] parameters = splitInput(input);
        int goalIndex = getNumber(parameters, FLAG_GOAL_INDEX) - 1;
        int habitIndex = getNumber(parameters, FLAG_HABIT_INDEX) - 1;
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
        checkNoDescription(commandInstruction);
        String[] parameters = splitInput(commandInstruction);
        int goalIndex = getNumber(parameters, FLAG_GOAL_INDEX) - 1;
        int habitIndex = getNumber(parameters, FLAG_HABIT_INDEX) - 1;
        int interval = getNumber(parameters, FLAG_INTERVAL);
        assert (goalIndex >= 0);
        assert (habitIndex >= 0);
        assert (interval >= 0);
        return new UpdateHabitIntervalCommand(goalIndex, habitIndex, interval);
    }

    /*
     * NOTE : ==================================================================
     * The following are private methods that are used to implement SLAP for the
     * above public methods. These methods are positioned at the bottom to better
     * visualise the actual methods that can be called from outside this class.
     * =========================================================================
     */

    private static boolean isUpdateGoalName(String[] parameters) {
        return isContainFlag(parameters, FLAG_GOAL_INDEX) && isContainFlag(parameters, FLAG_NAME);
    }

    private static boolean isUpdateGoalType(String[] parameters) {
        return isContainFlag(parameters, FLAG_GOAL_INDEX) && isContainFlag(parameters, FLAG_GOAL_TYPE);
    }

    private static boolean isUpdateGoalEndDate(String[] parameters) {
        return isContainFlag(parameters, FLAG_GOAL_INDEX) && isContainFlag(parameters, FLAG_END_DATE);
    }

    private static boolean isChangeHabitName(String[] parameters) {
        return isContainFlag(parameters, FLAG_HABIT_INDEX) && isContainFlag(parameters, FLAG_NAME);
    }

    private static boolean isChangeHabitInterval(String[] parameters) {
        return isContainFlag(parameters, FLAG_HABIT_INDEX) && isContainFlag(parameters, FLAG_INTERVAL);
    }

    private static boolean isContainFlag(String[] parameters, String flag) {
        for (String param : parameters) {
            if (param.contains(flag)) {
                return true;
            }
        }
        return false;
    }

    private static Date getDate(String[] parameters) throws HaBitParserException {
        String strEndDate = getParameter(parameters, FLAG_END_DATE);
        if (strEndDate == null || strEndDate.equals(FLAG_END_DATE)) {
            throw new HaBitParserException(ERROR_GOAL_END_DATE_FORMAT);
        }
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
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        formatter.setLenient(false);
        try {
            return formatter.parse(strDate);
        } catch (ParseException e) {
            throw new HaBitParserException(ERROR_GOAL_END_DATE_NON_DATE);
        }
    }

}
