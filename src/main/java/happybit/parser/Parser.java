package happybit.parser;

import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Parser {

    protected static final String DELIMITER = "\u0001";
    protected static final String LABEL_SYNTAX = "[a-zA-Z]/";
    protected static final String DATE_FORMAT = "ddMMyyyy";

    protected static final String FLAG_GOAL_INDEX = "g/";
    protected static final String FLAG_NAME = "n/";
    protected static final String FLAG_GOAL_TYPE = "f/";
    protected static final String FLAG_INTERVAL = "i/";
    protected static final String FLAG_START_DATE = "s/";
    protected static final String FLAG_END_DATE = "e/";
    protected static final String FLAG_HABIT_INDEX = "h/";
    private static final String COMMAND_HELP = "help";
    private static final String COMMAND_ADD_GOAL = "set";
    private static final String COMMAND_ADD_HABIT = "add";
    private static final String COMMAND_SET_GOAL = "goal";
    private static final String COMMAND_UPDATE_GOAL_NAME = "update";
    private static final String COMMAND_UPDATE_HABIT_NAME = "change";
    private static final String COMMAND_LIST_GOAL = "list";
    private static final String COMMAND_LIST_HABIT = "view";
    private static final String COMMAND_DELETE_GOAL = "remove";
    private static final String COMMAND_DELETE_HABIT = "delete";
    private static final String COMMAND_COMPLETE_HABIT = "done";
    private static final String COMMAND_VIEW_GOAL_PROGRESS = "progress";
    private static final String COMMAND_VIEW_HABIT_STREAK = "streak";
    private static final String COMMAND_RETURN = "return";
    private static final String COMMAND_EXIT = "bye";

    /**
     * Splits the input into the various parameters.
     *
     * @param input User input.
     * @return String array containing the parameters.
     */
    protected static String[] splitInput(String input) {
        Function<MatchResult, String> replace = x -> DELIMITER + x.group();
        Pattern pattern = Pattern.compile(LABEL_SYNTAX);
        Matcher matcher = pattern.matcher(input);
        String processedInput = matcher.replaceAll(replace);
        String[] parameters = processedInput.split(DELIMITER);
        return trimParameters(parameters);
    }

    /**
     * Removes leading and trailing whitespaces if any.
     *
     * @param parameters String array of command parameters.
     * @return String array of command parameters that have been trimmed of leading/trailing whitespaces.
     */
    private static String[] trimParameters(String[] parameters) {
        for (int i = 0; i < parameters.length; i++) {
            parameters[i] = parameters[i].trim();
        }
        return parameters;
    }

    /**
     * Finds the parameters corresponding to the given label and returns it.
     *
     * @param parameters String array of command parameters.
     * @param label      Label of a parameter.
     * @return Parameter if it exists, null otherwise.
     */
    protected static String getParameter(String[] parameters, String label) {
        for (String parameter : parameters) {
            if (parameter.contains(label)) {
                return parameter;
            }
        }
        return null;
    }

    /**
     * Parse the command instruction based off the command word.
     *
     * @param command String of the command word.
     * @param details String of the command instructions.
     * @return Command to be executed based off user input.
     */
    private static Command parseCommand(String command, String details) throws HaBitParserException {
        switch (command) {
        case COMMAND_ADD_GOAL:
            return AddParser.parseAddGoalCommand(details);
        case COMMAND_ADD_HABIT:
            return AddParser.parseAddHabitCommand(details);
        case COMMAND_SET_GOAL:
            return SetParser.parseSetGoalCommand(details);
        case COMMAND_LIST_GOAL:
            return new ListGoalsCommand();
        case COMMAND_LIST_HABIT:
            return ListHabitParser.parseListHabitCommand(details);
        case COMMAND_DELETE_GOAL:
            return DeleteParser.parseDeleteGoalCommand(details);
        case COMMAND_DELETE_HABIT:
            return DeleteParser.parseDeleteHabitCommand(details);
        case COMMAND_COMPLETE_HABIT:
            return DoneParser.parseDoneHabitCommand(details);
        case COMMAND_VIEW_GOAL_PROGRESS:
            return ProgressParser.parseViewGoalProgressCommand(details);
        case COMMAND_VIEW_HABIT_STREAK:
            return StreakParser.parseViewHabitStreakCommand(details);
        case COMMAND_UPDATE_GOAL_NAME:
            return UpdateParser.parseUpdateGoalNameCommand(details);
        case COMMAND_EXIT:
            return new ExitCommand();
        case COMMAND_RETURN:
            return new ReturnCommand();
        case COMMAND_HELP:
        default:
            return new HelpCommand();
        }
    }

}
