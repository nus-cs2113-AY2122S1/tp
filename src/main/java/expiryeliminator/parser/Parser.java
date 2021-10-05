package expiryeliminator.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import expiryeliminator.commands.Command;
import expiryeliminator.commands.IncorrectCommand;
import expiryeliminator.commands.AddRecipeCommand;
import expiryeliminator.commands.AddIngredientCommand;
import expiryeliminator.commands.ByeCommand;
import expiryeliminator.data.Ingredient;
import expiryeliminator.data.IngredientList;
import expiryeliminator.data.exception.DuplicateDataException;

/**
 * Parses user input.
 */
public class Parser {
    public static final String MESSAGE_QUANTITY_AND_INGREDIENT_NUMBER_NOT_EQUAL = "Please ensure each " +
            "ingredient has one quantity. :)";
    //@@author bernardboey-reused
    // Reused from https://github.com/bernardboey/ip/blob/master/src/main/java/duke/parser/Parser.java
    // with minor modifications

    /** Used for initial separation of command word and args. */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("^(?<command>[^/]+)(?<args> .*)?$");
    /**
     * Used for separation of each prefix and argument.
     * E.g. this matches "/a aaaaa aaaa"
     */
    private static final Pattern ARGS_FORMAT = Pattern.compile("\\w+/([^/\\s]+( +|$))+");

    private static final String PREFIX_RECIPE = "r";
    private static final String PREFIX_INGREDIENT = "i";
    private static final String PREFIX_QUANTITY = "q";
    private static final String PREFIX_EXPIRY = "e";

    private static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format!";
    private static final String MESSAGE_UNRECOGNISED_COMMAND = "I'm sorry, but I don't know what that means :-(";
    private static final String MESSAGE_INVALID_QUANTITY = "The specified quantity is not a valid integer.";
    public static final String EXPIRY_NOT_CHECKED_FOR_RECIPE = "not needed";

    /**
     * Parses user input as a command.
     *
     * @param userInput Input command together with any arguments.
     * @return Command that corresponds to the user input, if valid.
     */
    public static Command parseCommand(String userInput) {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput);
        if (!matcher.matches()) {
            throw new RuntimeException(MESSAGE_INVALID_COMMAND_FORMAT);
        }
        final String command = matcher.group("command");
        String args = matcher.group("args");

        // May not contain all arguments (e.g. if user did not provide).
        final HashMap<String, ArrayList<String>> prefixesToArgs;

