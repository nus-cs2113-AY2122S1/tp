package seedu.foodorama.command;

import org.junit.jupiter.api.Test;
import seedu.foodorama.Dish;
import seedu.foodorama.DishList;
import seedu.foodorama.IngredientList;
import seedu.foodorama.exceptions.FoodoramaException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SortDishCommandTest {

    @Test
    void execute() throws FoodoramaException {

        //define inputs
        ByteArrayInputStream fakeInput;
        Dish dishToAdd = new Dish("hamburger");
        fakeInput = new ByteArrayInputStream("3.00".getBytes());
        System.setIn(fakeInput);
        dishToAdd.addWaste();
        DishList.dishList.add(dishToAdd);
        Dish dishToAdd2 = new Dish("krabby patty");
        fakeInput = new ByteArrayInputStream("5.55".getBytes());
        System.setIn(fakeInput);
        dishToAdd2.addWaste();
        DishList.dishList.add(dishToAdd2);
        Dish dishToAdd3 = new Dish("samurai burger");
        fakeInput = new ByteArrayInputStream("4.55".getBytes());
        System.setIn(fakeInput);
        dishToAdd3.addWaste();
        DishList.dishList.add(dishToAdd3);

        //storing the output
        ByteArrayOutputStream sortDishOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(sortDishOutput));
        String actualOutSortDish = sortDishOutput.toString().trim();

        //run command
        SortDishCommand testCommand = new SortDishCommand();
        ArrayList<String> testInputs = new ArrayList<>();
        testCommand.execute(testInputs);

        //checking
        assertEquals("krabby patty", DishList.dishList.get(0).getDishName());
        assertEquals("hamburger", DishList.dishList.get(2).getDishName());

        //clearing dishList
        DishList.dishList.clear();
        IngredientList.ingredientList.clear();

    }
}
