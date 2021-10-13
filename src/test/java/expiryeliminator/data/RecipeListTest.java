package expiryeliminator.data;

import expiryeliminator.data.exception.DuplicateDataException;
import expiryeliminator.data.exception.NotFoundException;
import expiryeliminator.util.TestUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class RecipeListTest {
    @Test
    public void add_correctRecipeInput_correctRecipe() {
        Recipe recipe = new Recipe(TestUtil.EXAMPLE_RECIPE_NAME, TestUtil.generateIngredientListForRecipe());
        RecipeList recipes = new RecipeList();
        try {
            recipes.add(recipe);
        } catch (Exception e) {
            fail("Recipe not added.");
        }
        assertEquals(recipes.size(), 1);
    }

    @Test
    public void add_duplicateRecipeName_ExceptionThrown() {
        Recipe recipe = new Recipe(TestUtil.EXAMPLE_RECIPE_NAME, TestUtil.generateIngredientListForRecipe());
        RecipeList recipes = new RecipeList();
        try {
            recipes.add(recipe);
            Recipe duplicateRecipe = new Recipe(TestUtil.EXAMPLE_RECIPE_NAME,
                    TestUtil.generateIngredientListForRecipe());
            assertThrows(DuplicateDataException.class, () -> {
                recipes.add(duplicateRecipe);
            });
        } catch (Exception e) {
            fail("Recipe not added.");
        }
    }

    @Test
    public void remove_recipeNameNotInList_ExceptionThrown() {
        Recipe recipe = new Recipe(TestUtil.EXAMPLE_RECIPE_NAME, TestUtil.generateIngredientListForRecipe());
        RecipeList recipes = new RecipeList();
        try {
            recipes.add(recipe);
            assertThrows(NotFoundException.class, () -> {
                recipes.remove("Duck");
            });
        } catch (Exception e) {
            fail("Recipe not added.");
        }
    }

    @Test
    public void remove_recipeNameInList_recipeRemoved() {
        Recipe recipe = new Recipe(TestUtil.EXAMPLE_RECIPE_NAME, TestUtil.generateIngredientListForRecipe());
        RecipeList recipes = new RecipeList();
        try {
            recipes.add(recipe);
            recipes.remove(TestUtil.EXAMPLE_RECIPE_NAME);
            assertEquals(recipes.size(), 0);
        } catch (Exception e) {
            fail("Recipe not added.");
        }
    }
}
