package seedu.foodorama.command;

import org.junit.jupiter.api.Test;
import seedu.foodorama.DishList;
import seedu.foodorama.IngredientList;
import seedu.foodorama.exceptions.FoodoramaException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FindCommandTest {

    @Test
    void execute() throws FoodoramaException {
        ArrayList<String> inputParamsDish = new ArrayList<>();
        inputParamsDish.add("chicken rice");
        Command testAddDishCommand = new AddDishCommand();
        testAddDishCommand.execute(inputParamsDish);
        inputParamsDish.set(0, "fried rice");
        testAddDishCommand.execute(inputParamsDish);

        assertEquals(2, DishList.dishList.size());

        inputParamsDish.set(0, "dish");
        inputParamsDish.add(1, "rice");
        Command testFindDishCommand = new FindCommand();
        ByteArrayOutputStream dishOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(dishOutput));
        testFindDishCommand.execute(inputParamsDish);
        String actualOutDish = dishOutput.toString().trim();

        assertTrue(actualOutDish.contains("chicken rice") && actualOutDish.contains("fried rice"));

        ArrayList<String> inputParamsIngr = new ArrayList<>();
        inputParamsIngr.add("chicken");
        AddIngrCommand myIngrCommand = new AddIngrCommand();
        InputStream backupInputStream = System.in;
        ByteArrayInputStream fakeFirstInput = new ByteArrayInputStream("2.33".getBytes());
        System.setIn(fakeFirstInput);
        myIngrCommand.execute(inputParamsIngr);
        System.setIn(backupInputStream);
        inputParamsIngr.set(0, "fragrant rice");
        ByteArrayInputStream fakeSecondInput = new ByteArrayInputStream("5.0".getBytes());
        System.setIn(fakeSecondInput);
        myIngrCommand.execute(inputParamsIngr);
        System.setIn(backupInputStream);

        assertEquals(2, IngredientList.ingredientList.size());

        inputParamsIngr.set(0, "ingr");
        inputParamsIngr.add(1, "rice");
        Command testFindIngrCommand = new FindCommand();
        ByteArrayOutputStream ingrOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(ingrOutput));
        testFindIngrCommand.execute(inputParamsIngr);
        String actualOutIngr = ingrOutput.toString().trim();
        assertTrue(actualOutIngr.contains("fragrant rice"));

        DishList.dishList.clear();
        IngredientList.ingredientList.clear();
    }
}