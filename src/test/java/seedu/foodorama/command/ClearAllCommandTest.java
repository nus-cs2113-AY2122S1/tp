package seedu.foodorama.command;

import org.junit.jupiter.api.Test;
import seedu.foodorama.DishList;
import seedu.foodorama.IngredientList;
import seedu.foodorama.exceptions.FoodoramaException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClearAllCommandTest {

    @Test
    void execute() throws FoodoramaException {
        ArrayList<String> inputParamsDish = new ArrayList<>();
        inputParamsDish.add("chicken rice");
        Command testAddDishCommand = new AddDishCommand();
        testAddDishCommand.execute(inputParamsDish);

        inputParamsDish.set(0, "kway teow");
        testAddDishCommand.execute(inputParamsDish);

        assertEquals(2, DishList.dishList.size());

        ArrayList<String> inputParamsIngr = new ArrayList<>();
        inputParamsIngr.add("chicken");
        AddIngrCommand myIngrCommand = new AddIngrCommand();
        InputStream backupInputStream = System.in;
        ByteArrayInputStream fakeFirstInput = new ByteArrayInputStream("2.33".getBytes());
        System.setIn(fakeFirstInput);
        myIngrCommand.execute(inputParamsIngr);
        System.setIn(backupInputStream);

        inputParamsIngr.set(0, "rice");
        ByteArrayInputStream fakeSecondInput = new ByteArrayInputStream("5.0".getBytes());
        System.setIn(fakeSecondInput);
        myIngrCommand.execute(inputParamsIngr);
        System.setIn(backupInputStream);

        assertEquals(2, IngredientList.ingredientList.size());

        InputStream originalInput = System.in;
        InputStream fakeInput  = new ByteArrayInputStream("y".getBytes());
        System.setIn(fakeInput);
        ClearAllCommand clearAllCommand = new ClearAllCommand();
        clearAllCommand.execute(inputParamsDish);
        System.setIn(originalInput);

        assertEquals(0, DishList.dishList.size());
        assertEquals(0, IngredientList.ingredientList.size());
        DishList.dishList.clear();
        IngredientList.ingredientList.clear();
    }
}