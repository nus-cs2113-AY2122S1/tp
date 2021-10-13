package seedu.duke;

import seedu.duke.command.UpdateCommand;
import seedu.duke.command.AddCommand;
import seedu.duke.command.DeleteCommand;
import seedu.duke.command.ListCommand;
import seedu.duke.exceptions.DukeException;
import seedu.duke.ingredients.Ingredient;

import java.util.NoSuchElementException;

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
    private static final String INSUFFICIENT_PARAMETERS_MESSAGE = "The number of parameters is wrong!";

    private static final String SPACE_SEPARATOR = " ";
    private static final String INGREDIENT_NAME_SEPARATOR = "n/";
    private static final String INGREDIENT_AMOUNT_SEPARATOR = "a/";
    private static final String INGREDIENT_UNITS_SEPARATOR = "u/";
    private static final String INGREDIENT_EXPIRY_SEPARATOR = "e/";


    static boolean isExit(String command) {
        return (command.equals(COMMAND_EXIT));
    }

    /**
     * Sends the input to the relevant command parser.
     *
     * @param command The user input String
     * @return the output message
     * @throws DukeException when there is an issue with the input
     */
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

    /**
     * Parses update command and splits input into ingredient parameters.
     *
     * @param command 1 line of user input
     * @return Ingredient updated message
     */
    private static String parseUpdateCommand(String command) throws DukeException {
        String resultMsg = "";
        int i;
        String delimiter = "n/|a/|u/|e/";
        String[] details = command.split(delimiter);

        if (details.length != 5) {
            throw new DukeException(INSUFFICIENT_PARAMETERS_MESSAGE);
        }

        assert (details.length == 5);

        String ingredientName = details[1].trim();
        double ingredientAmount;
        try {
            ingredientAmount = Double.parseDouble(details[2]);
        } catch (NumberFormatException e) {
            throw new DukeException(NUMBER_FORMAT_MESSAGE);
        }
        String ingredientUnits = details[3].trim();
        String ingredientExpiry = details[4].trim();
        Ingredient updatedIngredient =
                new Ingredient(ingredientName, ingredientAmount, ingredientUnits, ingredientExpiry);
        resultMsg = new UpdateCommand(updatedIngredient).run();

        if (resultMsg == "") {
            resultMsg = NOT_FOUND_MESSAGE;
        }
        return resultMsg;
    }

    /**
     * Parses add command and splits input into ingredient parameters,
     * then calls and executes the add command.
     *
     * @param command The user input String
     * @return Ingredient added message
     */
    private static String parseAddCommand(String command) throws DukeException {
        String delimiter = "n/|a/|u/|e/";
        String[] details = command.split(delimiter);
        if (details.length != 5) {
            throw new DukeException(INSUFFICIENT_PARAMETERS_MESSAGE);
        }

        assert (details.length == 5);

        String ingredientName = details[1].trim();
        double ingredientAmount;
        try {
            ingredientAmount = Double.parseDouble(details[2]);
        } catch (NumberFormatException e) {
            throw new DukeException(NUMBER_FORMAT_MESSAGE);
        }
        String ingredientUnit = details[3].trim();
        String ingredientExpiry = details[4].trim();

        Ingredient newIngredient = new Ingredient(ingredientName, ingredientAmount, ingredientUnit, ingredientExpiry);
        return new AddCommand(newIngredient).run();
    }

    /**
     * Calls and Executes the List Command.
     *
     * @return List of ingredients
     * @throws DukeException if trying to access non-existing ingredients
     */
    private static String parseListCommand() throws DukeException {
        String resultMsg = new ListCommand().run();
        return resultMsg;
    }

    /**
     * Calls and Executes the Delete Command.
     *
     * @param command The user input String
     * @return Ingredient Deleted Message
     * @throws DukeException if trying to access non-existing ingredients
     */
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
