package expiryeliminator.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import expiryeliminator.data.IngredientRepository;
import expiryeliminator.data.RecipeList;
import expiryeliminator.data.exception.NotFoundException;
import expiryeliminator.util.TestUtil;
import org.junit.jupiter.api.Test;

class CookedRecipeCommandTest {
    @Test
    public void cookedRecipeCommand_recipeNotInList_recipeNotFoundError() {
        final RecipeList recipes = TestUtil.generateRecipeListWithSingleRecipe();
        final IngredientRepository ingredients = TestUtil.generateIngredientRepositoryForRecipe();
        Command command = new CookedRecipeCommand("Duck Soup");
        assertEquals(command.execute(ingredients,recipes),ViewRecipeCommand.MESSAGE_RECIPE_NOT_FOUND);
    }

    @Test
    public void cookedRecipeCommand_correctInputAndInsufficientIngredientQuantity_insufficientQuantityError() {
        final RecipeList recipes = TestUtil.generateRecipeListWithSingleRecipe();
        final IngredientRepository ingredients = TestUtil.generateIngredientRepositoryForExampleRecipe(2,10);
        Command command = new CookedRecipeCommand(TestUtil.EXAMPLE_RECIPE_NAME);
        assertEquals(command.execute(ingredients,recipes),CookedRecipeCommand.MESSAGE_INSUFFICIENT_QUANTITY);
        try {
            assertEquals(2,ingredients.find("Chicken").getQuantity());
            assertEquals(10,ingredients.find("Salt").getQuantity());
        } catch (NotFoundException e) {
            fail("Ingredients should be in repository by definition.");
        }
    }

    @Test
    public void cookedRecipeCommand_correctInputAndSufficientIngredientQuantity_ingredientRepositoryUpdated() {
        final RecipeList recipes = TestUtil.generateRecipeListWithSingleRecipe();
        final IngredientRepository ingredients = TestUtil.generateIngredientRepositoryForExampleRecipe(2,40);
        Command command = new CookedRecipeCommand(TestUtil.EXAMPLE_RECIPE_NAME);
        String successMessage = String.format(CookedRecipeCommand.MESSAGE_RECIPE_COOKED,
                TestUtil.generateFirstIngredient() + "\n" + TestUtil.generateSecondIngredient() + "\n");
        assertEquals(successMessage,command.execute(ingredients,recipes));
        try {
            assertEquals(1, ingredients.find("Chicken").getQuantity());
            assertEquals(20, ingredients.find("Salt").getQuantity());
        } catch (NotFoundException e) {
            fail("Ingredients should be in repository by definition");
        }
    }
}
