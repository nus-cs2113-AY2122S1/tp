package seedu.foodorama.command;

import org.junit.jupiter.api.Test;
import seedu.foodorama.IngredientList;
import seedu.foodorama.exceptions.FoodoramaException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EditIngrCommandTest {

    @Test
    void execute() throws FoodoramaException {
        //inputParam as String
        ArrayList<String> inputParams = new ArrayList<>();
        inputParams.add("chicken");

        InputStream ingrWeight = System.in;
        InputStream fakeInputWeight  = new ByteArrayInputStream("5".getBytes());
        System.setIn(fakeInputWeight);

        Command testAddIngrCommand = new AddIngrCommand();
        testAddIngrCommand.execute(inputParams);
        System.setIn(ingrWeight);

        assertEquals(1, IngredientList.ingredientList.size());

        InputStream newIngrName = System.in;
        InputStream fakeInput  = new ByteArrayInputStream("fish\ny".getBytes());
        System.setIn(fakeInput);

        Command testEditIngrCommandParamString = new EditIngrCommand();
        testEditIngrCommandParamString.execute(inputParams);
        System.setIn(newIngrName);

        String updatedIngrName = "" + IngredientList.ingredientList.get(0).getIngredientName();
        assert (updatedIngrName.equals("fish"));

        //inputParam as integer
        inputParams.set(0, "1");

        fakeInput  = new ByteArrayInputStream("chicken\ny".getBytes());
        System.setIn(fakeInput);

        Command testEditIngrCommandParamInteger = new EditIngrCommand();
        testEditIngrCommandParamInteger.execute(inputParams);
        System.setIn(newIngrName);

        updatedIngrName = "" + IngredientList.ingredientList.get(0).getIngredientName();
        assert (updatedIngrName.equals("chicken"));

        IngredientList.ingredientList.clear();

    }
}