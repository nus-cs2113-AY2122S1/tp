package seedu.duke;

import seedu.duke.command.CommandNames;
import seedu.duke.exceptions.CommandNotAvailableException;

import java.util.ArrayList;
import java.util.List;

public class InputParser {
    public CommandNames getCommandName(String input) throws CommandNotAvailableException {
        for (CommandNames command : CommandNames.values()) {
            if (input.startsWith(command.getName())) {
                return command;
            }
        }
        throw new CommandNotAvailableException();
    }

    public ArrayList<String> getParameters(String input, CommandNames inputCommand) {
        ArrayList<String> parameters = new ArrayList<>();

        //Replace the first command part with null, and you'll be left with params
        String parameterString = input.replaceFirst(inputCommand.getName(), "").trim();
        switch (inputCommand) {

        //No parameter commands check if there are params and if so error(Maybe not needed)
        case TEST:
            if (!parameterString.isBlank()) {
                System.out.println("Extra params, do not include next time");
            }
            return null;

        //One parameter commands just add the parameterString
        case ADD_DISH:
        case DELETE_DISH:
        case DELETE_INGR:
        case LIST:
            parameters.add(parameterString);
            break;

        //Multi param is same as one but will need to split further
        //Following commands of format number, name
        case ADD_INGR:
        case ADD_DISH_WASTE:
        case ADD_INGR_WASTE:
            parameters.addAll(List.of(parameterString.split(" ", 2)));
            break;

        case ADD_CONSTITUENT:
            //TODO trim inputs
            parameters.addAll(List.of(parameterString.split("/", 2)));
            break;
        default:
            break;
        }

        return parameters;
    }

}
