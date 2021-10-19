package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.DishList;
import seedu.duke.IngredientList;
import seedu.duke.exceptions.FoodoramaException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;


class ListCommandTest {

    // methodBeingTested_inputGiven_expectedOutcome
    // Input, one parameter, print.
    @Test
    void execute_arrayList_print() throws FoodoramaException {
        ArrayList<String> inputArrayList = new ArrayList<>();
        inputArrayList.add("notaCommand");

        ListCommand list = new ListCommand();
        try {
            list.execute(inputArrayList);
        } catch (FoodoramaException e) {
            //this is empty
        }
        assertTrue(list.isOther);
        DishList.clearList();
        IngredientList.clearList();
    }
}