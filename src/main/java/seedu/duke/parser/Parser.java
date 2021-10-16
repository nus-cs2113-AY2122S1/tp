package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.exception.GetJackDException;

import java.util.Locale;
import java.util.logging.Logger;

import static seedu.duke.logger.LoggerUtil.setupLogger;

/**
 * To make sense of user commands by extracting keywords and descriptions.
 */
public abstract class Parser {
    static final Logger LOGGER = Logger.getLogger(Parser.class.getName());
    static final String MESSAGE_INVALID_COMMAND = "Invalid command format\n";
    public static String WORKOUT_KEYWORD = "/w ";
    public static String EXERCISE_KEYWORD = "/e ";
    public static String SETS_KEYWORD = "/s ";
    public static String REPS_KEYWORD = "/r ";
    public static final String PARAMETER_SEPARATOR = ", ";
    protected String userInputString;

    public Parser(String userInputString) {
        this.userInputString = userInputString;
        setupLogger(LOGGER);
    }
    
    protected Parser() {
    }

    public static String getCommandType(String userInputString) {
        String[] commandTypeAndParams = splitCommandWordsAndArgs(userInputString, "\\s+");
        String commandType = commandTypeAndParams[0].trim().toLowerCase(Locale.ROOT);
        return commandType;
    }

    /**
     * Returns the parameters for commands from the user.
     *
     * @param userInputString user input
     * @return parameters to execute commands with
     */
    protected static String getCommandArguments(String userInputString) {
        String[] commandTypeAndParams = splitCommandWordsAndArgs(userInputString, "\\s+");
        String commandArgs = commandTypeAndParams[1].trim();
        return commandArgs;
    }

    /**
     * Gets workout and exercise indices.
     *
     * @param commandArgs raw input string without the command word.
     * @return String array of size 2. If there is no workout index, empty String array is returned.
     *         If there is a workout index but no exercise index, only workout index is returned.
     *         Otherwise, both workout index and exercise index are returned.
     */
    static String[] getWorkoutAndExerciseIndices(String commandArgs) {
        if (!commandArgs.contains(WORKOUT_KEYWORD)) {
            return new String[]{"", ""};
        }

        if (!commandArgs.contains(EXERCISE_KEYWORD)) {
            assert (commandArgs.contains(WORKOUT_KEYWORD));
            String workoutIndex = commandArgs.replace(WORKOUT_KEYWORD, "").trim();
            return new String[]{workoutIndex, ""};
        }

        assert (commandArgs.contains(WORKOUT_KEYWORD));
        assert (commandArgs.contains(EXERCISE_KEYWORD));

        String[] args = splitCommandWordsAndArgs(commandArgs, EXERCISE_KEYWORD);
        String workoutIndex = args[0].replace(WORKOUT_KEYWORD, "").trim();
        String exerciseIndex = args[1].trim();

        assert (!workoutIndex.contains(WORKOUT_KEYWORD));
        assert (!workoutIndex.contains(EXERCISE_KEYWORD));
        assert (!exerciseIndex.contains(WORKOUT_KEYWORD));
        assert (!exerciseIndex.contains(EXERCISE_KEYWORD));

        return new String[]{workoutIndex, exerciseIndex};
    }

    /**
     * Given a string and a keyword, this method splits the string around the keyword into 2.
     *
     * @param input   String
     * @param keyword keyword that we want to split the string around.
     * @return String array of size 2, none of the elements in the array contain the keyword.
     */
    static String[] splitCommandWordsAndArgs(String input, String keyword) {
        final String[] split = input.trim().split(keyword, 2);

        if (split.length == 2) {
            return split;
        }

        return new String[]{split[0].trim(), ""};
    }

    /**
     * Parses a string as an integer.
     *
     * @param index String that we want to parse as an integer.
     * @return integer obtained from the String
     * @throws GetJackDException if index is empty, or index cannot be parsed as integer.
     */
    static int parseArgsAsIndex(String index) throws GetJackDException {
        if (index.isEmpty()) {
            LOGGER.info("Workout or exercise index not found");
            throw new GetJackDException("Error. Workout or exercise index not found.");
        }

        try {
            return Integer.parseInt(index.trim());
        } catch (NumberFormatException e) {
            LOGGER.info("Invalid workout or exercise index");
            throw new GetJackDException("Error. Invalid workout or exercise index.");
        }
    }

    /**
     * Parses and processes the user input and returns a Command object with parameters and attributes according to the
     * user input.
     *
     * @return User-specified command
     */
    public abstract Command parseInput();
}
