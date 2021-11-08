package decodex.logic.commands.recipe;

import java.io.IOException;

import decodex.data.Data;
import decodex.data.DataManager;
import decodex.data.exception.ModuleException;
import decodex.data.exception.ModuleManagerException;
import decodex.data.exception.RecipeException;
import decodex.data.exception.RecipeManagerException;
import decodex.modules.Module;
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
class RecipePopCommandTest {

    private ModuleManager moduleManager;
    private RecipeManager recipeManager;
    private DataManager dataManager;
    private Ui ui;
    private Storage storage;
    private RecipePopCommand testCommand;

    private String testRecipeName;
    private Recipe testRecipe;


    @BeforeEach
    public void createInstances() throws RecipeException {
        dataManager = new DataManager();
        recipeManager = new RecipeManager();
        moduleManager = new ModuleManager();
        ui = new Ui();
        storage = new Storage();
        testCommand = new RecipePopCommand();

        testRecipeName = "test";
        testRecipe = new Recipe(testRecipeName);
    }

    @Test
    public void run_oneModuleToEditingRecipe_recipeSizeIsZero() throws RecipeManagerException, ModuleManagerException,
            ModuleException, RecipeException, IOException {
        recipeManager.addRecipe(testRecipe);
        recipeManager.selectRecipeForEditing(testRecipeName);

        String moduleName = "base64encode";
        String[] parameters = {};
        Module module = moduleManager.selectModule(moduleName, parameters);
        testRecipe.push(module);

        testCommand.run(dataManager, moduleManager, ui, recipeManager, storage);
        assertEquals(recipeManager.getEditingRecipe().getModuleList().size(), 0);
    }

    @Test
    public void run_emptyEditingRecipe_expectException() throws RecipeManagerException {
        recipeManager.addRecipe(testRecipe);
        recipeManager.selectRecipeForEditing(testRecipeName);
        assertThrows(RecipeException.class, () -> testCommand.run(dataManager, moduleManager, ui, recipeManager,
                storage));
    }
}
