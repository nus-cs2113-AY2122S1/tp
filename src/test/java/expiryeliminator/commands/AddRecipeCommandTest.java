package expiryeliminator.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import expiryeliminator.data.IngredientRepository;
import expiryeliminator.data.RecipeList;
import expiryeliminator.util.TestUtil;

class AddRecipeCommandTest {
    @Test
    public void addRecipeCommand_duplicateRecipeName_recipeAlreadyExistsError() {
        final IngredientRepository ingredientRepository = TestUtil.generateIngredientRepositoryForRecipe();
        RecipeList recipes = TestUtil.generateRecipeList();
        Command command = new AddRecipeCommand(TestUtil.EXAMPLE_RECIPE_NAME,
                TestUtil.generateIngredientNamesForRecipe(), TestUtil.generateQuantitiesForRecipe());
        String errorMessage = String.format(AddRecipeCommand.MESSAGE_RECIPE_ALREADY_EXISTS,
                TestUtil.EXAMPLE_RECIPE_NAME);
        assertEquals(command.execute(ingredientRepository, recipes), errorMessage);
    }

    @Test
    public void addRecipeCommand_correctInput_recipeAddedSuccessfully() {
        final IngredientRepository ingredientRepository = TestUtil.generateIngredientRepositoryForRecipe();
        RecipeList recipes = new RecipeList();
        Command command = new AddRecipeCommand(TestUtil.EXAMPLE_RECIPE_NAME,
                TestUtil.generateIngredientNamesForRecipe(), TestUtil.generateQuantitiesForRecipe());
        String successMessage = String.format(AddRecipeCommand.MESSAGE_RECIPE_ADDED, TestUtil.generateRecipe(), 1);
        assertEquals(command.execute(ingredientRepository, recipes), successMessage);
    }
}
