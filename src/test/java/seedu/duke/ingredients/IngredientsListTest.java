package seedu.duke.ingredients;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.DukeException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IngredientsListTest {
    @Test
    public void getIngredientInfoTest_invalidIngredientNumber_dukeExceptionThrown() {
        assertThrows(DukeException.class,
                ()->IngredientList.getInstance().getIngredientInfo(-1));
    }

    @Test
    public void getInstanceTest_noInputPattern_theSameInstancesReturned() {
        IngredientList list1 = IngredientList.getInstance();
        IngredientList list2 = IngredientList.getInstance();

        assertEquals(list1, list2);
    }
}
