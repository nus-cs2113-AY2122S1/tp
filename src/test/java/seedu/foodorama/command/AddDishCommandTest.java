package seedu.foodorama.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import seedu.foodorama.DishList;
import seedu.foodorama.IngredientList;
import seedu.foodorama.exceptions.FoodoramaException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddDishCommandTest {

    @Test
    void execute_inputParameters_add() throws FoodoramaException {
        //Define inputs
        ArrayList<String> inputParams = new ArrayList<>();
        inputParams.add("chicken rice");


        Command testAddDishCommand = new AddDishCommand();
        testAddDishCommand.execute(inputParams);

        assertEquals("chicken rice", DishList.dishList.get(0).getDishName());
        DishList.dishList.clear();
        IngredientList.ingredientList.clear();
    }

    @AfterEach
    void clearLists() {
        DishList.dishList.clear();
        IngredientList.ingredientList.clear();
    }
}
