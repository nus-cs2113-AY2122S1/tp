package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.AddCommand;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.HelpCommand;
import seedu.duke.command.InvalidCommand;
import seedu.duke.command.ListCommand;
import seedu.duke.log.Log;
import seedu.duke.task.TaskManager;

import java.util.HashMap;

public class CommandParser {

    private static final String EXIT_COMMAND = "bye";
    private static final String HELP_COMMAND = "help";
    private static final String ADD_COMMAND = "add";
    private static final String LIST_COMMAND = "list";

    private static final String FLAG_REGEX = "^--\\w+";

    public static HashMap getCommandOptions(String commandArguments) {

        HashMap<String, String> flagsToArguments = new HashMap<>();
        String[] tokens = commandArguments.split("\\s+");
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
                flagsToArguments.put(flag, flagArguments.trim());
            } else {
                mainArgument += tokens[i] + " ";
            }
        }

        flagsToArguments.put("mainArgument", mainArgument.trim());
        return flagsToArguments;
    }

    public static Command parseCommand(TaskManager taskManager, String userInput) {

        String[] inputArguments = userInput.split("\\s+", 2);
        String command = inputArguments[0];
        HashMap<String, String> commandOptions = new HashMap<>();

        if (inputArguments.length == 2) {
            commandOptions = getCommandOptions(inputArguments[1]);
        }

        switch (command) {
        case EXIT_COMMAND:
            return new ExitCommand();
        case HELP_COMMAND:
            return new HelpCommand();
        case ADD_COMMAND:
            return new AddCommand(taskManager, commandOptions);
        case LIST_COMMAND:
            return new ListCommand(taskManager, commandOptions);
        default:
            return new InvalidCommand();
        }
    }

    // Used to debug and check the whether the user command mapping of flag->value works
    public static String printCommandOptions(HashMap<String, String> commandOptions) {

        String flagsToArguments = "";

        for (String flag : commandOptions.keySet()) {
            flagsToArguments += flag + " = " + commandOptions.get(flag) + "\n";
        }

        System.out.println(flagsToArguments);

        return flagsToArguments;
    }


}
