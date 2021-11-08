package decodex.logic.commands;

import java.util.Arrays;

import decodex.data.Data;
import decodex.data.DataManager;
import decodex.data.exception.CommandException;
import decodex.data.exception.DataManagerException;
import decodex.data.exception.RecipeException;
import decodex.modules.ModuleManager;
import decodex.recipes.Recipe;
import decodex.recipes.RecipeManager;
import decodex.storage.Storage;
import decodex.ui.Ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

// @@author rizemon
public class InputCommandTest {

    private ModuleManager moduleManager;
    private RecipeManager recipeManager;
    private DataManager dataManager;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    public void createInstances() {
        dataManager = new DataManager();
        recipeManager = new RecipeManager();
        moduleManager = new ModuleManager();
        ui = new Ui();
        storage = new Storage();
    }

    @Test
    public void run_missingArguments_expectException() {
        String dataString = "";
        InputCommand testCommand = new InputCommand(dataString);
        assertThrows(CommandException.class, () -> testCommand.run(dataManager, moduleManager, ui, recipeManager,
                storage));
    }

    @Test
    public void run_insertData_insertedData() throws CommandException, DataManagerException {
        String dataString = "something";
        InputCommand testCommand = new InputCommand(dataString);
        testCommand.run(dataManager, moduleManager, ui, recipeManager, storage);
        Data testData = new Data(dataString);
        assertArrayEquals(dataManager.getOriginalData().getRawBytes(), testData.getRawBytes());
    }
}
