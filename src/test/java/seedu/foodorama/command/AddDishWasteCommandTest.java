package seedu.foodorama.command;

import org.junit.jupiter.api.Test;
import seedu.foodorama.DishList;
import seedu.foodorama.IngredientList;
import seedu.foodorama.Ui;
import seedu.foodorama.exceptions.FoodoramaException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
            DishList.dishList.clear();
            IngredientList.ingredientList.clear();
            //Test case if dish not present
            commandToTest.execute(inputs);
        } catch (FoodoramaException e) {
            assertEquals(ui.getDishNotExistMsg("prata"), e.getMessage());
        }
    }
}