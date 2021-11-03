package seedu.duke.commands;

import seedu.duke.common.LibmgrException;

import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import static seedu.duke.commands.RemoveCommand.COMMAND_REMOVE;

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
        try {
            if (input.equals(ExitCommand.COMMAND_WORD)) {
                return new ExitCommand();
            } else if (input.startsWith(COMMAND_REMOVE)) {
                return new RemoveCommand(input);
            } else if (input.startsWith(ListCommand.COMMAND_WORD)) {
                return new ListCommand(input);
            } else if (input.startsWith(SearchCommand.COMMAND_WORD)) {
                return new SearchCommand(extractArgs(input));
            } else if (input.startsWith(AddCommand.COMMAND_WORD)) {
                return new AddCommand(extractArgs(input));
            } else if (input.startsWith(LoanCommand.COMMAND_WORD)) {
                return new LoanCommand(extractArgs(input));
            } else if (input.startsWith(ReturnCommand.COMMAND_WORD)) {
                return new ReturnCommand(input);
            } else if (input.startsWith(EditCommand.COMMAND_WORD)) {
                return new EditCommand(extractArgs(input));
            } else if (input.startsWith(ReserveCommand.COMMAND_WORD)) {
                return new ReserveCommand(extractArgs(input));
            } else if (input.startsWith(DeadlineCommand.COMMAND_WORD)) {
                return new DeadlineCommand(input);
            } else if (input.startsWith(UnreserveCommand.COMMAND_WORD)) {
                return new UnreserveCommand(input);
            } else if (input.startsWith(StatsCommand.COMMAND_WORD)) {
                return new StatsCommand(input);
            } else if (input.startsWith(HelpCommand.COMMAND_WORD)) {
                return new HelpCommand();
            } else {
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
