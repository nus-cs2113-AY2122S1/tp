package seedu.foodorama.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.foodorama.Dish;
import seedu.foodorama.DishList;
import seedu.foodorama.Ingredient;
import seedu.foodorama.IngredientList;
import seedu.foodorama.Ui;
import seedu.foodorama.exceptions.FoodoramaException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class ListCommandTest {
    PrintStream originalOutputStream;
    ByteArrayOutputStream newOutputStream;
    Ui ui;

    @BeforeEach
    void setupStreams() {
        ui = new Ui();
        originalOutputStream = System.out;
        newOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newOutputStream));
    }

    // methodBeingTested_inputGiven_expectedOutcome
    // Input, one parameter, print.
    @Test
    void execute_arrayList_print() throws FoodoramaException {
        ArrayList<String> inputArrayList = new ArrayList<>();
        ListCommand list = new ListCommand();
        //Dish
        try {
            inputArrayList.add("dish");
            Dish dishToAdd = new Dish("noodles");
            DishList.dishList.add(dishToAdd);
            Dish dishToAdd2 = new Dish("mee siam");
            DishList.dishList.add(dishToAdd2);
            list.execute(inputArrayList);
            assertTrue(newOutputStream.toString().trim().contains(dishToAdd.toString()));
            assertTrue(newOutputStream.toString().trim().contains(dishToAdd2.toString()));
            DishList.dishList.clear();
            list.execute(inputArrayList);
        } catch (FoodoramaException e) {
            assertEquals("List is empty, nothing to show", e.getMessage());
        }

        //Ingr
        try {
            inputArrayList.set(0,"ingr");
            Ingredient ingredientToAdd = new Ingredient("chocolate", 3.33);
            IngredientList.ingredientList.add(ingredientToAdd);
            Ingredient ingredientToAdd2 = new Ingredient("rice", 3.42);
            IngredientList.ingredientList.add(ingredientToAdd2);
            list.execute(inputArrayList);
            assertTrue(newOutputStream.toString().trim().contains(ingredientToAdd.toString()));
            assertTrue(newOutputStream.toString().trim().contains(ingredientToAdd2.toString()));
            IngredientList.ingredientList.clear();
            list.execute(inputArrayList);
        } catch (FoodoramaException e) {
            assertEquals("List is empty, nothing to show", e.getMessage());
        }

        try {
            inputArrayList.add("notaCommand");
            list.execute(inputArrayList);
        } catch (FoodoramaException e) {
            assertEquals(ui.getListMissingParamMsg(), e.getMessage());
        }
        DishList.dishList.clear();
        IngredientList.ingredientList.clear();
    }

    @AfterEach
    void reset() {
        DishList.dishList.clear();
        IngredientList.ingredientList.clear();
        System.setOut(originalOutputStream);
    }
}