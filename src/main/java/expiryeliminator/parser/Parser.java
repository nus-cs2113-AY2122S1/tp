package expiryeliminator.parser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import expiryeliminator.commands.AddIngredientCommand;
import expiryeliminator.commands.AddRecipeCommand;
import expiryeliminator.commands.ByeCommand;
import expiryeliminator.commands.Command;
import expiryeliminator.commands.DecrementCommand;
import expiryeliminator.commands.DeleteIngredientCommand;
import expiryeliminator.commands.DeleteRecipeCommand;
import expiryeliminator.commands.IncorrectCommand;
import expiryeliminator.commands.IncrementCommand;
import expiryeliminator.commands.ListCommand;
import expiryeliminator.commands.ListIngredientExpiringCommand;
import expiryeliminator.commands.ListIngredientsExpiredCommand;
import expiryeliminator.commands.ListRecipeCommand;
import expiryeliminator.commands.ViewIngredientCommand;
import expiryeliminator.commands.ViewRecipeCommand;
import expiryeliminator.data.Ingredient;
import expiryeliminator.data.IngredientList;
import expiryeliminator.data.exception.DuplicateDataException;
import expiryeliminator.parser.argparser.ExpiryDateParser;
import expiryeliminator.parser.argparser.IngredientParser;
import expiryeliminator.parser.argparser.QuantityParser;
import expiryeliminator.parser.argparser.RecipeParser;
import expiryeliminator.parser.exception.InvalidArgFormatException;
import expiryeliminator.parser.exception.InvalidPrefixException;
import expiryeliminator.parser.exception.MissingPrefixException;
import expiryeliminator.parser.exception.MultipleArgsException;


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

    private static final Prefix PREFIX_RECIPE = new Prefix("r");
    private static final Prefix PREFIX_INGREDIENT = new Prefix("i");
    private static final Prefix PREFIX_QUANTITY = new Prefix("q");
    private static final Prefix PREFIX_EXPIRY = new Prefix("e");

    private static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format!";
    private static final String MESSAGE_UNRECOGNISED_COMMAND = "I'm sorry, but I don't know what that means :-(";

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
        case ViewIngredientCommand.COMMAND_WORD:
            return prepareViewIngredient(args);
        case AddRecipeCommand.COMMAND_WORD:
            return prepareAddRecipe(args);
        case DeleteRecipeCommand.COMMAND_WORD:
            return prepareDeleteRecipe(args);
        case ListRecipeCommand.COMMAND_WORD:
            return new ListRecipeCommand();
        case ViewRecipeCommand.COMMAND_WORD:
            return prepareViewRecipe(args);
        case ByeCommand.COMMAND_WORD:
            return new ByeCommand();
        default:
            return new IncorrectCommand(MESSAGE_UNRECOGNISED_COMMAND);
        }
    }
    //@@author

    private static Command prepareAddIngredient(String args) {
        final ArgParser argParser = new ArgParser(PREFIX_INGREDIENT, PREFIX_QUANTITY, PREFIX_EXPIRY);
        try {
            argParser.parse(args);
        } catch (InvalidPrefixException | MissingPrefixException e) {
            return new IncorrectCommand("Wrong format for add command");
        }

        try {
            final String ingredient = new IngredientParser().parse(argParser.getSingleArg(PREFIX_INGREDIENT));
            final int quantity = new QuantityParser().parse(argParser.getSingleArg(PREFIX_QUANTITY));
            final LocalDate expiry = new ExpiryDateParser().parse(argParser.getSingleArg(PREFIX_EXPIRY));
            return new AddIngredientCommand(ingredient, quantity, expiry);
        } catch (InvalidArgFormatException e) {
            return new IncorrectCommand(e.getMessage());
        } catch (MissingPrefixException | MultipleArgsException e) {
            return new IncorrectCommand("Wrong format for add command");
        }
    }

    private static Command prepareDecrementIngredient(String args) {
        final ArgParser argParser = new ArgParser(PREFIX_INGREDIENT, PREFIX_QUANTITY);
        try {
            argParser.parse(args);
        } catch (InvalidPrefixException | MissingPrefixException e) {
            return new IncorrectCommand("Wrong format for decrement command");
        }

        try {
            final String ingredient = new IngredientParser().parse(argParser.getSingleArg(PREFIX_INGREDIENT));
            final int quantity = new QuantityParser().parse(argParser.getSingleArg(PREFIX_QUANTITY));
            return new DecrementCommand(ingredient, quantity);
        } catch (InvalidArgFormatException e) {
            return new IncorrectCommand(e.getMessage());
        } catch (MissingPrefixException | MultipleArgsException e) {
            return new IncorrectCommand("Wrong format for decrement command");
        }
    }

    private static Command prepareIncrementIngredient(String args) {
        final ArgParser argParser = new ArgParser(PREFIX_INGREDIENT, PREFIX_QUANTITY);
        try {
            argParser.parse(args);
        } catch (InvalidPrefixException | MissingPrefixException e) {
            return new IncorrectCommand("Wrong format for increment command");
        }

        try {
            final String ingredient = new IngredientParser().parse(argParser.getSingleArg(PREFIX_INGREDIENT));
            final int quantity = new QuantityParser().parse(argParser.getSingleArg(PREFIX_QUANTITY));
            return new IncrementCommand(ingredient, quantity);
        } catch (InvalidArgFormatException e) {
            return new IncorrectCommand(e.getMessage());
        } catch (MissingPrefixException | MultipleArgsException e) {
            return new IncorrectCommand("Wrong format for increment command");
        }
    }

    private static Command prepareDeleteIngredient(String args) {
        final ArgParser argParser = new ArgParser(PREFIX_INGREDIENT);
        try {
            argParser.parse(args);
        } catch (InvalidPrefixException | MissingPrefixException e) {
            return new IncorrectCommand("Wrong format for delete command");
        }

        try {
            final String ingredient = new IngredientParser().parse(argParser.getSingleArg(PREFIX_INGREDIENT));
            return new DeleteIngredientCommand(ingredient);
        } catch (InvalidArgFormatException e) {
            return new IncorrectCommand(e.getMessage());
        } catch (MissingPrefixException | MultipleArgsException e) {
            return new IncorrectCommand("Wrong format for delete command");
        }
    }

    /**
     * Creates a AddRecipeCommand from the inputs.
     *
     * @param args Command arguments.
     * @return a AddRecipeCommand with the recipe name and the ingredients if successful
     *     and an IncorrectCommand if not.
     */
    private static Command prepareAddRecipe(String args) {
        final ArgParser argParser = new ArgParser(PREFIX_RECIPE, PREFIX_INGREDIENT, PREFIX_QUANTITY);
        try {
            argParser.parse(args);
        } catch (InvalidPrefixException | MissingPrefixException e) {
            return new IncorrectCommand("Wrong format for add recipe command");
        }

        try {
            final String recipe = new RecipeParser().parse(argParser.getSingleArg(PREFIX_RECIPE));
            final ArrayList<String> ingredients = new IngredientParser().parse(argParser.getArgList(PREFIX_INGREDIENT));
            final ArrayList<Integer> quantities = new QuantityParser().parse(argParser.getArgList(PREFIX_QUANTITY));
            final IngredientList ingredientList = new IngredientList();
            final IncorrectCommand error = addIngredients(ingredients, quantities, ingredientList);
            if (error != null) {
                return error;
            }
            assert !recipe.isBlank();
            return new AddRecipeCommand(recipe, ingredientList);
        } catch (InvalidArgFormatException e) {
            return new IncorrectCommand(e.getMessage());
        } catch (MissingPrefixException | MultipleArgsException e) {
            return new IncorrectCommand("Wrong format for add recipe command");
        }
    }

    /**
     * Creates a DeleteRecipeCommand from the inputs.
     *
     * @param args Command arguments.
     * @return a DeleteRecipeCommand with the recipe name if successful and an IncorrectCommand if not.
     */
    private static Command prepareDeleteRecipe(String args) {
        final ArgParser argParser = new ArgParser(PREFIX_RECIPE);
        try {
            argParser.parse(args);
        } catch (InvalidPrefixException | MissingPrefixException e) {
            return new IncorrectCommand("Wrong format for delete recipe command");
        }

        try {
            final String recipe = new RecipeParser().parse(argParser.getSingleArg(PREFIX_RECIPE));
            assert !recipe.isBlank();
            return new DeleteRecipeCommand(recipe);
        } catch (InvalidArgFormatException e) {
            return new IncorrectCommand(e.getMessage());
        } catch (MissingPrefixException | MultipleArgsException e) {
            return new IncorrectCommand("Wrong format for delete recipe command");
        }
    }

    /**
     * Adds the ingredients into the ingredient list.
     *
     * @param ingredientNames Array of name of ingredients
     * @param quantities      Array of quantity of ingredients
     * @param ingredients     Ingredient list to store the ingredients
     * @return null if there's no error and an IncorrectCommand if there is.
     */
    private static IncorrectCommand addIngredients(ArrayList<String> ingredientNames, ArrayList<Integer> quantities,
                                                   IngredientList ingredients) {
        if (ingredientNames.size() != quantities.size()) {
            return new IncorrectCommand("Should have same number of ingredient names and quantities");
        }
        for (int i = 0; i < ingredientNames.size(); i++) {
            if (quantities.get(i) == 0) {
                return new IncorrectCommand("Quantity of ingredients for recipe cannot be zero.");
            }
            Ingredient ingredient = new Ingredient(ingredientNames.get(i), quantities.get(i), null);
            assert !ingredientNames.get(i).isBlank();
            assert quantities.get(i) != null && quantities.get(i) != 0;
            try {
                ingredients.add(ingredient);
            } catch (DuplicateDataException e) {
                return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT);
            }
        }
        return null;
    }

    private static Command prepareViewIngredient(String args) {
        final ArgParser argParser = new ArgParser(PREFIX_INGREDIENT);
        try {
            argParser.parse(args);
        } catch (InvalidPrefixException | MissingPrefixException e) {
            return new IncorrectCommand("Wrong format for view command");
        }

        try {
            final String ingredient = new IngredientParser().parse(argParser.getSingleArg(PREFIX_INGREDIENT));
            return new ViewIngredientCommand(ingredient);
        } catch (InvalidArgFormatException e) {
            return new IncorrectCommand(e.getMessage());
        } catch (MissingPrefixException | MultipleArgsException e) {
            return new IncorrectCommand("Wrong format for view command");
        }
    }

    private static Command prepareViewRecipe(String args) {
        final ArgParser argParser = new ArgParser(PREFIX_RECIPE);
        try {
            argParser.parse(args);
        } catch (InvalidPrefixException | MissingPrefixException e) {
            return new IncorrectCommand("Wrong format for view recipe command");
        }

        try {
            final String recipe = new RecipeParser().parse(argParser.getSingleArg(PREFIX_RECIPE));
            return new ViewRecipeCommand(recipe);
        } catch (InvalidArgFormatException e) {
            return new IncorrectCommand(e.getMessage());
        } catch (MissingPrefixException | MultipleArgsException e) {
            return new IncorrectCommand("Wrong format for view recipe command");
        }
    }
}
