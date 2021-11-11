package seedu.foodorama.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.foodorama.DishList;
import seedu.foodorama.IngredientList;
import seedu.foodorama.Ui;
import seedu.foodorama.exceptions.FoodoramaException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SetDishLimitCommandTest {
    InputStream backupInputStream;
    ByteArrayInputStream fakeInput;
    Ui ui;

    @BeforeEach
    void setupStreams() {
        ui = new Ui();
        backupInputStream = System.in;
    }

    @Test
    void execute() {
        //Define inputs
        Ui ui = new Ui();
        ArrayList<String> inputs = new ArrayList<>();
        inputs.add("prata");
        SetDishLimitCommand commandToTest = new SetDishLimitCommand();
        //Add the dish
        DishList.add("prata");
        try {
            //Spoof scanner input with input stream
            fakeInput = new ByteArrayInputStream("7.2".getBytes());
            System.setIn(fakeInput);
            commandToTest.execute(inputs);
            assertEquals(7.2,DishList.dishList.get(0).getLimit());
            //Test case for negative inputs
            fakeInput = new ByteArrayInputStream("-22\n2".getBytes());
            System.setIn(fakeInput);
            commandToTest.execute(inputs);
        } catch (FoodoramaException e) {
            assertEquals(ui.getInvalidNumberMsg(), e.getMessage());
        }
        //Clear lists
        DishList.dishList.clear();
        IngredientList.ingredientList.clear();
        //Test case if dish not present
        try {
            commandToTest.execute(inputs);
        } catch (FoodoramaException e) {
            assertEquals(ui.getDishNotExistMsg("prata"), e.getMessage());
        }
    }

    @AfterEach
    void reset() {
        DishList.dishList.clear();
        IngredientList.ingredientList.clear();
        System.setIn(backupInputStream);
    }

}