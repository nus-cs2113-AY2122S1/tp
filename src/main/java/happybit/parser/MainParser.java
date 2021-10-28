package happybit.parser;

import happybit.command.Command;
import happybit.command.ListGoalsCommand;
import happybit.command.ExitCommand;
import happybit.command.HelpCommand;
import happybit.command.ReturnCommand;
import happybit.exception.HaBitParserException;

public class MainParser {

    private static final String DELIMITER = "@@@";

    private static final String COMMAND_HELP = "help";
    private static final String COMMAND_ADD_GOAL = "set";
    private static final String COMMAND_ADD_HABIT = "add";
    private static final String COMMAND_SET_GOAL = "goal";
    private static final String COMMAND_UPDATE_GOAL_NAME = "update";
    private static final String COMMAND_UPDATE_GOAL_TYPE = "updateType";
    private static final String COMMAND_UPDATE_HABIT_NAME = "change";
    private static final String COMMAND_UPDATE_HABIT_INTERVAL = "interval";
    private static final String COMMAND_LIST_GOAL = "list";
    private static final String COMMAND_LIST_HABIT = "view";
    private static final String COMMAND_DELETE_GOAL = "remove";
    private static final String COMMAND_DELETE_HABIT = "delete";
    private static final String COMMAND_COMPLETE_HABIT = "done";
    private static final String COMMAND_RETURN = "return";
    private static final String COMMAND_EXIT = "exit";

    /**
     * Parses the user input.
     *
     * @param userInput String of the user input in the command line.
     * @return Command to be executed based off user input.
     * @throws HaBitParserException If there are syntax / missing information in the user input.
     */
    public static Command parse(String userInput) throws HaBitParserException {
        String treatedUserInput = treatUserInput(userInput);
        String[] words = treatedUserInput.split(" ");
        String commandWord = words[0];
        String commandInstruction = getCommandInstruction(words);
        return parseCommand(commandWord, commandInstruction);
    }

    /*
     * NOTE : ==================================================================
     * The following are private methods that are used to implement SLAP for the
     * above public methods. These methods are positioned at the bottom to better
     * visualise the actual methods that can be called from outside this class.
     * =========================================================================
     */

    /**
     * Treat the userInput by removing extra blank/white spaces.
     *
     * @param userInput String input by the user into the command line.
     * @return String with removed extraneous whitespaces.
     * @throws HaBitParserException If the delimiter appears in the user's input.
     */
    private static String treatUserInput(String userInput) throws HaBitParserException {
        if (userInput.contains(DELIMITER)) {
            throw new HaBitParserException("Invalid character in the user input");
        }
        String treatedInput = userInput.strip().replaceAll("\\s+"," ");
        testEmptyString(treatedInput);
        return treatedInput;
    }

    /**
     * Checks if the string is empty.
     *
     * @param userInput String of user's input with extraneous whitespace removed.
     * @throws HaBitParserException If the treated user's input is empty.
     */
    private static void testEmptyString(String userInput) throws HaBitParserException {
        if (userInput.isEmpty()) {
            throw new HaBitParserException("No user command detected");
        }
    }

    /**
     * Obtain the instruction of the user input command without the command word.
     *
     * @param words String array of user input command delimited by whitespaces.
     * @return String containing the remainder of the user input without the command word.
     */
    private static String getCommandInstruction(String[] words) {
        if (words.length == 1) {
            return null;
        }
        return concatenateString(words);
    }

    /**
     * Concatenate the string in a string array, excluding the first string.
     *
     * @param words String array containing the strings to be concatenated.
     * @return String that has been concatenated.
     */
    private static String concatenateString(String[] words) {
        StringBuilder instruction = new StringBuilder();
        for (int i = 1; i < words.length; i++) {
            instruction.append(words[i]);
            if (i != words.length - 1) {
                instruction.append(" ");
            }
        }
        return instruction.toString();
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
        case COMMAND_UPDATE_GOAL_NAME:
            return UpdateParser.parseUpdateGoalNameCommand(details);
        case COMMAND_UPDATE_GOAL_TYPE:
            return UpdateParser.parseUpdateGoalTypeCommand(details);
        case COMMAND_UPDATE_HABIT_NAME:
            return UpdateParser.parseUpdateHabitNameCommand(details);
        case COMMAND_UPDATE_HABIT_INTERVAL:
            return UpdateParser.parseUpdateHabitIntervalCommand(details);
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
