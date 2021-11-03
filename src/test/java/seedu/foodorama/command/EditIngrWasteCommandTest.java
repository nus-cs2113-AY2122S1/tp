package seedu.foodorama.command;

import org.junit.jupiter.api.Test;
import seedu.foodorama.IngredientList;
import seedu.foodorama.exceptions.FoodoramaException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EditIngrWasteCommandTest {

    @Test
    void execute() throws FoodoramaException {

        ArrayList<String> inputParams = new ArrayList<>();
        inputParams.add("chicken");

        InputStream ingrWeight = System.in;
        InputStream fakeInputWeight  = new ByteArrayInputStream("5".getBytes());
        System.setIn(fakeInputWeight);

        //Add Ingredient Chicken
        Command testAddIngrCommand = new AddIngrCommand();
        testAddIngrCommand.execute(inputParams);
        System.setIn(ingrWeight);

        assertEquals(1, IngredientList.ingredientList.size());

        InputStream ingrWasteWeight = System.in;
        InputStream fakeInputWasteWeight  = new ByteArrayInputStream("3.33".getBytes());
        System.setIn(fakeInputWasteWeight);

        //Add 3.33kg Chicken Waste
        Command testAddIngrWasteCommand = new AddIngrWasteCommand();
        testAddIngrWasteCommand.execute(inputParams);
        System.setIn(ingrWasteWeight);

        assertEquals(3.33, IngredientList.ingredientList.get(0).getWastage());


        fakeInputWasteWeight  = new ByteArrayInputStream("10\ny".getBytes());
        System.setIn(fakeInputWasteWeight);

        //Edit Chicken Waste to 10kg
        Command testEditIngrWasteCommand = new EditIngrWasteCommand();
        testEditIngrWasteCommand.execute(inputParams);
        System.setIn(ingrWasteWeight);

        assertEquals(10, IngredientList.ingredientList.get(0).getWastage());

    }
}