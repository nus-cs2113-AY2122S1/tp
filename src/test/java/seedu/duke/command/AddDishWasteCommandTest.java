package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.DishList;
import seedu.duke.Ingredient;
import seedu.duke.IngredientList;
import seedu.duke.Ui;
import seedu.duke.exceptions.FoodoramaException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AddDishWasteCommandTest {

    @Test
    void execute() {
        //Define inputs
        Ui ui = new Ui();
        ArrayList<String> inputs = new ArrayList<>();
        inputs.add("prata");
        AddDishWasteCommand commandToTest = new AddDishWasteCommand();
        //Add the dish
        DishList.add("prata");
        try {
            //Spoof scanner input with input stream
            InputStream backupInputStream = System.in;
            ByteArrayInputStream fakeInput = new ByteArrayInputStream("2.33".getBytes());
            System.setIn(fakeInput);
            commandToTest.execute(inputs);
            System.setIn(backupInputStream);
            assertEquals(2.33,DishList.dishList.get(0).getWastage());
            //Clear lists
            DishList.clearList();
            IngredientList.clearList();
            //Test case if dish not present
            commandToTest.execute(inputs);
        } catch (FoodoramaException e) {
            assertEquals(ui.getDishNotExistMsg("prata"), e.getMessage());
        }
    }
}