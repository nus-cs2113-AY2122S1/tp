package decodex.logic.commands.recipe;

import java.io.IOException;

import decodex.data.DataManager;
import decodex.data.exception.CommandException;
import decodex.data.exception.DataManagerException;
import decodex.data.exception.ModuleException;
import decodex.data.exception.ModuleManagerException;
import decodex.data.exception.RecipeException;
import decodex.data.exception.RecipeManagerException;
import decodex.modules.ModuleManager;
import decodex.recipes.Recipe;
import decodex.recipes.RecipeManager;
import decodex.storage.Storage;
import decodex.ui.Ui;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RecipeDeselectCommandTest {

    @Test
    public void run_deselectEditingRecipe_successExpectRecipeManagerException() throws RecipeException,
            RecipeManagerException, CommandException, IOException, ModuleException, ModuleManagerException,
            DataManagerException {
        DataManager dataManager = new DataManager();
        ModuleManager moduleManager = new ModuleManager();
        Storage storage = new Storage();
        RecipeManager recipeManager = new RecipeManager();
        Ui ui = new Ui();

        // Add recipe
        String testRecipeName = "test";
        Recipe testRecipe = new Recipe(testRecipeName);
        recipeManager.addRecipe(testRecipe);

        // Select recipe
        RecipeSelectCommand recipeSelectCommand = new RecipeSelectCommand(testRecipeName);
        recipeSelectCommand.run(dataManager, moduleManager, ui, recipeManager, storage);

        // Deselect recipe
        RecipeDeselectCommand recipeDeselectCommand = new RecipeDeselectCommand();
        recipeDeselectCommand.run(dataManager, moduleManager, ui, recipeManager, storage);

        assertThrows(RecipeManagerException.class, () -> recipeManager.getEditingRecipe());
    }

    @Test
    public void run_noSelectedRecipe_expectRecipeManagerException() {
        DataManager dataManager = new DataManager();
        ModuleManager moduleManager = new ModuleManager();
        Storage storage = new Storage();
        RecipeManager recipeManager = new RecipeManager();
        Ui ui = new Ui();

        // Deselect recipe
        RecipeDeselectCommand recipeDeselectCommand = new RecipeDeselectCommand();
        assertThrows(RecipeManagerException.class, () -> recipeDeselectCommand.run(dataManager, moduleManager, ui,
                recipeManager, storage));
    }
}
