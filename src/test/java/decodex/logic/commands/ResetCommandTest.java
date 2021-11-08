package decodex.logic.commands;

import java.util.Arrays;

import decodex.data.Data;
import decodex.data.DataManager;
import decodex.data.exception.DataManagerException;
import decodex.modules.ModuleManager;
import decodex.recipes.RecipeManager;
import decodex.storage.Storage;
import decodex.ui.Ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// @@author rizemon
class ResetCommandTest {

    private ModuleManager moduleManager;
    private RecipeManager recipeManager;
    private DataManager dataManager;
    private Ui ui;
    private Storage storage;

    private Data originalData;
    private Data newData;

    @BeforeEach
    public void createInstances() {
        dataManager = new DataManager();
        recipeManager = new RecipeManager();
        moduleManager = new ModuleManager();
        ui = new Ui();
        storage = new Storage();

        originalData = new Data("hi");
        newData = new Data("bye");
    }

    @Test
    public void run_noChanges_sameOriginalData() throws DataManagerException {
        dataManager.setOriginalData(originalData);

        ResetCommand testCommand = new ResetCommand();
        testCommand.run(dataManager, moduleManager, ui, recipeManager, storage);
        assertArrayEquals(dataManager.getCurrentData().getRawBytes(), originalData.getRawBytes());
    }

    @Test
    public void run_newData_sameOriginalData() throws DataManagerException {
        dataManager.setOriginalData(originalData);
        dataManager.setCurrentData(newData);

        ResetCommand testCommand = new ResetCommand();
        testCommand.run(dataManager, moduleManager, ui, recipeManager, storage);
        assertArrayEquals(dataManager.getCurrentData().getRawBytes(), originalData.getRawBytes());
    }
}
