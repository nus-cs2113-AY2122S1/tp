package expiryeliminator.commands;

//@@author JoshHDs

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import expiryeliminator.data.IngredientRepository;
import expiryeliminator.util.TestUtil;

public class ListIngredientsExpiredCommandTest {

    @Test
    public void listIngredientsExpiredCommand_sampleIngredientRepository_expectExpiredIngredientString() {
        IngredientRepository ingredientRepository = TestUtil.generateIngredientRepositoryWithSomeExpiredIngredients();
        assert ingredientRepository != null;
        Command command = new ListIngredientsExpiredCommand();
        String message = String.format(ListIngredientsExpiredCommand.MESSAGE_SHOW_WHOLE_LIST,
                ingredientRepository.findExpiredIngredients());

        assertEquals(command.execute(ingredientRepository, null), message);
    }
}
