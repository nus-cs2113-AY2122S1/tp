package expiryeliminator.parser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import expiryeliminator.commands.AddIngredientCommand;
import expiryeliminator.commands.AddRecipeCommand;
import expiryeliminator.commands.ByeCommand;
import expiryeliminator.commands.Command;
import expiryeliminator.commands.CookedRecipeCommand;
import expiryeliminator.commands.DecrementCommand;
import expiryeliminator.commands.DeleteExpiredIngredientCommand;
import expiryeliminator.commands.DeleteIngredientCommand;
import expiryeliminator.commands.DeleteRecipeCommand;
import expiryeliminator.commands.HelpCommand;
import expiryeliminator.commands.IncorrectCommand;
import expiryeliminator.commands.IncrementCommand;
import expiryeliminator.commands.ListCommand;
import expiryeliminator.commands.ListIngredientExpiringCommand;
import expiryeliminator.commands.ListIngredientsExpiredCommand;
import expiryeliminator.commands.ListRecipeCommand;
import expiryeliminator.commands.ListRecipesUserCanCookCommand;
import expiryeliminator.commands.ShoppingListCommand;
import expiryeliminator.commands.UpdateRecipeCommand;
import expiryeliminator.commands.UpdateUnitsCommand;
import expiryeliminator.commands.ViewIngredientCommand;
import expiryeliminator.commands.ViewRecipeCommand;
import expiryeliminator.commands.DeleteIngredientInRecipeCommand;
import expiryeliminator.parser.argparser.ExpiryDateParser;
import expiryeliminator.parser.argparser.IngredientParser;
import expiryeliminator.parser.argparser.QuantityParser;
import expiryeliminator.parser.argparser.RecipeParser;
import expiryeliminator.parser.argparser.UnitParser;
import expiryeliminator.parser.exception.InvalidArgFormatException;
import expiryeliminator.parser.exception.InvalidPrefixException;
import expiryeliminator.parser.exception.MissingPrefixException;
import expiryeliminator.parser.exception.MultipleArgsException;
import expiryeliminator.parser.prefix.MultipleArgPrefix;
import expiryeliminator.parser.prefix.OptionalArgPrefix;
import expiryeliminator.parser.prefix.SingleArgPrefix;


/**
 * Parses user input.
 */
public class Parser {
    //@@author bernardboey-reused
    // Reused from https://github.com/bernardboey/ip/blob/master/src/main/java/duke/parser/Parser.java
    // with minor modifications

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("^(?<command>[^/]+)(?<args> .*)?$");

    private static final SingleArgPrefix PREFIX_RECIPE = new SingleArgPrefix("r");
    private static final SingleArgPrefix PREFIX_INGREDIENT = new SingleArgPrefix("i");
    private static final SingleArgPrefix PREFIX_QUANTITY = new SingleArgPrefix("q");
    private static final SingleArgPrefix PREFIX_EXPIRY = new SingleArgPrefix("e");
    private static final OptionalArgPrefix PREFIX_OPTIONAL_UNIT = new OptionalArgPrefix("u");
    private static final MultipleArgPrefix PREFIX_MULTIPLE_INGREDIENT = new MultipleArgPrefix(PREFIX_INGREDIENT);
    private static final MultipleArgPrefix PREFIX_MULTIPLE_QUANTITY = new MultipleArgPrefix(PREFIX_QUANTITY);
    private static final MultipleArgPrefix PREFIX_MULTIPLE_RECIPE = new MultipleArgPrefix(PREFIX_RECIPE);

    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format!\n%1$s";
    public static final String MESSAGE_INVALID_ARGUMENT_FORMAT = "Invalid argument format!\n%1$s";
    private static final String MESSAGE_UNRECOGNISED_COMMAND = "I'm sorry, but I don't know what that means :-(\n"
            + "For help, please type `help`.";

    /**
     * Parses user input as a command.
     *
     * @param userInput Input command together with any arguments.
     * @return Command that corresponds to the user input, if valid.
     */
    public static Command parseCommand(String userInput) {
        assert userInput != null : "User input string cannot be null";
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput);
        if (!matcher.matches()) {
            return new IncorrectCommand(MESSAGE_UNRECOGNISED_COMMAND);
        }
        final String command = matcher.group("command");
        String args = matcher.group("args");

