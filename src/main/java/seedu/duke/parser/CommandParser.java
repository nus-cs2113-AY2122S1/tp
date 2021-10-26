package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.CommandEnum;
import seedu.duke.command.ByeCommand;
import seedu.duke.command.DeleteCommand;
import seedu.duke.command.HelpCommand;
import seedu.duke.command.InvalidCommand;
import seedu.duke.command.ListCommand;
import seedu.duke.command.SortCommand;
import seedu.duke.command.addtask.DeadlineCommand;
import seedu.duke.command.addtask.EventCommand;
import seedu.duke.command.addtask.ModuleCommand;
import seedu.duke.command.addtask.TodoCommand;
import seedu.duke.log.Log;

import java.util.HashMap;
import java.util.Map;

//@@author APZH
public class CommandParser {

    private static final String FLAG_REGEX = "^--\\w+";
    private static final String WHITESPACE_REGEX = "\\s+";

    //@@author APZH
    public static Map<String, String> getCommandOptions(String commandArguments) {

        Map<String, String> flagsToArguments = new HashMap<>();
        String[] tokens = commandArguments.split(WHITESPACE_REGEX);
        String mainArgument = "";

        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].matches(FLAG_REGEX)) {
                String flag = tokens[i];
                String flagArguments = "";
                try {
                    while (tokens[i + 1].matches(FLAG_REGEX) == false) {
                        flagArguments += tokens[i + 1] + " ";
                        i++;
                    }
                } catch (IndexOutOfBoundsException e) {
                    Log.warning(e.getMessage());
                }
                flagsToArguments.put(flag.substring(2), flagArguments.trim());
            } else {
                mainArgument += tokens[i] + " ";
            }
        }

        flagsToArguments.put(Command.MAIN_ARGUMENT, mainArgument.trim());
        return flagsToArguments;
    }

    //@@author APZH
    public static Command parseCommand(String userInput) {

        String[] inputArguments = userInput.split("\\s+", 2);
        String command = inputArguments[0];
        CommandEnum commandEnum = CommandEnum.getCommand(command);
        Map<String, String> commandOptions = new HashMap<>();

        if (inputArguments.length == 2) {
            commandOptions = getCommandOptions(inputArguments[1]);
        }

        switch (commandEnum) {
        case BYE:
            return new ByeCommand();
        case HELP:
            return new HelpCommand();
        case TODO:
            return new TodoCommand(commandOptions);
        case DEADLINE:
            return new DeadlineCommand(commandOptions);
        case EVENT:
            return new EventCommand(commandOptions);
        case LIST:
            return new ListCommand(commandOptions);
        case DELETE:
            return new DeleteCommand(commandOptions);
        case SORT:
            return new SortCommand(commandOptions);
        case MODULE:
            return new ModuleCommand(commandOptions);
        default:
            return new InvalidCommand();
        }
    }

    //@@author APZH
    // Used to debug and check the whether the user command mapping of flag->value works
    public static String printCommandOptions(Map<String, String> commandOptions) {

        String flagsToArguments = "";

        for (String flag : commandOptions.keySet()) {
            flagsToArguments += flag + " = " + commandOptions.get(flag) + "\n";
        }

        System.out.println(flagsToArguments);

        return flagsToArguments;
    }


}
