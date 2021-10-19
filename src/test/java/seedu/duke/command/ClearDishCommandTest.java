package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.DishList;
import seedu.duke.IngredientList;
import seedu.duke.exceptions.FoodoramaException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ClearDishCommandTest {

    @Test
    void execute() throws FoodoramaException {
        ArrayList<String> inputParams = new ArrayList<>();
        inputParams.add("chicken rice");
        Command testAddDishCommand = new AddDishCommand();
        testAddDishCommand.execute(inputParams);

        inputParams.set(0, "kway teow");
        testAddDishCommand.execute(inputParams);

        assertEquals(2, DishList.dishList.size());

        Command testClearDishCommand = new ClearDishCommand();
        testClearDishCommand.execute(inputParams);
        assertEquals(0, DishList.dishList.size());
        DishList.clearList();
        IngredientList.clearList();
    }
}