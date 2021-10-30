package seedu.duke.commands;

import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import static seedu.duke.commands.RemoveCommand.COMMAND_REMOVE;

/**
 * The parser class contains methods to process the string input by the user.
 */
public class Parser {

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
        if (input.equals(ExitCommand.COMMAND_WORD)) {
            return new ExitCommand();
        } else if (input.startsWith(COMMAND_REMOVE)) {
            return new RemoveCommand(input);
        } else if (input.startsWith(ListCommand.COMMAND_WORD)) {
            return new ListCommand(input);
        } else if (input.startsWith(SearchCommand.COMMAND_WORD)) {
            return new SearchCommand(input);
        } else if (input.startsWith(AddCommand.COMMAND_WORD)) {
            return new AddCommand(extractArgs(input));
        } else if (input.startsWith(LoanCommand.COMMAND_WORD)) {
            return new LoanCommand(extractArgs(input));
        } else if (input.startsWith(ReturnCommand.COMMAND_WORD)) {
            return new ReturnCommand(input);
        } else if (input.startsWith(EditCommand2.COMMAND_WORD)) {
            return new EditCommand2(extractArgs(input));
        } else if (input.startsWith(ReserveCommand.COMMAND_WORD)) {
            return new ReserveCommand(extractArgs(input));
        } else if (input.startsWith(DeadlineCommand.COMMAND_WORD)) {
            return new DeadlineCommand(input);
        } else if (input.startsWith(UnreserveCommand.COMMAND_WORD)) {
            return new UnreserveCommand(input);
        } else {
            return new UnknownCommand();
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
    public HashMap<String, String> extractArgs(String input) {
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
            args.put(currentArg[0].strip(), currentArg[1].strip());
        }
        return args;
    }
}
