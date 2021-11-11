package seedu.foodorama.command;

import org.junit.jupiter.api.Test;
import seedu.foodorama.IngredientList;
import seedu.foodorama.exceptions.FoodoramaException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EditIngrStoredCommandTest {

    @Test
    void execute() throws FoodoramaException {
        //input params as String
        ArrayList<String> inputParams = new ArrayList<>(); //new ArrayList for inputParams
        inputParams.add("duck"); //add "duck" to inputParams ArrayList


        InputStream fakeInputWeight = new ByteArrayInputStream("5".getBytes()); //initialise "5" to be used
        System.setIn(fakeInputWeight); //use 5 as inputWeight
        InputStream ingrWeight = System.in; //prepare variable for ingrWeight

        Command testAddIngrCommand = new AddIngrCommand(); //initialise an AddIngrCommand
        testAddIngrCommand.execute(inputParams); //adding "duck"
        System.setIn(ingrWeight); //next line, "5" for weight

        //assert that there is only one ingredient in ingredientList which is duck
        assertEquals(1, IngredientList.ingredientList.size());

        //check that the current weight of duck is 5
        assertEquals(5, IngredientList.ingredientList.get(0).getIngredientWeight());

        //up till here, so far so good. duck has 5kg stored

        //now, for the EditIngrStoredCommand. we want to edit the 5kg of duck into lets say, 10kg

        //use "10" as new weight
        InputStream fakeInputNewWeight = new ByteArrayInputStream("10\ny".getBytes());
        System.setIn(fakeInputNewWeight);
        InputStream ingrNewWeight = System.in;

        //we initialise the EditIngrStoredCommand
        Command testEditIngrStoredCommand = new EditIngrStoredCommand();

        //attempt to edit duck
        testEditIngrStoredCommand.execute(inputParams);

        //put in "10" into the "command line" and "y" to confirm change
        System.setIn(ingrNewWeight);

        //check that the new ingredientWeight of duck is 10.0kg
        assertEquals(10.0, IngredientList.ingredientList.get(0).getIngredientWeight());

        //clear the ingredientList
        IngredientList.ingredientList.clear();
    }


}
