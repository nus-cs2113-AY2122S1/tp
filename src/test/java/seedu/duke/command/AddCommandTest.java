package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.DukeException;
import seedu.duke.ingredients.Ingredient;
import seedu.duke.ingredients.IngredientList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddCommandTest {
    @Test
    public void addCommandTest_sampleIngredient() throws DukeException {
        Ingredient sampleIngredient = new Ingredient("onion", 15.0, "grams", "10 Oct 2021");
        String resultMsg = new AddCommand(sampleIngredient).run();
        String expected = "Got it. This ingredient has been added to the inventory:\n"
                + "\t" + sampleIngredient.toString() + '\n'
                + "Current inventory has " + IngredientList.getInstance().getInventoryStock()
                + " items.";

        assertEquals(expected, resultMsg);
    }
}
