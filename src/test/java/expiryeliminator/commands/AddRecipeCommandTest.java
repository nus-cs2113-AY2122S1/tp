package expiryeliminator.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import expiryeliminator.data.IngredientRepository;
import expiryeliminator.data.RecipeList;
import expiryeliminator.util.TestUtil;

class AddRecipeCommandTest {
    @Test
    public void addRecipeCommand_duplicateRecipeName_recipeAlreadyExistsError() {
        final IngredientRepository ingredientRepository = TestUtil.generateIngredientRepositoryForRecipe();
        RecipeList recipes = TestUtil.generateRecipeListWithSingleRecipe();
        Command command = new AddRecipeCommand(TestUtil.EXAMPLE_RECIPE_NAME,
                TestUtil.generateIngredientNamesForRecipe("Chicken","Salt"),
                TestUtil.generateQuantitiesForRecipe(1,20));
        String errorMessage = String.format(AddRecipeCommand.MESSAGE_RECIPE_ALREADY_EXISTS,
                TestUtil.EXAMPLE_RECIPE_NAME);
        assertEquals(command.execute(ingredientRepository, recipes), errorMessage);
    }

    @Test
    public void addRecipeCommand_duplicateIngredientName_duplicateIngredientsInRecipeError() {
        final IngredientRepository ingredientRepository = new IngredientRepository();
        RecipeList recipes = new RecipeList();
        Command command = new AddRecipeCommand(TestUtil.EXAMPLE_RECIPE_NAME,
                TestUtil.generateIngredientNamesForRecipe("Chicken","Chicken"),
                TestUtil.generateQuantitiesForRecipe(1,20));
        String errorMessage = String.format(AddRecipeCommand.MESSAGE_DUPLICATE_INGREDIENT, TestUtil.EXAMPLE_RECIPE_NAME,
                TestUtil.EXAMPLE_INGREDIENT_NAME);
        assertEquals(command.execute(ingredientRepository, recipes), errorMessage);
    }

    @Test
    public void addRecipeCommand_zeroForQuantity_IllegalValueError() {
        final IngredientRepository ingredientRepository = new IngredientRepository();
        RecipeList recipes = new RecipeList();
        Command command = new AddRecipeCommand(TestUtil.EXAMPLE_RECIPE_NAME,
                TestUtil.generateIngredientNamesForRecipe("Chicken","Salt"),
                TestUtil.generateQuantitiesForRecipe(1,0));
        String errorMessage = AddRecipeCommand.MESSAGE_ILLEGAL_VALUE_ERROR;
        assertEquals(command.execute(ingredientRepository, recipes), errorMessage);
    }

    @Test
    public void addRecipeCommand_correctInput_recipeAddedSuccessfully() {
        final IngredientRepository ingredientRepository = TestUtil.generateIngredientRepositoryForRecipe();
        RecipeList recipes = new RecipeList();
        Command command = new AddRecipeCommand(TestUtil.EXAMPLE_RECIPE_NAME,
                TestUtil.generateIngredientNamesForRecipe("Chicken","Salt"),
                TestUtil.generateQuantitiesForRecipe(1,20));
        String successMessage = String.format(AddRecipeCommand.MESSAGE_RECIPE_ADDED,
                TestUtil.generateChickenRecipe(), 1);
        assertEquals(command.execute(ingredientRepository, recipes), successMessage);
    }

    @Test
    public void addRecipeCommand_correctInputAndIngredientsNotInRepository_recipeAndIngredientsAddedSuccessfully() {
        final IngredientRepository ingredientRepository = new IngredientRepository();
        RecipeList recipes = new RecipeList();
        Command command = new AddRecipeCommand(TestUtil.EXAMPLE_RECIPE_NAME,
                TestUtil.generateIngredientNamesForRecipe("Chicken","Salt"),
                TestUtil.generateQuantitiesForRecipe(1,20));
        String successAndIngredientsAddedMessage = String.format(AddRecipeCommand.MESSAGE_RECIPE_ADDED_WITH_REMINDER,
                TestUtil.generateRecipeWithoutUnits(),1,TestUtil.INGREDIENTS_TO_UPDATE_UNITS);
        assertEquals(command.execute(ingredientRepository,recipes),successAndIngredientsAddedMessage);
        assertEquals(ingredientRepository.size(),2);
        assertNotNull(ingredientRepository.findWithNullReturn("Chicken"));
        assertNotNull(ingredientRepository.findWithNullReturn("Salt"));
    }
}
