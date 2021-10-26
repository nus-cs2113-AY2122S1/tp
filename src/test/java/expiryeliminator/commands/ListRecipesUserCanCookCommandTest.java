package expiryeliminator.commands;

import expiryeliminator.data.IngredientRepository;
import expiryeliminator.data.RecipeList;
import expiryeliminator.util.TestUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListRecipesUserCanCookCommandTest {
    @Test
    public void listRecipesUserCanCookCommand_noRecipes_emptyRecipeListError() {
        final RecipeList recipes = TestUtil.generateEmptyRecipeList();
        final IngredientRepository ingredients = TestUtil.generateIngredientRepositoryWithoutExpiredIngredients();
        Command command = new ListRecipesUserCanCookCommand();
        assertEquals(command.execute(ingredients,recipes),ListRecipeCommand.MESSAGE_EMPTY_RECIPE_LIST);
    }

    @Test
    public void listRecipesUserCanCookCommand_notEnoughIngredients_notEnoughIngredientsError() {
        final RecipeList recipes = TestUtil.generateRecipeListWithSingleRecipe();
        final IngredientRepository ingredients = TestUtil.generateIngredientRepositoryForExampleRecipe(0,0);
        Command command = new ListRecipesUserCanCookCommand();
        assertEquals(command.execute(ingredients,recipes),
                ListRecipesUserCanCookCommand.MESSAGE_NOT_ENOUGH_INGREDIENTS);
    }

    @Test
    public void listRecipesUserCanCookCommand_enoughIngredients_listRecipeUserCanCook() {
        final RecipeList recipes = TestUtil.generateRecipeListWithSingleRecipe();
        final IngredientRepository ingredients = TestUtil.generateIngredientRepositoryForExampleRecipe(1,20);
        Command command = new ListRecipesUserCanCookCommand();
        String message = String.format(ListRecipesUserCanCookCommand.MESSAGE_RECIPES_WITH_ENOUGH_INGREDIENTS_TO_COOK,
                TestUtil.generateChickenRecipe() + "\n");
        assertEquals(command.execute(ingredients,recipes),message);
    }

    @Test
    public void listRecipesUserCanCookCommand_enoughIngredientsButSomeAreExpired_listRecipeUserCanCook() {
        final RecipeList recipes = TestUtil.generateRecipeListWithSingleRecipe();
        final IngredientRepository ingredients = TestUtil.generateIngredientRepositoryWithExpiredIngredients(1,20);
        Command command = new ListRecipesUserCanCookCommand();
        String message = String.format(ListRecipesUserCanCookCommand.MESSAGE_RECIPES_WITH_ENOUGH_INGREDIENTS_TO_COOK,
                TestUtil.generateChickenRecipe() + "\n"
                        + String.format(ListRecipesUserCanCookCommand.MESSAGE_INGREDIENTS_EXPIRED,"Chicken\nSalt\n"));
        assertEquals(command.execute(ingredients,recipes),message);
    }
}
