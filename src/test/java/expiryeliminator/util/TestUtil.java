package expiryeliminator.util;

import expiryeliminator.data.Ingredient;
import expiryeliminator.data.IngredientList;
import expiryeliminator.data.Recipe;
import expiryeliminator.data.RecipeList;
import expiryeliminator.data.exception.DuplicateDataException;

import static org.junit.jupiter.api.Assertions.fail;

public class TestUtil {

    public static final String EXAMPLE_RECIPE_NAME = "Chicken Soup";

    public static final String RANDOM_INPUT_RECIPE_NAME = "Foo";

    public static IngredientList generateIngredientListForRecipe() {
        Ingredient firstIngredient = new Ingredient("Chicken", 1,null);
        Ingredient secondIngredient = new Ingredient("Salt", 20,null);
        IngredientList ingredientsForRecipe = new IngredientList();
        try {
            ingredientsForRecipe.add(firstIngredient);
            ingredientsForRecipe.add(secondIngredient);
            return ingredientsForRecipe;
        } catch (DuplicateDataException e) {
            fail("Recipe should be valid by definition");
            return null;
        }
    }

    public static Recipe generateRecipe() {
        return new Recipe("Chicken Soup", generateIngredientListForRecipe());
    }

    public static RecipeList generateRecipeList() {
        try {
            Recipe recipe = generateRecipe();
            RecipeList recipes = new RecipeList();
            recipes.add(recipe);
            return recipes;
        } catch (DuplicateDataException e) {
            fail("Recipe should be valid by definition");
            return null;
        }
    }
}
