package seedu.duke.commands;

import seedu.duke.common.LibmgrException;

import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * The parser class contains methods to process the string input by the user.
 */
public class Parser {
    private static final String ERR_EMPTY_ATTRIBUTES = "  (!) Attributes cannot be empty!";
    private static final String ERR_DUPLICATE_ATTRIBUTES = "  (!) Do not specify an attribute more than once!";

    /**
     * Sole constructor.
     */
    public Parser() {
    }

    /**
     * Processes the commands inputted by the user.
     *
     * @param input A string corresponding to a specific command
     * @return The specific Command object corresponding to the input
     */
    public Command parse(String input) {
        String[] inputList = input.strip().split("\\s+");
        String command = inputList[0];
        try {
            switch (command) {
            case ExitCommand.COMMAND_WORD:
                return new ExitCommand();
            case RemoveCommand.COMMAND_WORD:
                return new RemoveCommand(input);
            case ListCommand.COMMAND_WORD:
                return new ListCommand(input);
            case SearchCommand.COMMAND_WORD:
                return new SearchCommand(extractArgs(input));
            case AddCommand.COMMAND_WORD:
                return new AddCommand(extractArgs(input));
            case LoanCommand.COMMAND_WORD:
                return new LoanCommand(extractArgs(input));
            case ReturnCommand.COMMAND_WORD:
                return new ReturnCommand(input);
            case EditCommand.COMMAND_WORD:
                return new EditCommand(extractArgs(input));
            case ReserveCommand.COMMAND_WORD:
                return new ReserveCommand(extractArgs(input));
            case DeadlineCommand.COMMAND_WORD:
                return new DeadlineCommand(input);
            case UnreserveCommand.COMMAND_WORD:
                return new UnreserveCommand(input);
            case InfoCommand.COMMAND_WORD:
                return new InfoCommand(input);
            case HelpCommand.COMMAND_WORD:
                return new HelpCommand();
            default:
                return new UnknownCommand();
            }
        } catch (LibmgrException e) {
            return new ErrorCommand(e.getMessage());
        }
    }

    //@@author exetr
    /**
     * Converts a string of user input into a hashmap.
     * Key represents the type of input (null representing command word).
     * Value contains values supplied by user that are associated to the argument types.
     * @param input User input string
     * @return HashMap(String, String) Containing key-value pairs for different arguments
     */
    public HashMap<String, String> extractArgs(String input) throws LibmgrException {
        HashMap<String, String> args = new HashMap<>();
        // Configure regex matcher
        String splitByDelimiter = ".+?(?=\\s\\w/)|.+?$";
        Pattern p = Pattern.compile(splitByDelimiter);
        Matcher m = p.matcher(input);
        // Get command word
        m.find();
        // Assign command word to null key
        args.put(null,m.group());
        // Iterate through all other arguments supplied
        while (m.find()) {
            String[] currentArg = m.group().split("/", 2);
            String key = currentArg[0].strip();
            String val = currentArg[1].strip();
            // Check if empty string is supplied
            if (val.equals("")) {
                throw new LibmgrException(ERR_EMPTY_ATTRIBUTES);
            }
            if (args.get(key) != null) {
                throw new LibmgrException(ERR_DUPLICATE_ATTRIBUTES);
            }
            args.put(key, val);
        }
        return args;
    }
}
