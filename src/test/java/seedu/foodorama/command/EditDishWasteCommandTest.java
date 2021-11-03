package seedu.foodorama.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.foodorama.DishList;
import seedu.foodorama.Ui;
import seedu.foodorama.exceptions.FoodoramaException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EditDishWasteCommandTest {
    InputStream backupInputStream;
    ByteArrayInputStream fakeInput;
    Ui ui;

    @BeforeEach
    void setupStreams() {
        ui = new Ui();
        backupInputStream = System.in;
    }

    @Test
    void execute() throws FoodoramaException {
        //inputParam as String
        ArrayList<String> inputParams = new ArrayList<>();
        inputParams.add("cake");
        DishList.add("cake");

        assertEquals(1, DishList.dishList.size());

        fakeInput  = new ByteArrayInputStream("1.0\ny".getBytes());
        System.setIn(fakeInput);

        Command commandToTest = new EditDishWasteCommand();
        commandToTest.execute(inputParams);

        String updatedDishName = DishList.dishList.get(0).getDishName();
        assertEquals(1.0, DishList.dishList.get(0).getWastage());

        //inputParam as integer
        inputParams.set(0, "1");

        fakeInput  = new ByteArrayInputStream("7.0\ny".getBytes());
        System.setIn(fakeInput);

        commandToTest.execute(inputParams);

        assertEquals(7.0, DishList.dishList.get(0).getWastage());

        //Negative value test
        try {
            fakeInput = new ByteArrayInputStream("-7.0\ny".getBytes());
            System.setIn(fakeInput);
            commandToTest.execute(inputParams);
        } catch (FoodoramaException e) {
            assertEquals(ui.getInvalidNumberMsg(), e.getMessage());
        }

        DishList.dishList.clear();
    }
}