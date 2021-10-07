package seedu.duke.ingredients;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.DukeException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientsListTest {
    @Test
    public void getIngredientInfoTest_invalidIngredientNumber_dukeExceptionThrown() {
        try {
            int ingredientNumber = -1;
            IngredientList.getInstance().getIngredientInfo(ingredientNumber);
        } catch (DukeException e) {
            assertEquals("Ingredient number have not existed!", e.getMessage());
        }
    }

    @Test
    public void getInstanceTest_noInputPattern_theSameInstancesReturned() {
        IngredientList list1 = IngredientList.getInstance();
        IngredientList list2 = IngredientList.getInstance();

        assertEquals(list1, list2);
    }
}
