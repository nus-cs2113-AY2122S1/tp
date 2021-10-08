package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.IngredientList;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeleteIngrCommandTest {

    @Test
    void execute() {
        ArrayList<String> inputParams = new ArrayList<>();
        inputParams.add("2"); //first param
        inputParams.add("chicken"); //second param

        AddIngrCommand myIngrCommand = new AddIngrCommand();
        myIngrCommand.execute(inputParams);

        assertEquals(1, IngredientList.ingredientList.size());

        inputParams.remove("2");

        DeleteIngrCommand deleteIngrCommand = new DeleteIngrCommand();
        deleteIngrCommand.execute(inputParams);

        assertEquals(0, IngredientList.ingredientList.size());
    }
}