        //catches null if user types in wrong prefix.
        try {
            prefixesToArgs = parseArgs(args);
        } catch (NullPointerException error) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT);
        }

        switch (command) {
        case AddIngredientCommand.COMMAND_WORD:
            return prepareAddIngredient(prefixesToArgs);
        case ByeCommand.COMMAND_WORD:
            return new ByeCommand();
        case AddRecipeCommand.COMMAND_WORD:
            return prepareAddRecipe(prefixesToArgs);
        default:
            return new IncorrectCommand(MESSAGE_UNRECOGNISED_COMMAND);
        }
    }
    //@@author

    /**
     * Parses the arguments to categories of quantity, item, recipe and expiry date.
     *
     * @param args Arguments from the parsed input.
     * @return A hash map of each prefix to an array list.
     */
    private static HashMap<String, ArrayList<String>> parseArgs(String args) {
        // TODO(bernardboey): Change the way this is done to allow for multiple of the same tags.
        // Separate to different class and feed in the argument prefixes that are expected when parsing?
        /* Changed method so that it can store multiple values for each prefix, but have to manually set up the Hash
        Map */
        args = args == null ? "" : args.trim();
        final HashMap<String, ArrayList<String>> prefixesToArgs = new HashMap<>();
        setUpPrefixesToArgs(prefixesToArgs);
        final Matcher matcher = ARGS_FORMAT.matcher(args);

        while (matcher.find()) {
            final String match = matcher.group();
            final String[] prefixAndArg = match.split("/");
            final String prefix = prefixAndArg[0];
            final String arg = prefixAndArg[1].trim();
            prefixesToArgs.get(prefix).add(arg);
        }
        return prefixesToArgs;
    }

    private static void setUpPrefixesToArgs(HashMap<String, ArrayList<String>> prefixesToArgs) {
        prefixesToArgs.put(PREFIX_INGREDIENT,new ArrayList<>());
        prefixesToArgs.put(PREFIX_EXPIRY,new ArrayList<>());
        prefixesToArgs.put(PREFIX_RECIPE,new ArrayList<>());
        prefixesToArgs.put(PREFIX_QUANTITY,new ArrayList<>());
    }

    private static Command prepareAddIngredient(HashMap<String, ArrayList<String>> prefixesToArgs) {
        final String ingredient = prefixesToArgs.get(PREFIX_INGREDIENT).get(0);
        final String quantity = prefixesToArgs.get(PREFIX_QUANTITY).get(0);
        final String expiry = prefixesToArgs.get(PREFIX_EXPIRY).get(0);
        IncorrectCommand result = checkArgs(ingredient, quantity, expiry);
        if (result != null) {
            return result;
        }
        try {
            return new AddIngredientCommand(ingredient, parseQuantity(quantity), expiry);
        } catch (NumberFormatException e) {
            return new IncorrectCommand(MESSAGE_INVALID_QUANTITY);
        }
    }

    private static IncorrectCommand checkArgs(String ingredient, String quantity, String expiry) {
        if (ingredient == null || ingredient.isBlank()) {
            return new IncorrectCommand("Missing ingredient");
        }
        if (quantity == null || quantity.isBlank()) {
            return new IncorrectCommand("Missing quantity");
        }
        if (expiry == null || expiry.isBlank()) {
            return new IncorrectCommand("Missing expiry");
        }
        return null;
    }

    /**
     * Creates a AddRecipeCommand from the inputs
     *
     * @param prefixesToArgs A hash map of prefixes to their arguments
     * @return a AddRecipeCommand with the recipe name and the ingredients if successful
     * and an IncorrectCommand if not
     */
    private static Command prepareAddRecipe(HashMap<String,ArrayList<String>> prefixesToArgs) {
        final String recipe;
        try {
           recipe = prefixesToArgs.get(PREFIX_RECIPE).get(0);
        } catch (Exception e) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT);
        }
        final ArrayList<String> name = prefixesToArgs.get(PREFIX_INGREDIENT);
        final ArrayList<String> quantity = prefixesToArgs.get(PREFIX_QUANTITY);
        final IngredientList ingredients = new IngredientList();
        if(name.size() != quantity.size()) {
            return new IncorrectCommand(MESSAGE_QUANTITY_AND_INGREDIENT_NUMBER_NOT_EQUAL);
        }
        for(int i = 0; i < name.size(); i ++) {
            IncorrectCommand error = checkArgs(name.get(i), quantity.get(i), EXPIRY_NOT_CHECKED_FOR_RECIPE);
            if (error != null) {
                return error;
            }
            error = addIngredients(name, quantity, ingredients, i);
            if (error != null) {
                return error;
            }
        }
        return new AddRecipeCommand(recipe,ingredients);
    }

    /**
     * Adds the ingredients into the ingredient list.
     *
     * @param name Array of name of ingredients
     * @param quantity Array of quantity of ingredients
     * @param ingredients Ingredient list to store the ingredients
     * @param i index of the loop
     * @return null if there's no error and an IncorrectCommand if there is.
     */
    private static IncorrectCommand addIngredients
            (ArrayList<String> name, ArrayList<String> quantity, IngredientList ingredients, int i) {
        Ingredient ingredient;
        try {
            ingredient = new Ingredient(name.get(i),parseQuantity(quantity.get(i)),null);
        } catch (NumberFormatException e) {
            return new IncorrectCommand(MESSAGE_INVALID_QUANTITY);
        }
        try {
            ingredients.add(ingredient);
        } catch (DuplicateDataException e) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT);
        }
        return null;
    }

    private static int parseQuantity(String quantityString) throws NumberFormatException {
        return Integer.parseInt(quantityString);
    }
}
