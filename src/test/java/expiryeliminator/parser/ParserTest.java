package expiryeliminator.parser;

import static expiryeliminator.parser.Parser.parseCommand;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import expiryeliminator.commands.AddRecipeCommand;
import expiryeliminator.commands.DeleteRecipeCommand;
import expiryeliminator.data.IngredientRepository;
import expiryeliminator.data.RecipeList;
import expiryeliminator.parser.argparser.IngredientParser;
import expiryeliminator.parser.argparser.QuantityParser;
import expiryeliminator.parser.argparser.RecipeParser;
import expiryeliminator.util.TestUtil;

class ParserTest {
    @Test
    public void prepareAddRecipe_incorrectFormats_ErrorMessage() {
        String[] tests = {"add recipe r/chicken soup",
                          "add recipe r/Apple Pie",
                          "add recipe i/Red Apple q/4 i/Green Apple q/4"};

        for (String test : tests) {
            assertEquals(parseCommand(test).execute(null, null),
                    String.format(Parser.MESSAGE_INVALID_COMMAND_FORMAT, AddRecipeCommand.MESSAGE_USAGE));
        }
    }

    @Test
    public void prepareAddRecipe_quantityBlank_ErrorMessage() {
        String test = "add recipe r/chicken soup i/chicken q/";
        assertEquals(String.format(Parser.MESSAGE_INVALID_ARGUMENT_FORMAT, QuantityParser.MESSAGE_BLANK_QUANTITY),
                parseCommand(test).execute(null, null));
    }

    @Test
    public void prepareAddRecipe_ingredientBlank_ErrorMessage() {
        String test = "add recipe r/chicken soup i/ q/1";
        assertEquals(String.format(Parser.MESSAGE_INVALID_ARGUMENT_FORMAT, IngredientParser.MESSAGE_BLANK_INGREDIENT),
                parseCommand(test).execute(null, null));
    }

    @Test
    public void prepareAddRecipe_quantityNotANumber_ErrorMessage() {
        String test = "add recipe r/Apple Pie i/Red Apple q/4 i/Green Apple q/four";
        assertEquals(String.format(Parser.MESSAGE_INVALID_ARGUMENT_FORMAT, "Quantity must be a valid number."),
                parseCommand(test).execute(null, null));
    }

    @Test
    public void prepareAddRecipe_missingQuantityOrIngredient_ErrorMessage() {
        String[] tests = {"add recipe r/chicken soup i/chicken q/1 i/salt",
                          "add recipe r/chicken soup i/chicken q/1 q/20",
                          "add recipe r/Apple Pie i/Red Apple q/4 i/Green Apple",
                          "add recipe r/Apple Pie i/Red Apple q/4 q/4"};

        for (String test : tests) {
            assertEquals("Should have same number of ingredient names and quantities",
                    parseCommand(test).execute(null, null));
        }
    }

    @Test
    public void prepareAddRecipe_correctInput_AddRecipeCommand() {
        String test = "add recipe r/Chicken Soup i/chicken q/1 i/salt q/20";
        assertTrue(parseCommand(test) instanceof AddRecipeCommand);
    }

    @Test
    public void prepareAddRecipe_ingredientWithZeroQuantity_ErrorMessage() {
        final IngredientRepository ingredientRepository = TestUtil.generateIngredientRepositoryForRecipe();
        final RecipeList recipeList = TestUtil.generateEmptyRecipeList();
        String test = "add recipe r/Chicken Soup i/chicken q/0 i/salt q/20";
        assertEquals("Quantity of ingredients for recipe cannot be zero.",
                parseCommand(test).execute(ingredientRepository, recipeList));
    }

    @Test
    public void prepareDeleteRecipe_correctInput_DeleteRecipeCommand() {
        String test = "delete recipe r/Chicken Soup";
        assertTrue(parseCommand(test) instanceof DeleteRecipeCommand);
    }

    @Test
    public void prepareDeleteRecipe_incorrectFormat_ErrorMessage() {
        String test = "delete recipe r/";
        assertEquals(String.format(Parser.MESSAGE_INVALID_ARGUMENT_FORMAT, RecipeParser.MESSAGE_BLANK_RECIPE),
                parseCommand(test).execute(null, null));
    }
}
