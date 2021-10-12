package seedu.duke;

import seedu.duke.command.UpdateCommand;
import seedu.duke.command.DeleteCommand;
import seedu.duke.command.ListCommand;
import seedu.duke.exceptions.DukeException;
import seedu.duke.ingredients.IngredientList;

public class Parser {
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_ADD = "add";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_UPDATE = "update";
    private static final String COMMAND_EXIT = "exit";

    private static final String INVALID_COMMAND_MESSAGE = "Invalid command!";
    private static final String DELETE_ERROR_MESSAGE = "Nothing to remove!";
    private static final String NUMBER_FORMAT_MESSAGE = "Invalid number format!";
    private static final String NOT_FOUND_MESSAGE = "Ingredient not found!";


    private static final String SPACE_SEPARATOR = " ";


    static boolean isExit(String command) {
        return (command.equals(COMMAND_EXIT));
    }

    public static String parse(String command) throws DukeException {

        String[] words = command.split(SPACE_SEPARATOR, 2);

        switch (words[0]) {
        case COMMAND_LIST:
            return parseListCommand();
        case COMMAND_ADD:
            return parseAddCommand(command);
        case COMMAND_DELETE:
            return parseDeleteCommand(command);
        case COMMAND_UPDATE:
            return parseUpdateCommand(command);
        case COMMAND_EXIT:
            return "";
        default:
            return INVALID_COMMAND_MESSAGE;
        }
    }


    private static String parseUpdateCommand(String command) throws DukeException{
        String resultMsg = "";
        int i;
        String delimiter = "i/|a/|u/|e/";
        String[] details = command.split(delimiter);

        String ingredientName = details[0];
        Double ingredientAmount = Double.parseDouble(details[1]);
        String ingredientUnits = details[2];
        String ingredientExpiry = details[3];

        //For future versions, updating non-existing item will simply add item to stock.
        if (IngredientList.getInstance().getInventoryStock() == 0) {
            resultMsg = DELETE_ERROR_MESSAGE;
            return resultMsg;
        }

        for (i = 0; i < IngredientList.getInstance().getInventoryStock() - 1; i++) {
            if (ingredientName == IngredientList.getInstance().getIngredientInfo(i + 1).split(" ")[0]) {
                resultMsg = new UpdateCommand(i + 1, ingredientName, ingredientAmount, ingredientUnits, ingredientExpiry).run();
            }
        }
        if (resultMsg == "") {
            resultMsg = NOT_FOUND_MESSAGE;
        }
        return resultMsg;
    }

    private static String parseAddCommand(String command) {
        String resultMsg = "";
        return resultMsg;
    }

    private static String parseListCommand() throws DukeException {
        String resultMsg = new ListCommand().run();
        return resultMsg;
    }

    private static String parseDeleteCommand(String command) throws DukeException {
        String detail = command.substring(COMMAND_DELETE.length()).trim();
        String resultMsg;

        if (detail.length() <= 0) {
            resultMsg = DELETE_ERROR_MESSAGE;
            return resultMsg;
        }

        try {
            int ingredientRemoveNumber = Integer.parseInt(detail);
            resultMsg = new DeleteCommand(ingredientRemoveNumber).run();
            return resultMsg;
        } catch (NumberFormatException e) {
            throw new DukeException(NUMBER_FORMAT_MESSAGE);
        }
    }
}
