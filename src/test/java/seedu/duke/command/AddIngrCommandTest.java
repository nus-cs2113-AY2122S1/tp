package seedu.duke.command;

import org.junit.jupiter.api.Test;

import seedu.duke.DishList;
import seedu.duke.IngredientList;
import seedu.duke.exceptions.FoodoramaException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddIngrCommandTest {

    @Test
    void execute_ingredientParams_add() throws FoodoramaException {

        ArrayList<String> inputParams = new ArrayList<>();
        inputParams.add("chicken");
        AddIngrCommand myIngrCommand = new AddIngrCommand();
        InputStream backupInputStream = System.in;
        ByteArrayInputStream fakeInput = new ByteArrayInputStream("2.33".getBytes());
        System.setIn(fakeInput);
        myIngrCommand.execute(inputParams);
        System.setIn(backupInputStream);
        assertEquals(1, IngredientList.ingredientList.size());
        DishList.clearList();
        IngredientList.clearList();
    }
}


