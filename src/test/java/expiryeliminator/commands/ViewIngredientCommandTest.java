package expiryeliminator.commands;

//@@author JoshHDs

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import expiryeliminator.data.IngredientRepository;
import expiryeliminator.data.IngredientStorage;
import expiryeliminator.data.exception.NotFoundException;
import expiryeliminator.util.TestUtil;

public class ViewIngredientCommandTest {

    @Test
    public void viewIngredientCommand_sampleIngredientRepository_expectRedAppleString() {
        IngredientRepository ingredientRepository = TestUtil.generateIngredientRepositoryWithSomeExpiredIngredients();
        assert ingredientRepository != null;
        Command command = new ViewIngredientCommand("Red Apple");

        IngredientStorage ingredientStorage = null;
        try {
            ingredientStorage = ingredientRepository.find("Red Apple");
        } catch (NotFoundException e) {
            fail("Ingredient should exist by definition");
        }
        String message = String.format(ViewIngredientCommand.MESSAGE_SHOW_INGREDIENT, ingredientStorage);

        assertEquals(command.execute(ingredientRepository, null), message);
    }
}
