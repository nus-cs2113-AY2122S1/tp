package expiryeliminator.commands;

import org.junit.jupiter.api.Test;
import expiryeliminator.util.TestUtil;
import expiryeliminator.data.RecipeList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListRecipeCommandTest {

    @Test
    public void listRecipeCommand_sampleRecipeList_expectLIstString() {
        RecipeList recipeList = TestUtil.generateRecipeListWithSingleRecipe();
        assert recipeList != null;
        Command command = new ListRecipeCommand();
        String message = String.format(ListRecipeCommand.MESSAGE_RECIPE_LIST, recipeList.getWholeRecipeList(), 1);
        assertEquals(command.execute(null, recipeList), message);
    }
}
