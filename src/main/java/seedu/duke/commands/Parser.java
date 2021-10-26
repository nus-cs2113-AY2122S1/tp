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
            return new LoanCommand(input);
        } else if (input.startsWith(ReturnCommand.COMMAND_WORD)) {
            return new ReturnCommand(input);
        } else if (input.startsWith(ReserveCommand.COMMAND_WORD)) {
            return new ReserveCommand(input);
        } else {
            return new UnknownCommand();
        }
    }

    public HashMap<String, String> extractArgs(String input) {
        HashMap<String, String> args = new HashMap<>();
        String splitByDelimiter = ".+?(?=\\s\\w\\/)|.+?$";
        Pattern p = Pattern.compile(splitByDelimiter);
        Matcher m = p.matcher(input);
        m.find();
        args.put(null,m.group());
        while(m.find()) {
            String[] currentArg = m.group().split("\\/");
            args.put(currentArg[0].strip(), currentArg[1].strip());
        }
        return args;
    }
}
