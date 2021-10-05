package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.task.TaskManager;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Parser {

    private static final String HELP_COMMAND = "help";
    private static final String ADD_COMMAND = "add";
    private static final String LIST_COMMAND = "list";
    private static final String DELETE_COMMAND = "delete";
    private static final String EXIT_COMMAND = "bye";

    private static final String FLAG_REGEX = "^--\\w+";

    public static String printCommandOptions(Map<String, String> commandOptions) {

        String flagsToArguments = "";

        for (String flag: commandOptions.keySet()){
            flagsToArguments += flag + " = " + commandOptions.get(flag) + "\n";
        }

        System.out.println(flagsToArguments);

        return flagsToArguments;
    }

    public static Map getCommandOptions(String commandArguments) {

        Map<String, String> flagsToArguments = new HashMap<>();
        String[] tokens = commandArguments.split("\\s+");
        String mainArgument = "";

        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].matches(FLAG_REGEX)) {
                flagsToArguments.put(tokens[i], tokens[i + 1]);
                i++;
            } else {
                mainArgument += tokens[i] + " ";
            }
        }

        flagsToArguments.put("mainArgument", mainArgument.trim());

        return flagsToArguments;
    }

    public static String parseCommand(String userInput) {

        String[] inputArguments = userInput.split("\\s+", 2);
        String command = inputArguments[0];
        Map<String, String> commandOptions = new HashMap<>();

        if (inputArguments.length == 2) {
            commandOptions = getCommandOptions(inputArguments[1]);
        }

        String mapFlagsToArguments = printCommandOptions(commandOptions);


        switch (command) {
        case HELP_COMMAND:
        case ADD_COMMAND:
        case LIST_COMMAND:
        case DELETE_COMMAND:
        case EXIT_COMMAND:
        default:
        }


        return userInput;
    }


}
