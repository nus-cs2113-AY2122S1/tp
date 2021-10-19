package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.DishList;
import seedu.duke.Ingredient;
import seedu.duke.IngredientList;
import seedu.duke.exceptions.FoodoramaException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AddIngrWasteCommandTest {
    @Test
    void execute() {
        //Define inputs
        ArrayList<String> inputs = new ArrayList<>();
        inputs.add("carrot");
        AddIngrWasteCommand commandToTest = new AddIngrWasteCommand();
        //Add the dish and ingr to link
        IngredientList.ingredientList.add(new Ingredient("carrot" , 2.34));
        try {
            InputStream backupInputStream = System.in;
            ByteArrayInputStream fakeInput = new ByteArrayInputStream("3.56".getBytes());
            System.setIn(fakeInput);
            commandToTest.execute(inputs);
            System.setIn(backupInputStream);
            assertEquals(3.56,IngredientList.ingredientList.get(0).getWastage());
            //Clear lists
            DishList.clearList();
            IngredientList.clearList();
            //Test case if dish not present
            commandToTest.execute(inputs);
        } catch (FoodoramaException e) {
            //Unreachable
            assertEquals("The ingredient carrot does not exist", e.getMessage());
        }
    }
}