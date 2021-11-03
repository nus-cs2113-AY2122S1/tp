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
    public static final String PARAMETER_SEPARATOR = ", ";
    static final Logger LOGGER = Logger.getLogger(Parser.class.getName());
    static final String MESSAGE_INVALID_COMMAND = "Invalid command format\n\n";
    protected String userInputString;

    public Parser(String userInputString) {
        this.userInputString = userInputString;
        setupLogger(LOGGER);
    }

    protected Parser() {
    }

    public static String getCommandType(String userInputString) {
        String[] commandTypeAndParams = splitCommandWordsAndArgs(userInputString, "\\s+");
        return commandTypeAndParams[0].trim().toLowerCase(Locale.ROOT);
    }

    /**
     * Returns the parameters for commands from the user.
     *
     * @param userInputString user input
     * @return parameters to execute commands with
     */
    protected static String getCommandArguments(String userInputString) {
        String[] commandTypeAndParams = splitCommandWordsAndArgs(userInputString, "\\s+");
        return commandTypeAndParams[1].trim();
    }

    static int parseWorkoutIndex(String commandArgs) throws GetJackDException {
        String arg = commandArgs.trim();
        return (Command.workoutMode == 0 || !arg.isEmpty()) ? parseArgsAsIndex(arg) :
                Command.workoutMode;
    }

    /**
     * For parsing workout and exercise index for "done" and "remove" commands.
     *
     * @param commandArgs command arguments from user, in the format "workoutIndex, exerciseIndex"
     * @param isEdit      whether this method is being called from a EditExerciseParser or not
     * @return int[2] the indices of workout and exercise
     * @throws GetJackDException when workout or exercise indices are not valid
     */
    static int[] parseWorkoutAndExerciseIndex(String commandArgs, boolean isEdit) throws GetJackDException {
        String[] args = commandArgs.split(PARAMETER_SEPARATOR);
        if ((args.length < 2 && Command.workoutMode == 0) || args.length < 1) {
            throw new GetJackDException("Error. Missing workout parameters");
        }
        int exerciseIndex = parseArgsAsIndex(args[0]);
        int lengthForItToContainWorkoutIndex = (isEdit) ? 3 : 1;
        int workoutIndex = (Command.workoutMode == 0 || args.length > lengthForItToContainWorkoutIndex)
                ? parseArgsAsIndex(args[1]) : Command.workoutMode;
        return new int[]{exerciseIndex, workoutIndex};
    }

    /**
     * Given a string and a keyword, this method splits the string around the keyword into 2.
     *
     * @param input   String
     * @param keyword keyword that we want to split the string around
     * @return String array of size 2, none of the elements in the array contain the keyword
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
     * @param index String that we want to parse as an integer
     * @return integer obtained from the String
     * @throws GetJackDException if index is empty, or index cannot be parsed as integer
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
     * Gets arguments required for an exercise, such as workoutIndex, exerciseName, sets and reps.
     * commandArgs passed in as [exercise description], [sets and reps], [workout index or name]
     *
     * @param commandArgs user input without the command word
     * @param isEdit      whether this function was called for the EditExerciseParser or not
     * @return string array containing workoutIndex, exerciseName, sets and reps
     * @throws GetJackDException if any of the above-mentioned arguments are empty
     */
    static String[] getExerciseArgs(String commandArgs, boolean isEdit) throws GetJackDException {
        if (!commandArgs.contains(PARAMETER_SEPARATOR)) {
            throw new GetJackDException("Invalid format for add exercise.");
        }

        String[] arguments = commandArgs.split(PARAMETER_SEPARATOR);
        if (!isEdit && ((arguments.length < 3 && Command.workoutMode == 0) || arguments.length < 2)) {
            LOGGER.info("Missing exercise arguments");
            throw new GetJackDException("Error. Missing exercise parameters");
        }

        try {
            String exerciseDescription = arguments[0];
            String[] setsAndReps = arguments[1].split(" ");
            String sets = setsAndReps[0];
            String reps = setsAndReps[1];
            String workoutIdentifier;
            workoutIdentifier = getWorkoutIdentifier(arguments, isEdit);

            return new String[]{exerciseDescription, sets, reps, workoutIdentifier};

        } catch (ArrayIndexOutOfBoundsException e) {
            LOGGER.info("Missing exercise arguments");
            throw new GetJackDException("Error. Missing sets or reps parameters");
        }
    }

    /**
     * returns the index of the workout included in the parameters in the format of a string.
     * If this function was called for the EditExerciseParser or if the user has currently entered
     * a workout then the workout index of the workout being edited or entered is returned.
     *
     * @param arguments array of Strings consisting all the arguments input by user
     * @param isEdit    whether this function was called for the EditExerciseParser or not
     * @return index of workout in String format extracted from the arguments
     */
    private static String getWorkoutIdentifier(String[] arguments, boolean isEdit) {
        if (isEdit) {
            return String.valueOf(EditExerciseParser.getWorkoutIndex());
        }
        return (Command.workoutMode == 0 || arguments.length > 2) ? arguments[2] :
                String.valueOf(Command.workoutMode);
    }

    /**
     * Parses and processes the user input and returns a Command object with parameters and attributes according to the
     * user input.
     *
     * @return User-specified command
     */
    public abstract Command parseInput();
}
