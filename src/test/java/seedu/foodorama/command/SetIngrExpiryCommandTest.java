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

class SetIngrExpiryCommandTest {
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
        inputs.add("chicken");
        SetIngrExpiryCommand commandToTest = new SetIngrExpiryCommand();
        //Add the ingredient
        IngredientList.ingredientList.add(new Ingredient("chicken", 2.55));
        try {
            //Spoof scanner input with input stream
            fakeInput = new ByteArrayInputStream("30/11/2021".getBytes());
            System.setIn(fakeInput);
            commandToTest.execute(inputs);
            assertEquals("30/11/2021", IngredientList.ingredientList.get(0).getExpiryDate());
        } catch (FoodoramaException e) {
            assertEquals(ui.getIncorrectExpiryDateFormatMsg(), e.getMessage());
        }
        //Clear lists
        DishList.dishList.clear();
        IngredientList.ingredientList.clear();
        //Test case if ingredient not present
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