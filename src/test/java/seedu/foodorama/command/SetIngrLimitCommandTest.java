package seedu.foodorama.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.foodorama.DishList;
import seedu.foodorama.Ingredient;
import seedu.foodorama.IngredientList;
import seedu.foodorama.Ui;
import seedu.foodorama.exceptions.FoodoramaException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SetIngrLimitCommandTest {
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
        inputs.add("chocolate");
        SetIngrLimitCommand commandToTest = new SetIngrLimitCommand();
        //Add the ingredient
        Ingredient ingredientToAdd = new Ingredient("chocolate", 3.33);
        IngredientList.ingredientList.add(ingredientToAdd);
        System.out.println(IngredientList.ingredientList.get(0));
        try {
            //Spoof scanner input with input stream
            fakeInput = new ByteArrayInputStream("7.2".getBytes());
            System.setIn(fakeInput);
            commandToTest.execute(inputs);
            assertEquals(7.2,IngredientList.ingredientList.get(0).getLimit());
            //Test case for negative inputs
            fakeInput = new ByteArrayInputStream("-22\n33".getBytes());
            System.setIn(fakeInput);
            commandToTest.execute(inputs);
            //System.setIn(fakeInput);
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
            assertEquals(ui.getIngrNotExistMsg(), e.getMessage());
        }
    }

    @AfterEach
    void reset() {
        DishList.dishList.clear();
        IngredientList.ingredientList.clear();
        System.setIn(backupInputStream);
    }

}