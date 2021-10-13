package happybit.parser;

import happybit.command.Command;
import happybit.command.HelpCommand;
import happybit.exception.HBParserException;

public class Parser {

    private static final String DELIMITER = "@@@";

    public static Command parse(String userInput) throws HBParserException {
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
     * @throws HBParserException If the delimiter appears in the user's input.
     */
    private static String treatUserInput(String userInput) throws HBParserException {
        if (userInput.contains(DELIMITER)) {
            throw new HBParserException("Invalid character in the user input");
        }
        String treatedInput = userInput.strip().replaceAll("\\s+"," ");
        testEmptyString(treatedInput);
        return treatedInput;
    }

    /**
     * Checks if the string is empty.
     *
     * @param userInput String of user's input with extraneous whitespace removed.
     * @throws HBParserException If the treated user's input is empty.
     */
    private static void testEmptyString(String userInput) throws HBParserException {
        if (userInput.isEmpty()) {
            throw new HBParserException("No user command detected");
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
        String instruction = "";
        for (int i = 1; i < words.length; i++) {
            instruction += words[i];
        }
        return instruction;
    }

    private static Command parseCommand(String command, String details) {
        switch (command) {
        case "help":
            return new HelpCommand();
        case "list":
            return null;
        case "set":
            return null;
        case "remove":
            return null;
        case "done":
            return null;
        case "delete":
            return null;
        case "edit":
            return null;
        case "view":
            return null;
        default:
            return null;
        }
    }

}
