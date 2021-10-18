package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.DukeException;
import seedu.duke.ingredients.Ingredient;
import seedu.duke.ingredients.IngredientList;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UpdateCommandTest {

    /*@Test
    public void updateCommandTest_dukeExceptionThrown() throws DukeException, IOException {
        Ingredient ingredient1 = new Ingredient("Carrot", 300, "grams", "21st Oct");
        Ingredient ingredient2 = new Ingredient("Tomato", 200, "ounces", "1st Nov");
        Ingredient ingredient3 = new Ingredient("Carrot", 1.5, "kilograms", "2nd Oct");

        IngredientList.getInstance().add(ingredient1);
        IngredientList.getInstance().add(ingredient2);
        IngredientList.getInstance().add(ingredient3);

        Ingredient updatedIngredient = new Ingredient("Carrot", 450.0, "grams", "4th Nov");
        String resultMsg = new UpdateCommand(updatedIngredient).run();
        String expected = "Got it. This ingredient has been updated:\n" + "\t" + updatedIngredient.toString();

        assertEquals(expected, resultMsg);
    }*/
}