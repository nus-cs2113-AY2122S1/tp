package decodex.logic.commands.recipe;

import decodex.data.DataManager;
import decodex.data.exception.CommandException;
import decodex.data.exception.RecipeException;
import decodex.data.exception.RecipeManagerException;
import decodex.modules.ModuleManager;
import decodex.recipes.Recipe;
import decodex.recipes.RecipeManager;
import decodex.storage.Storage;
import decodex.ui.Ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

// @@author rizemon
class RecipeSelectCommandTest {

    private ModuleManager moduleManager;
    private RecipeManager recipeManager;
    private DataManager dataManager;
    private Ui ui;
    private Storage storage;

    private String testRecipeName;
    private Recipe testRecipe;

    @BeforeEach
    public void createInstances() throws RecipeException {
        dataManager = new DataManager();
        recipeManager = new RecipeManager();
        moduleManager = new ModuleManager();
        ui = new Ui();
        storage = new Storage();

        testRecipeName = "test";
        testRecipe = new Recipe(testRecipeName);
    }

    @Test
    public void run_selectExistingRecipe_existingRecipe() throws RecipeManagerException, CommandException {
        recipeManager.addRecipe(testRecipe);
        RecipeSelectCommand testCommand = new RecipeSelectCommand(testRecipeName);
        testCommand.run(dataManager, moduleManager, ui, recipeManager, storage);
        assertEquals(recipeManager.getEditingRecipe().getName(), testRecipeName);
    }

    @Test
    public void run_selectNonExistentRecipe_expectException() {
        RecipeSelectCommand testCommand = new RecipeSelectCommand(testRecipeName);
        assertThrows(RecipeManagerException.class,
            () -> testCommand.run(dataManager, moduleManager, ui, recipeManager, storage));
    }

    @Test
    public void run_nullRecipe_expectException() {
        String testRecipeName = "";

        RecipeSelectCommand testCommand = new RecipeSelectCommand(testRecipeName);
        assertThrows(CommandException.class,
            () -> testCommand.run(dataManager, moduleManager, ui, recipeManager, storage));
    }
}
