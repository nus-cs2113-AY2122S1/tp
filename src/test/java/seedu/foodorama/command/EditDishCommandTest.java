package seedu.foodorama.command;

import org.junit.jupiter.api.Test;
import seedu.foodorama.DishList;
import seedu.foodorama.exceptions.FoodoramaException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EditDishCommandTest {

    @Test
    void execute() throws FoodoramaException {
        //inputParam as String
        ArrayList<String> inputParams = new ArrayList<>();
        inputParams.add("prata");

        Command testAddDishCommand = new AddDishCommand();
        testAddDishCommand.execute(inputParams);

        assertEquals(1, DishList.dishList.size());

        InputStream newDishName = System.in;
        InputStream fakeInput  = new ByteArrayInputStream("naan\ny".getBytes());
        System.setIn(fakeInput);

        Command testEditDishCommandParamString = new EditDishCommand();
        testEditDishCommandParamString.execute(inputParams);
        System.setIn(newDishName);

        String updatedDishName = "" + DishList.dishList.get(0).getDishName();
        assert (updatedDishName.equals("naan"));

        //inputParam as integer
        inputParams.set(0, "1");

        fakeInput  = new ByteArrayInputStream("prata\ny".getBytes());
        System.setIn(fakeInput);

        Command testEditDishCommandParamInteger = new EditDishCommand();
        testEditDishCommandParamInteger.execute(inputParams);
        System.setIn(newDishName);

        updatedDishName = "" + DishList.dishList.get(0).getDishName();
        assert (updatedDishName.equals("prata"));

        DishList.dishList.clear();

    }
}