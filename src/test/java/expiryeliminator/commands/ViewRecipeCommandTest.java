package expiryeliminator.commands;

import org.junit.jupiter.api.Test;
import expiryeliminator.util.TestUtil;
import expiryeliminator.data.RecipeList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ViewRecipeCommandTest {

    @Test
    public void viewRecipeCommand_noMatchingRecipeName() {
        RecipeList recipes = TestUtil.generateRecipeListWithSingleRecipe();
        Command command = new ViewRecipeCommand(TestUtil.RANDOM_INPUT_RECIPE_NAME);
        String errorMessage = ViewRecipeCommand.MESSAGE_RECIPE_NOT_FOUND;
        assertEquals(command.execute(null, recipes), errorMessage);
    }

    @Test
    public void viewRecipeCommand_hasMatchingRecipeName() {
        RecipeList recipes = TestUtil.generateRecipeListWithSingleRecipe();
        Command command = new ViewRecipeCommand(TestUtil.EXAMPLE_RECIPE_NAME);
        String confirmMessage = String.format(ViewRecipeCommand.MESSAGE_SHOW_RECIPE, TestUtil.generateChickenRecipe());
        assertEquals(command.execute(null, recipes), confirmMessage);
    }
}
