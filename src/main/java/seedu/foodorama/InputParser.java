package seedu.foodorama;

import seedu.foodorama.command.CommandNames;
import seedu.foodorama.exceptions.FoodoramaException;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the parsing of user inputs to extract the command and parameters.
 */
public class InputParser {
    private static final Ui UI = new Ui();
    private static final int SPLIT_LIMIT = 2;

    /**
     * Gets the command type the user gave from the input.
     * @param input text the user inputted
     * @return the respective command if it exists
     * @throws FoodoramaException if no command can be found in users input
     *
     * @author Dniv-ra
     */
    public CommandNames getCommandName(String input) throws FoodoramaException {
        String trimmedInput = input.trim() + " ";
        for (CommandNames command : CommandNames.values()) {
            if (trimmedInput.startsWith(command.getName() + " ")) {
                return command;
            }
        }
        throw new FoodoramaException(UI.getInvalidCommandMsg());
    }

    /**
     * Extracts the parameters of a command from the user input into an ArrayList.
     * @param input user input
     * @param inputCommand the type of command the input is
     * @return parameters of the command present in the input
     * @throws FoodoramaException if certain command types are missing parameters
     *
     * @author Dniv-ra
     */
    public ArrayList<String> getParameters(String input, CommandNames inputCommand) throws FoodoramaException {
        Ui ui  = new Ui();
        ArrayList<String> parameters = new ArrayList<>();

        //Replace the first command part with null, and you'll be left with params
        String parameterString = input.replaceFirst(inputCommand.getName(), "").trim();
        switch (inputCommand) {

        //No parameter commands
        case CLEAR_DISH:
        case CLEAR_INGR:
        case CLEAR_ALL:
        case SORT_DISH:
        case SORT_INGR:
        case RAND_RISH_NAME:
        case HELP:
            if (!parameterString.isBlank()) {
                ui.printExcessParamMsg(inputCommand.getName());
            }
            break;

        //One parameter commands just add the parameterString
        case GRAPH:
        case ADD_DISH_WASTE:
        case ADD_INGR_WASTE:
        case ADD_INGR_STORED:
        case DELETE_DISH:
        case DELETE_INGR:
        case SET_DISH_LIMIT:
        case SET_INGR_LIMIT:
        case SET_INGR_EXPIRY:
        case ADD_INGR:
        case ADD_DISH:
        case EDIT_INGR_NAME:
        case EDIT_DISH_WASTE:
        case EDIT_DISH_NAME:
        case EDIT_INGR_WASTE:
        case EDIT_INGR_STORAGE:
        case LIST:
            parameters.add(parameterString);
            break;

        //Multi param is same as one but will need to split further
        case LINK:
            String[] splitString = parameterString.split("/", SPLIT_LIMIT);
            if (splitString.length != SPLIT_LIMIT) {
                throw new FoodoramaException(ui.getMissingParameters());
            }
            for (String param : splitString) {
                String trimmedParam = param.trim();
                if (trimmedParam.isBlank()) {
                    throw new FoodoramaException(ui.getMissingParameters());
                }
                parameters.add(trimmedParam);
            }
            break;

        case FIND:
            String[] splitBySpace = parameterString.split(" ", 2);
            parameters.addAll(List.of(splitBySpace));
            break;

        default:
            assert false;
            break;
        }

        return parameters;
    }

}
