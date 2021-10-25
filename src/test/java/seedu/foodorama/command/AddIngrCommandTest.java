package seedu.foodorama.command;

import org.junit.jupiter.api.Test;

import seedu.foodorama.DishList;
import seedu.foodorama.IngredientList;
import seedu.foodorama.exceptions.FoodoramaException;

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
        DishList.dishList.clear();
        IngredientList.ingredientList.clear();
    }
}


