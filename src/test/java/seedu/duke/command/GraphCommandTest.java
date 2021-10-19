package seedu.duke.command;

import org.junit.jupiter.api.*;
import seedu.duke.*;
import seedu.duke.exceptions.FoodoramaException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

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
        try{
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
            assertTrue(newOutputStream.toString().trim().contains(dishToAdd.toGraph(max)));
            assertTrue(newOutputStream.toString().trim().contains(dishToAdd2.toGraph(max)));
            DishList.clearList();
            commandToTest.execute(inputs);
        } catch (FoodoramaException e) {
            assertEquals("List is empty, nothing to show", e.getMessage());
        }

        //Ingr
        try{
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
            assertTrue(newOutputStream.toString().trim().contains(ingredientToAdd.toGraph(max)));
            assertTrue(newOutputStream.toString().trim().contains(ingredientToAdd2.toGraph(max)));
            IngredientList.clearList();
            commandToTest.execute(inputs);
        } catch (FoodoramaException e) {
            assertEquals("List is empty, nothing to show", e.getMessage());
        }

        //Other
        inputs.set(0,"not dish or ingr");
        try {
            commandToTest.execute(inputs);
        } catch (FoodoramaException e) {
            assertEquals(ui.getGraphInvalidParamMsg(), e.getMessage());
        }

    }

    @AfterEach
    void reset() {
        DishList.clearList();
        IngredientList.clearList();
        System.setOut(originalOutputStream);
        System.setIn(backupInputStream);
    }
}