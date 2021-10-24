package seedu.foodorama.command;

import org.junit.jupiter.api.Test;
import seedu.foodorama.DishList;
import seedu.foodorama.Ingredient;
import seedu.foodorama.IngredientList;
import seedu.foodorama.exceptions.FoodoramaException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SortIngrCommandTest {

    @Test
    void execute() throws FoodoramaException {
        //define inputs

        ByteArrayInputStream fakeInput;
        Ingredient ingredientToAdd = new Ingredient("egg", 1.55);
        fakeInput = new ByteArrayInputStream("1.25".getBytes());
        System.setIn(fakeInput);
        ingredientToAdd.addWaste();
        IngredientList.ingredientList.add(ingredientToAdd);
        Ingredient ingredientToAdd2 = new Ingredient("chicken", 5.55);
        fakeInput = new ByteArrayInputStream("5.25".getBytes());
        System.setIn(fakeInput);
        ingredientToAdd2.addWaste();
        IngredientList.ingredientList.add(ingredientToAdd2);
        Ingredient ingredientToAdd3 = new Ingredient("bitter gourd", 3.55);
        fakeInput = new ByteArrayInputStream("3.25".getBytes());
        System.setIn(fakeInput);
        ingredientToAdd3.addWaste();
        IngredientList.ingredientList.add(ingredientToAdd3);

        //storing the output
        ByteArrayOutputStream sortIngrOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(sortIngrOutput));
        String actualOutSortDish = sortIngrOutput.toString().trim();

        //run command
        SortIngrCommand testCommand = new SortIngrCommand();
        ArrayList<String> testInputs = new ArrayList<>();
        testCommand.execute(testInputs);

        //checking
        assertEquals("chicken", IngredientList.ingredientList.get(0).getIngredientName());
        assertEquals("egg", IngredientList.ingredientList.get(2).getIngredientName());

        //clearing IngredientList
        DishList.dishList.clear();
        IngredientList.ingredientList.clear();
    }
}