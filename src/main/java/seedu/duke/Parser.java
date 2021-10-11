package seedu.duke;

import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.InsufficientParametersException;
import seedu.duke.ingredients.AddIngredient;
import seedu.duke.ingredients.Ingredient;
import seedu.duke.ingredients.IngredientList;

public class Parser {
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_ADD = "add";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_UPDATE = "update";
    private static final String COMMAND_EXIT = "exit";

    private static final String INVALID_COMMAND = "Invalid command!";

    private static final String SPACE_SEPARATOR = " ";


    static boolean isExit(String command) {
        return (command.equals(COMMAND_EXIT));
    }

    public static String parse(String command) throws DukeException {
        String[] words = command.split(SPACE_SEPARATOR);

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
            return INVALID_COMMAND;
        }
    }


    private static String parseUpdateCommand(String command) {
        String resultMsg = "";
        return resultMsg;
    }

    private static String parseAddCommand(String command) {
        String[] userInput = command.split(SPACE_SEPARATOR, 2);
        String ingredientName, ingredientUnit, ingredientExpiry;
        double ingredientAmount;

        try {
            ingredientName = AddIngredient.getIngredientName(userInput[1]);
            ingredientAmount = AddIngredient.getIngredientAmount(userInput[1]);
            ingredientUnit = AddIngredient.getIngredientUnit(userInput[1]);
            ingredientExpiry = AddIngredient.getIngredientExpiry(userInput[1]);
        } catch (InsufficientParametersException e){
            return e.getMessage();
        }
        catch (DukeException e){
            return "Amount is not a number. Please try again";
        }

        Ingredient newIngredient = new Ingredient(ingredientName, ingredientAmount,ingredientUnit, ingredientExpiry);
        return IngredientList.getInstance().addNewIngredient(newIngredient);
    }

    private static String parseListCommand() throws DukeException {
        String resultMsg = "";
        int i;

        if (IngredientList.getInstance().getInventoryStock() == 0) {
            resultMsg = "Inventory is empty!";
            return resultMsg;
        }

        for (i = 0; i < IngredientList.getInstance().getInventoryStock() - 1; i++) {
            resultMsg += (i + 1) + "."
                    + IngredientList.getInstance().getIngredientInfo(i + 1)
                    + '\n' + '\t';

        }
        resultMsg = resultMsg + IngredientList.getInstance().getInventoryStock() + "."
                + IngredientList.getInstance().getIngredientInfo(i + 1);

        return resultMsg;
    }

    private static String parseDeleteCommand(String command) throws DukeException {
        String detail = command.substring(COMMAND_DELETE.length()).trim();
        String resultMsg;

        if (detail.length() <= 0) {
            resultMsg = "Nothing to delete!";
            return resultMsg;
        }
        try {
            int ingredientRemoveNumber = Integer.parseInt(detail);
            Ingredient removedIngredient = IngredientList.getInstance().remove(ingredientRemoveNumber);
            resultMsg = "Noted. This has been removed:\n"
                    + "t" + removedIngredient.toString();
            return resultMsg;
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid number format!");
        }
    }
}
