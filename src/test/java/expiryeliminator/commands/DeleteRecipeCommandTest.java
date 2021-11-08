package expiryeliminator.commands;

import expiryeliminator.util.TestUtil;
import expiryeliminator.data.RecipeList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeleteRecipeCommandTest {
    @Test
    public void deleteRecipeCommand_recipeNameNotInList_recipeNotFoundErrorMessage() {
        RecipeList recipes = TestUtil.generateRecipeListWithSingleRecipe();
        Command command = new DeleteRecipeCommand("Duck soup");
        assertEquals(command.execute(null, recipes), DeleteRecipeCommand.MESSAGE_RECIPE_NOT_FOUND);
    }

    @Test
    public void deleteRecipeCommand_recipeNameInList_recipeDeletedMessage() {
        RecipeList recipes = TestUtil.generateRecipeListWithSingleRecipe();
        Command command = new DeleteRecipeCommand(TestUtil.EXAMPLE_RECIPE_NAME);
        String message = String.format(DeleteRecipeCommand.MESSAGE_RECIPE_DELETED, TestUtil.generateChickenRecipe(), 0);
        assertEquals(command.execute(null, recipes), message);
    }
}
