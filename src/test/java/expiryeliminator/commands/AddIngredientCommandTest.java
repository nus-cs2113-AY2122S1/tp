package expiryeliminator.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import expiryeliminator.data.IngredientRepository;
import expiryeliminator.data.RecipeList;
import expiryeliminator.util.TestUtil;

public class AddIngredientCommandTest {
    @Test
    public void addIngredientCommand_noUnits_ingredientAddedSuccessfully() {
        final IngredientRepository ingredientRepository = new IngredientRepository();
        RecipeList recipes = new RecipeList();
        Command command = new AddIngredientCommand("Red Apple", null);
        command.execute(ingredientRepository, recipes);
        assertEquals(1, ingredientRepository.size());
    }

    @Test
    public void addIngredientCommand_withUnits_ingredientAddedSuccessfully() {
        final IngredientRepository ingredientRepository = new IngredientRepository();
        RecipeList recipes = new RecipeList();
        Command command = new AddIngredientCommand("Red Apple", "kg");
        command.execute(ingredientRepository, recipes);
        assertEquals(1, ingredientRepository.size());
    }

    @Test
    public void addIngredientCommand_ingredientAlreadyExists_ingredientNotAdded() {
        final IngredientRepository ingredientRepository = TestUtil.generateIngredientRepositoryForRecipe();
        RecipeList recipes = new RecipeList();
        Command command = new AddIngredientCommand("Chicken", null);
        command.execute(ingredientRepository, recipes);
        assert ingredientRepository != null;
        assertEquals(2, ingredientRepository.size());
    }
}
