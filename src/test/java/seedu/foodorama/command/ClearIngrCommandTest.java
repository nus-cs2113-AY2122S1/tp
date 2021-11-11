package seedu.foodorama.command;

import org.junit.jupiter.api.Test;
import seedu.foodorama.DishList;
import seedu.foodorama.IngredientList;
import seedu.foodorama.exceptions.FoodoramaException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ClearIngrCommandTest {

    @Test
    void execute() throws FoodoramaException {
        ArrayList<String> inputParams = new ArrayList<>();
        inputParams.add("chicken");
        AddIngrCommand myIngrCommand = new AddIngrCommand();
        InputStream backupInputStream = System.in;
        ByteArrayInputStream fakeFirstInput = new ByteArrayInputStream("2.33".getBytes());
        System.setIn(fakeFirstInput);
        myIngrCommand.execute(inputParams);
        System.setIn(backupInputStream);

        inputParams.set(0, "rice");
        ByteArrayInputStream fakeSecondInput = new ByteArrayInputStream("5.0".getBytes());
        System.setIn(fakeSecondInput);
        myIngrCommand.execute(inputParams);
        System.setIn(backupInputStream);

        assertEquals(2, IngredientList.ingredientList.size());

        InputStream originalInput = System.in;
        InputStream fakeInput  = new ByteArrayInputStream("y".getBytes());
        System.setIn(fakeInput);
        ClearIngrCommand clearIngrCommand = new ClearIngrCommand();
        clearIngrCommand.execute(inputParams);
        System.setIn(originalInput);

        assertEquals(0, IngredientList.ingredientList.size());
        DishList.dishList.clear();
        IngredientList.ingredientList.clear();
    }
}