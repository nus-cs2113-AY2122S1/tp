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
    static final String MESSAGE_INVALID_COMMAND = "Invalid command format\n\n";
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


    static int parseWorkoutIndex(String commandArgs) throws GetJackDException {
        String arg = commandArgs.trim();
        int workoutIndex = (Command.workoutMode == 0) ? parseArgsAsIndex(arg) : Command.workoutMode;
        return workoutIndex;
    }

    /**
     * For parsing workout and exercise index for "done" and "remove" commands.
     * @param commandArgs command arguments from user, in the format "workoutIndex, exerciseIndex"
     * @return int[2] the indices of workout and exercise
     * @throws GetJackDException when workout or exercise indices are not valid
     */
    static int[] parseWorkoutAndExerciseIndex(String commandArgs) throws GetJackDException {
        String[] args = commandArgs.split(PARAMETER_SEPARATOR);
        if ((args.length < 2 && Command.workoutMode == 0) || args.length < 1) {
            throw new GetJackDException("Error. Missing workout parameters");
        }
        int exerciseIndex = parseArgsAsIndex(args[0]);
        int workoutIndex = (Command.workoutMode == 0) ? parseArgsAsIndex(args[1]) : Command.workoutMode;
        int[] indices = {exerciseIndex, workoutIndex};
        return indices;
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
