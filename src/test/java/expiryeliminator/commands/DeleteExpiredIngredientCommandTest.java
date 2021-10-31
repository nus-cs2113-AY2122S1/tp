package expiryeliminator.commands;

//@@author JoshHDs

import expiryeliminator.data.IngredientRepository;
import org.junit.jupiter.api.Test;
import expiryeliminator.util.TestUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteExpiredIngredientCommandTest {

    @Test
    public void deleteExpiredIngredientCommand_ingredientRepoWithExpiredIngredients_deletedExpiredIngredientsMessage() {
        //generate repository with some expired ingredients
        IngredientRepository ingredientRepository = TestUtil.generateIngredientRepositoryWithSomeExpiredIngredients();
        assert ingredientRepository != null;

        Command command = new DeleteExpiredIngredientCommand();

        assertEquals(command.execute(ingredientRepository, null),
                DeleteExpiredIngredientCommand.MESSAGE_DELETED_ALL_EXPIRED);
    }

    @Test
    public void deleteExpiredIngredientCommand_ingredientRepoWithoutExpiredIngredients_noExpiredIngredientsMessage() {
        //generate repository without expired ingredients
        IngredientRepository ingredientRepository = TestUtil.generateIngredientRepositoryWithoutExpiredIngredients();
        assert ingredientRepository != null;

        Command command = new DeleteExpiredIngredientCommand();

        assertEquals(command.execute(ingredientRepository, null),
                DeleteExpiredIngredientCommand.MESSAGE_NO_EXPIRED_INGREDIENTS);
    }

}
