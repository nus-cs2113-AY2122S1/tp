package seedu.foodorama.command;

import org.junit.jupiter.api.Test;
import seedu.foodorama.DishList;
import seedu.foodorama.IngredientList;
import seedu.foodorama.exceptions.FoodoramaException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


class DeleteDishCommandTest {

    @Test
    void execute() throws FoodoramaException {
        ArrayList<String> inputParams = new ArrayList<>();
        inputParams.add("chicken rice");

        Command testAddDishCommand = new AddDishCommand();
        testAddDishCommand.execute(inputParams);

        assertEquals(1, DishList.dishList.size());

        InputStream originalInput = System.in;
        InputStream fakeInput  = new ByteArrayInputStream("y".getBytes());
        System.setIn(fakeInput);
        Command testDeleteDishCommand = new DeleteDishCommand();
        testDeleteDishCommand.execute(inputParams);
        System.setIn(originalInput);

        assertEquals(0, DishList.dishList.size());
        DishList.dishList.clear();
        IngredientList.ingredientList.clear();
    }
}