package seedu.duke;

import seedu.duke.command.AddModuleCommand;
import seedu.duke.command.Command;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.ListModulesCommand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Parser {
    private static final int USERINPUT_SPLIT_COUNT = 2;

    public static Command parseUserInput(String userInput) throws CustomException {
        String[] userInputSplit = splitFirstSpace(userInput);
        String commandString = userInputSplit[0];
        String argument = userInputSplit[1];

        Command command;
        switch (commandString) {
        case Command.COMMAND_EXIT:
            command = new ExitCommand(argument);
            break;

        case Command.COMMAND_LIST_MODULES:
            command = new ListModulesCommand(argument);
            break;

        case Command.COMMAND_ADD_MODULE:
            command = new AddModuleCommand(argument);
            break;

        default:
            throw new CustomException("Unknown Command");
        }

        return command;
    }

    public static String[] splitFirstSpace(String string) {
        String[] result = string.trim().split("\\s+", USERINPUT_SPLIT_COUNT);
        if (result.length != USERINPUT_SPLIT_COUNT) {
            return new String[]{result[0], ""};
        }

        return result;
    }

    public static HashMap<String,String> getArgumentsFromString(String string, String[] argumentKeys) {
        String argumentString = " " + string;
        HashMap<String, Integer> argumentIndexMap = new HashMap<>();
        ArrayList<Integer> argumentIndexes = new ArrayList<>();
        for (String key : argumentKeys) {
            String searchString = String.format(" %s/", key);
            int index = argumentString.indexOf(searchString);
            if (index != -1) {
                argumentIndexMap.put(key, index);
                argumentIndexes.add(index);
            }
        }
        Collections.sort(argumentIndexes);

        HashMap<String,String> result = new HashMap<>();
        for (String key : argumentIndexMap.keySet()) {
            int argumentIndex = argumentIndexMap.get(key);
            int startIndex = argumentIndex + key.length() + 1;

            String value;
            int listIndex = argumentIndexes.indexOf(argumentIndex);
            boolean isLastIndex = listIndex == (argumentIndexes.size() - 1);
            if (isLastIndex) {
                value = string.substring(startIndex);
            } else {
                int nextIndex = argumentIndexes.get(listIndex + 1);
                value = string.substring(startIndex, nextIndex - 1);
            }
            value = value.trim();

            result.put(key, value);
        }

        return result;
    }
}
