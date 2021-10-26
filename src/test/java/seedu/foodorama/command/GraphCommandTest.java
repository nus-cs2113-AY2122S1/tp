package seedu.foodorama.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.foodorama.Dish;
import seedu.foodorama.DishList;
import seedu.foodorama.Ingredient;
import seedu.foodorama.IngredientList;
import seedu.foodorama.Ui;
import seedu.foodorama.exceptions.FoodoramaException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GraphCommandTest {
    PrintStream originalOutputStream;
    ByteArrayOutputStream newOutputStream;
    InputStream backupInputStream;
    Ui ui;

    @BeforeEach
    void setupStreams() {
        ui = new Ui();
        backupInputStream = System.in;
        originalOutputStream = System.out;
        newOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newOutputStream));
    }

    @Test
    void execute() throws FoodoramaException {
        //Define inputs
        ArrayList<String> inputs = new ArrayList<>();
        inputs.add("dish");
        GraphCommand commandToTest = new GraphCommand();
        ByteArrayInputStream fakeInput;
        //Dish
        try {
            Dish dishToAdd = new Dish("carrot cake");
            fakeInput = new ByteArrayInputStream("2.33".getBytes());
            System.setIn(fakeInput);
            dishToAdd.addWaste();
            DishList.dishList.add(dishToAdd);
            Dish dishToAdd2 = new Dish("chocolate cake");
            fakeInput = new ByteArrayInputStream("7.25".getBytes());
            System.setIn(fakeInput);
            dishToAdd2.addWaste();
            DishList.dishList.add(dishToAdd2);
            double max = DishList.getGreatestWaste();
            commandToTest.execute(inputs);
            //assertTrue(newOutputStream.toString().trim().contains(dishToAdd.getGraphHeight(max)));
            //assertTrue(newOutputStream.toString().trim().contains(dishToAdd2.getGraphHeight(max)));
            DishList.dishList.clear();
            commandToTest.execute(inputs);
        } catch (FoodoramaException e) {
            //assertEquals("List is empty, nothing to show", e.getMessage());
        }

        //Ingr
        try {
            inputs.set(0,"ingr");
            Ingredient ingredientToAdd = new Ingredient("chocolate", 3.33);
            fakeInput = new ByteArrayInputStream("7.25".getBytes());
            System.setIn(fakeInput);
            ingredientToAdd.addWaste();
            IngredientList.ingredientList.add(ingredientToAdd);
            Ingredient ingredientToAdd2 = new Ingredient("chocolate", 3.33);
            fakeInput = new ByteArrayInputStream("7.25".getBytes());
            System.setIn(fakeInput);
            ingredientToAdd2.addWaste();
            IngredientList.ingredientList.add(ingredientToAdd2);
            double max = IngredientList.getGreatestWaste();
            commandToTest.execute(inputs);
            //Todo modify tests to reflect any changes if present
            //assertTrue(newOutputStream.toString().trim().contains(ingredientToAdd.getGraphHeight(max)));
            //assertTrue(newOutputStream.toString().trim().contains(ingredientToAdd2.getGraphHeight(max)));
            IngredientList.ingredientList.clear();
            commandToTest.execute(inputs);
        } catch (FoodoramaException e) {
            //assertEquals("List is empty, nothing to show", e.getMessage());
        }

        //Other
        inputs.set(0,"not dish or ingr");
        try {
            commandToTest.execute(inputs);
        } catch (FoodoramaException e) {
            //assertEquals(ui.getGraphInvalidParamMsg(), e.getMessage());
        }

    }

    @AfterEach
    void reset() {
        DishList.dishList.clear();
        IngredientList.ingredientList.clear();
        System.setOut(originalOutputStream);
        System.setIn(backupInputStream);
    }
}