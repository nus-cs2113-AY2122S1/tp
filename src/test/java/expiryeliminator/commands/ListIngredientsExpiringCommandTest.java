package expiryeliminator.commands;

//@@author JoshHDs

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import expiryeliminator.data.IngredientRepository;
import expiryeliminator.util.TestUtil;

public class ListIngredientsExpiringCommandTest {

    @Test
    public void listIngredientsExpiringCommand_sampleIngredientRepository_expectExpiringIngredientString() {
        IngredientRepository ingredientRepository = TestUtil.generateIngredientRepositoryWithSomeExpiredIngredients();
        assert ingredientRepository != null;
        Command command = new ListIngredientExpiringCommand();
        String message = String.format(ListIngredientExpiringCommand.MESSAGE_SHOW_WHOLE_LIST,
                ingredientRepository.findExpiringIngredients());

        assertEquals(command.execute(ingredientRepository, null), message);
    }


}
