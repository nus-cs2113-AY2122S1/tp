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

class AddIngrStoredCommandTest {

    @Test
    void execute() {
        //Define inputs
        Ui ui = new Ui();
        ArrayList<String> inputs = new ArrayList<>();
        inputs.add("flour");
        AddIngrStoredCommand commandToTest = new AddIngrStoredCommand();
        //Add the dish and ingr to link
        IngredientList.ingredientList.add(new Ingredient("flour" , 2.34));
        try {
            InputStream backupInputStream = System.in;
            ByteArrayInputStream fakeInput = new ByteArrayInputStream("2".getBytes());
            System.setIn(fakeInput);
            commandToTest.execute(inputs);
            System.setIn(backupInputStream);
            assertEquals(4.34,IngredientList.ingredientList.get(0).getIngredientWeight());
            //Clear lists
            DishList.clearList();
            IngredientList.clearList();
            //Test case if dish not present
            commandToTest.execute(inputs);
        } catch (FoodoramaException e) {
            assertEquals(ui.getIngrNotExistMsg(), e.getMessage());
        }
    }
}