        try {
            switch (command) {
            case AddIngredientCommand.COMMAND_WORD:
                return prepareAddIngredient(args);
            case DecrementCommand.COMMAND_WORD:
                return prepareDecrementIngredient(args);
            case IncrementCommand.COMMAND_WORD:
                return prepareIncrementIngredient(args);
            case DeleteIngredientCommand.COMMAND_WORD:
                return prepareDeleteIngredient(args);
            case ListCommand.COMMAND_WORD:
                return new ListCommand();
            case ListIngredientExpiringCommand.COMMAND_WORD:
                return new ListIngredientExpiringCommand();
            case ListIngredientsExpiredCommand.COMMAND_WORD:
                return new ListIngredientsExpiredCommand();
            case DeleteExpiredIngredientCommand.COMMAND_WORD:
                return new DeleteExpiredIngredientCommand();
            case ViewIngredientCommand.COMMAND_WORD:
                return prepareViewIngredient(args);
            case AddRecipeCommand.COMMAND_WORD:
                return prepareAddRecipe(args);
            case DeleteRecipeCommand.COMMAND_WORD:
                return prepareDeleteRecipe(args);
            case ListRecipeCommand.COMMAND_WORD:
                return new ListRecipeCommand();
            case ListRecipesUserCanCookCommand.COMMAND_WORD:
                return new ListRecipesUserCanCookCommand();
            case ViewRecipeCommand.COMMAND_WORD:
                return prepareViewRecipe(args);
            case UpdateRecipeCommand.COMMAND_WORD:
                return prepareUpdateRecipe(args);
            case ShoppingListCommand.COMMAND_WORD:
                return prepareShoppingList(args);
            case CookedRecipeCommand.COMMAND_WORD:
                return prepareCookedRecipe(args);
            case UpdateUnitsCommand.COMMAND_WORD:
                return prepareUpdateUnits(args);
            case DeleteIngredientInRecipeCommand.COMMAND_WORD:
                return prepareDeleteRecipeIngredient(args);
            case ByeCommand.COMMAND_WORD:
                return new ByeCommand();
            case HelpCommand.COMMAND_WORD:
                return new HelpCommand();
            default:
                return new IncorrectCommand(MESSAGE_UNRECOGNISED_COMMAND);
            }
        } catch (InvalidArgFormatException e) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_ARGUMENT_FORMAT, e.getMessage()));
        }

    }
    //@@author

    private static Command prepareAddIngredient(String args) throws InvalidArgFormatException {
        final ArgsParser argsParser = new ArgsParser(PREFIX_INGREDIENT, PREFIX_OPTIONAL_UNIT);
        try {
            argsParser.parse(args);
        } catch (InvalidPrefixException | MissingPrefixException | MultipleArgsException e) {
            return new IncorrectCommand(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddIngredientCommand.MESSAGE_USAGE));
        }
        final String ingredient = new IngredientParser().parse(argsParser.getSingleArg(PREFIX_INGREDIENT));
        final String unitString = new UnitParser().parse(argsParser.getSingleArg(PREFIX_OPTIONAL_UNIT));

        return new AddIngredientCommand(ingredient, unitString);
    }

    private static Command prepareDecrementIngredient(String args) throws InvalidArgFormatException {
        final ArgsParser argsParser = new ArgsParser(PREFIX_INGREDIENT, PREFIX_QUANTITY);
        try {
            argsParser.parse(args);
        } catch (InvalidPrefixException | MissingPrefixException | MultipleArgsException e) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DecrementCommand.MESSAGE_USAGE));
        }

        final String ingredient = new IngredientParser().parse(argsParser.getSingleArg(PREFIX_INGREDIENT));
        final int quantity = new QuantityParser().parse(argsParser.getSingleArg(PREFIX_QUANTITY));
        return new DecrementCommand(ingredient, quantity);
    }

    private static Command prepareIncrementIngredient(String args) throws InvalidArgFormatException {
        final ArgsParser argsParser = new ArgsParser(PREFIX_INGREDIENT, PREFIX_QUANTITY, PREFIX_EXPIRY);
        try {
            argsParser.parse(args);
        } catch (InvalidPrefixException | MissingPrefixException | MultipleArgsException e) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, IncrementCommand.MESSAGE_USAGE));
        }

        final String ingredient = new IngredientParser().parse(argsParser.getSingleArg(PREFIX_INGREDIENT));
        final int quantity = new QuantityParser().parse(argsParser.getSingleArg(PREFIX_QUANTITY));
        final LocalDate expiry = new ExpiryDateParser().parse(argsParser.getSingleArg(PREFIX_EXPIRY));
        return new IncrementCommand(ingredient, quantity, expiry);
    }

    private static Command prepareDeleteIngredient(String args) throws InvalidArgFormatException {
        final ArgsParser argsParser = new ArgsParser(PREFIX_INGREDIENT);
        try {
            argsParser.parse(args);
        } catch (InvalidPrefixException | MissingPrefixException | MultipleArgsException e) {
            return new IncorrectCommand(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteIngredientCommand.MESSAGE_USAGE));
        }

        final String ingredient = new IngredientParser().parse(argsParser.getSingleArg(PREFIX_INGREDIENT));
        return new DeleteIngredientCommand(ingredient);
    }

    /**
     * Creates a AddRecipeCommand from the inputs.
     *
     * @param args Command arguments.
     * @return a AddRecipeCommand with the recipe name and the ingredients if successful
     * and an IncorrectCommand if not.
     */
    private static Command prepareAddRecipe(String args) throws InvalidArgFormatException {
        final ArgsParser argsParser = new ArgsParser(PREFIX_RECIPE, PREFIX_MULTIPLE_INGREDIENT,
                PREFIX_MULTIPLE_QUANTITY);
        try {
            argsParser.parse(args);
        } catch (InvalidPrefixException | MissingPrefixException | MultipleArgsException e) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddRecipeCommand.MESSAGE_USAGE));
        }

        final String recipe = new RecipeParser().parse(argsParser.getSingleArg(PREFIX_RECIPE));
        final ArrayList<String> ingredients =
                new IngredientParser().parse(argsParser.getArgList(PREFIX_MULTIPLE_INGREDIENT));
        final ArrayList<Integer> quantities =
                new QuantityParser().parse(argsParser.getArgList(PREFIX_MULTIPLE_QUANTITY));

        if (ingredients.size() != quantities.size()) {
            return new IncorrectCommand("Should have same number of ingredient names and quantities");
        }
        assert !recipe.isBlank();
        return new AddRecipeCommand(recipe, ingredients, quantities);
    }

    /**
     * Creates a DeleteRecipeCommand from the inputs.
     *
     * @param args Command arguments.
     * @return a DeleteRecipeCommand with the recipe name if successful and an IncorrectCommand if not.
     */
    private static Command prepareDeleteRecipe(String args) throws InvalidArgFormatException {
        final ArgsParser argsParser = new ArgsParser(PREFIX_RECIPE);
        try {
            argsParser.parse(args);
        } catch (InvalidPrefixException | MissingPrefixException | MultipleArgsException e) {
            return new IncorrectCommand(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteRecipeCommand.MESSAGE_USAGE));
        }

        final String recipe = new RecipeParser().parse(argsParser.getSingleArg(PREFIX_RECIPE));
        assert !recipe.isBlank();
        return new DeleteRecipeCommand(recipe);
    }

    /**
     * Creates a ViewIngredientCommand from the inputs.
     *
     * @param args Command arguments.
     * @return a DeleteRecipeCommand with the recipe name if successful and an IncorrectCommand if not.
     */
    private static Command prepareViewIngredient(String args) throws InvalidArgFormatException {
        final ArgsParser argsParser = new ArgsParser(PREFIX_INGREDIENT);
        try {
            argsParser.parse(args);
        } catch (InvalidPrefixException | MissingPrefixException | MultipleArgsException e) {
            return new IncorrectCommand(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewIngredientCommand.MESSAGE_USAGE));
        }

        final String ingredient = new IngredientParser().parse(argsParser.getSingleArg(PREFIX_INGREDIENT));
        return new ViewIngredientCommand(ingredient);
    }

    /**
     * Creates a UpdateRecipeCommand from the inputs.
     *
     * @param args Command arguments.
     * @return a DeleteRecipeCommand with the recipe name if successful and an IncorrectCommand if not.
     */
    private static Command prepareUpdateRecipe(String args) throws InvalidArgFormatException {
        final ArgsParser argsParser = new ArgsParser(PREFIX_RECIPE, PREFIX_MULTIPLE_INGREDIENT,
                PREFIX_MULTIPLE_QUANTITY);
        try {
            argsParser.parse(args);
        } catch (InvalidPrefixException | MissingPrefixException | MultipleArgsException e) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    UpdateRecipeCommand.MESSAGE_USAGE));
        }

        final String recipe = new RecipeParser().parse(argsParser.getSingleArg(PREFIX_RECIPE));
        final ArrayList<String> ingredients =
                new IngredientParser().parse(argsParser.getArgList(PREFIX_MULTIPLE_INGREDIENT));
        final ArrayList<Integer> quantities =
                new QuantityParser().parse(argsParser.getArgList(PREFIX_MULTIPLE_QUANTITY));
        if (ingredients.size() != quantities.size()) {
            return new IncorrectCommand("Should have same number of ingredient names and quantities");
        }
        assert !recipe.isBlank();
        return new UpdateRecipeCommand(recipe, ingredients, quantities);
    }

    /**
     * Creates a DeleteIngredientInRecipeCommand from the inputs.
     *
     * @param args Command arguments.
     * @return a DeleteRecipeCommand with the recipe name if successful and an IncorrectCommand if not.
     */
    private static Command prepareDeleteRecipeIngredient(String args) throws InvalidArgFormatException {
        final ArgsParser argsParser = new ArgsParser(PREFIX_RECIPE, PREFIX_MULTIPLE_INGREDIENT);
        try {
            argsParser.parse(args);
        } catch (InvalidPrefixException | MissingPrefixException | MultipleArgsException e) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteIngredientInRecipeCommand.MESSAGE_USAGE));
        }
        final String recipe = new RecipeParser().parse(argsParser.getSingleArg(PREFIX_RECIPE));
        final ArrayList<String> ingredients =
                new IngredientParser().parse(argsParser.getArgList(PREFIX_MULTIPLE_INGREDIENT));
        assert !recipe.isBlank();
        return new DeleteIngredientInRecipeCommand(recipe, ingredients);
    }

    /**
     * Creates a CookedRecipeCommand from the inputs.
     *
     * @param args Command arguments.
     * @return a DeleteRecipeCommand with the recipe name if successful and an IncorrectCommand if not.
     */
    private static Command prepareCookedRecipe(String args) throws InvalidArgFormatException {
        final ArgsParser argsParser = new ArgsParser(PREFIX_RECIPE);
        try {
            argsParser.parse(args);
        } catch (InvalidPrefixException | MissingPrefixException | MultipleArgsException e) {
            return new IncorrectCommand(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, CookedRecipeCommand.MESSAGE_USAGE));
        }

        final String recipe = new RecipeParser().parse(argsParser.getSingleArg(PREFIX_RECIPE));
        return new CookedRecipeCommand(recipe);
    }

    /**
     * Creates a ViewRecipeCommand from the inputs.
     *
     * @param args Command arguments.
     * @return a DeleteRecipeCommand with the recipe name if successful and an IncorrectCommand if not.
     */
    private static Command prepareViewRecipe(String args) throws InvalidArgFormatException {
        final ArgsParser argsParser = new ArgsParser(PREFIX_RECIPE);
        try {
            argsParser.parse(args);
        } catch (InvalidPrefixException | MissingPrefixException | MultipleArgsException e) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewRecipeCommand.MESSAGE_USAGE));
        }

        final String recipe = new RecipeParser().parse(argsParser.getSingleArg(PREFIX_RECIPE));
        return new ViewRecipeCommand(recipe);
    }

    /**
     * Creates a ShoppingListCommand from the inputs.
     *
     * @param args Command arguments.
     * @return a DeleteRecipeCommand with the recipe name if successful and an IncorrectCommand if not.
     */
    private static Command prepareShoppingList(String args) throws InvalidArgFormatException {
        final ArgsParser argsParser = new ArgsParser(PREFIX_MULTIPLE_RECIPE);
        try {
            argsParser.parse(args);
        } catch (InvalidPrefixException | MissingPrefixException | MultipleArgsException e) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ShoppingListCommand.MESSAGE_USAGE));
        }
        final ArrayList<String> recipe = new RecipeParser().parse(argsParser.getArgList(PREFIX_MULTIPLE_RECIPE));
        return new ShoppingListCommand(recipe);
    }

    /**
     * Creates a UpdateUnitsCommand from the inputs.
     *
     * @param args Command arguments.
     * @return a DeleteRecipeCommand with the recipe name if successful and an IncorrectCommand if not.
     */
    private static Command prepareUpdateUnits(String args) throws InvalidArgFormatException {
        final ArgsParser argsParser = new ArgsParser(PREFIX_INGREDIENT, PREFIX_OPTIONAL_UNIT);

        try {
            argsParser.parse(args);
        } catch (InvalidPrefixException | MissingPrefixException | MultipleArgsException e) {
            return new IncorrectCommand(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateUnitsCommand.MESSAGE_USAGE));
        }


        final String unitString = new UnitParser().parse(argsParser.getSingleArg(PREFIX_OPTIONAL_UNIT));
        final String ingredient = new IngredientParser().parse(argsParser.getSingleArg(PREFIX_INGREDIENT));

        return new UpdateUnitsCommand(ingredient, unitString);
    }
}
