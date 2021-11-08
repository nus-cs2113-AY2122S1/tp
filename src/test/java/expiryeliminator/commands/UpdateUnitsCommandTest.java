package expiryeliminator.commands;

//@@author JoshHDs

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import expiryeliminator.data.IngredientRepository;
import expiryeliminator.data.RecipeList;
import expiryeliminator.util.TestUtil;

public class UpdateUnitsCommandTest {

    @Test
    public void updateUnitsCommandTest_inputIngredientNameAndNewUNits_unitsOfIngredientRepoIsUpdated() {
        //generate ingredient repo
        IngredientRepository ingredientRepository = TestUtil.generateIngredientRepositoryForRecipe();
        assert ingredientRepository != null;
        //generate recipe list

        RecipeList recipes = TestUtil.generateRecipeListWithSingleRecipe();
        //update units
        Command command = new UpdateUnitsCommand("Salt","kilograms");
        String output = command.execute(ingredientRepository, recipes);
        //check it updated
        String newUnit = TestUtil.getUpdatedUnitsForIngredientRepo(ingredientRepository);

        assertEquals(newUnit, "kilograms");
    }

    @Test
    public void updateUnitsCommandTest_inputIngredientNameAndNewUNits_unitsOfRecipeListIsUpdated() {
        //generate ingredient repo
        IngredientRepository ingredientRepository = TestUtil.generateIngredientRepositoryForRecipe();
        assert ingredientRepository != null;
        //generate recipe list
        RecipeList recipes = TestUtil.generateRecipeListWithSingleRecipe();
        //update units
        Command command = new UpdateUnitsCommand("Salt","kilograms");
        String output = command.execute(ingredientRepository, recipes);
        //check it updated
        String newUnit = TestUtil.getUpdatedUnitsForRecipeList(recipes);
        assertEquals(newUnit, "kilograms");
    }




}
