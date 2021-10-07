package seedu.duke.command;

import org.junit.jupiter.api.Test;

import seedu.duke.IngredientList;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddIngrCommandTest {

    @Test
    void execute_ingredientParams_add() {

        ArrayList<String> inputParams = new ArrayList<>();
        inputParams.add("2"); //first param
        inputParams.add("chicken"); //second param

        AddIngrCommand myIngrCommand = new AddIngrCommand();
        myIngrCommand.execute(inputParams);

        assertEquals(1, IngredientList.ingredientList.size());
    }
}


