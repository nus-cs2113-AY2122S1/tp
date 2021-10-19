package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.IngredientList;
import seedu.duke.exceptions.FoodoramaException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeleteIngrCommandTest {

    @Test
    void execute() throws FoodoramaException {
        ArrayList<String> inputParams = new ArrayList<>();
        inputParams.add("chicken");
        AddIngrCommand myIngrCommand = new AddIngrCommand();
        InputStream backupInputStream = System.in;
        ByteArrayInputStream fakeInput = new ByteArrayInputStream("2.33".getBytes());
        System.setIn(fakeInput);
        myIngrCommand.execute(inputParams);
        System.setIn(backupInputStream);
        assertEquals(1, IngredientList.ingredientList.size());

        DeleteIngrCommand deleteIngrCommand = new DeleteIngrCommand();
        deleteIngrCommand.execute(inputParams);

        assertEquals(0, IngredientList.ingredientList.size());
    }
}