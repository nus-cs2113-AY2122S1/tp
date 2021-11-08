package expiryeliminator.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import expiryeliminator.data.exception.DuplicateDataException;
import expiryeliminator.data.exception.NotFoundException;
import expiryeliminator.util.TestUtil;

public class RecipeListTest {
    @Test
    public void add_correctRecipeInput_correctRecipe() {
        Recipe recipe = TestUtil.generateChickenRecipe();
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
        Recipe recipe = TestUtil.generateChickenRecipe();
        RecipeList recipes = new RecipeList();
        try {
            recipes.add(recipe);
            Recipe duplicateRecipe = TestUtil.generateChickenRecipe();
            assertThrows(DuplicateDataException.class, () -> {
                recipes.add(duplicateRecipe);
            });
        } catch (Exception e) {
            fail("Recipe not added.");
        }
    }

    @Test
    public void remove_recipeNameNotInList_ExceptionThrown() {
        Recipe recipe = TestUtil.generateChickenRecipe();
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
        Recipe recipe = TestUtil.generateChickenRecipe();
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
