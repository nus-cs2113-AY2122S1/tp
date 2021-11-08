package decodex.logic.commands.recipe;

import java.io.IOException;

import decodex.data.DataManager;
import decodex.data.exception.CommandException;
import decodex.data.exception.ModuleException;
import decodex.data.exception.ModuleManagerException;
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
class RecipePushCommandTest {

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
    public void run_oneModuleToEditingRecipe_recipeSizeIsOne() throws RecipeManagerException, CommandException,
            ModuleException, ModuleManagerException, IOException {
        recipeManager.addRecipe(testRecipe);
        recipeManager.selectRecipeForEditing(testRecipeName);

        String moduleName = "base64encode";
        String[] parameters = {};

        RecipePushCommand testCommand = new RecipePushCommand(moduleName, parameters);
        testCommand.run(dataManager, moduleManager, ui, recipeManager, storage);

        assertEquals(recipeManager.getEditingRecipe().getModuleList().size(), 1);
    }

    @Test
    public void run_emptyModuleName_expectException() throws RecipeManagerException {
        recipeManager.addRecipe(testRecipe);
        recipeManager.selectRecipeForEditing(testRecipeName);

        String moduleName = "";
        String[] parameters = {};

        RecipePushCommand testCommand = new RecipePushCommand(moduleName, parameters);

        assertThrows(CommandException.class, () -> testCommand.run(dataManager, moduleManager, ui, recipeManager,
                storage));
    }
}
