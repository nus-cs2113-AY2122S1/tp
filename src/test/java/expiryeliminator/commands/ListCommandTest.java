package expiryeliminator.commands;

//@@author JoshHDs

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import expiryeliminator.data.IngredientRepository;
import expiryeliminator.util.TestUtil;

public class ListCommandTest {


    @Test
    public void listCommand_sampleIngredientRepository_expectListString() {
        IngredientRepository ingredientRepository = TestUtil.generateIngredientRepositoryWithSomeExpiredIngredients();
        assert ingredientRepository != null;
        Command command = new ListCommand();
        String message = String.format(ListCommand.MESSAGE_SHOW_WHOLE_LIST, ingredientRepository.printWholeList(), 3);
        assertEquals(command.execute(ingredientRepository, null), message);
    }

    @Test
    public void listCommand_emptyIngredientRepository_emptyIngredientRepositoryMessage() {
        IngredientRepository ingredientRepository = TestUtil.generateEmptyIngredientRepository();
        Command command = new ListCommand();
        String message = ListCommand.MESSAGE_EMPTY_INGREDIENT_LIST;
        assertEquals(command.execute(ingredientRepository, null), message);
    }


}
