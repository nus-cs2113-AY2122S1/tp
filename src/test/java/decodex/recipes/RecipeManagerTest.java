package decodex.recipes;

import decodex.data.exception.RecipeException;
import decodex.data.exception.RecipeManagerException;
import decodex.modules.Module;
import decodex.modules.hex.HexEncoder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

// @@author rizemon
class RecipeManagerTest {

    private RecipeManager recipeManager;
    private String testRecipeName;
    private Recipe testRecipe;
    private Recipe repeatedRecipe;
    private Module insertedModule;

    @BeforeEach
    public void createInstances() throws RecipeException {
        recipeManager = new RecipeManager();
        testRecipeName = "testRecipeName";
        testRecipe = new Recipe(testRecipeName);
        repeatedRecipe = new Recipe(testRecipeName);
        insertedModule = new HexEncoder();
    }

    @Test
    void addRecipe_repeatedRecipeName_expectException() throws RecipeManagerException {
        recipeManager.addRecipe(testRecipe);
        assertThrows(RecipeManagerException.class, () -> recipeManager.addRecipe(repeatedRecipe));
    }

    @Test
    void removeRecipe_oneRecipe_removedRecipeIsTheOneRecipe() throws RecipeManagerException {
        recipeManager.addRecipe(testRecipe);
        Recipe removedRecipe = recipeManager.removeRecipe(testRecipeName);
        assertEquals(removedRecipe.getName(), testRecipeName);
    }

    @Test
    void removeRecipe_noRecipe_expectException() {
        assertThrows(RecipeManagerException.class, () -> recipeManager.removeRecipe(testRecipeName));
    }

    @Test
    void removeRecipe_recipeIsEditedRecipe_expectException() throws RecipeManagerException {
        recipeManager.addRecipe(testRecipe);
        recipeManager.selectRecipeForEditing(testRecipeName);
        recipeManager.removeRecipe(testRecipeName);
        assertThrows(RecipeManagerException.class, () -> recipeManager.getEditingRecipe());
    }

    @Test
    void getRecipe_oneRecipe_managerContainOneRecipe() throws RecipeManagerException {
        recipeManager.addRecipe(testRecipe);
        Recipe retrievedRecipe = recipeManager.getRecipe(testRecipeName);
        assertEquals(testRecipe.getName(), retrievedRecipe.getName());
    }

    @Test
    void getRecipe_noRecipe_expectException() {
        assertThrows(RecipeManagerException.class, () -> recipeManager.getRecipe(testRecipeName));
    }

    @Test
    void getEditedRecipe_oneEditedRecipe_editedRecipe() throws RecipeManagerException {
        recipeManager.addRecipe(testRecipe);
        recipeManager.selectRecipeForEditing(testRecipeName);
        assertEquals(recipeManager.getEditingRecipe().getName(), testRecipeName);
    }

    @Test
    void getEditedRecipe_noEditedRecipe_expectException() {
        assertThrows(RecipeManagerException.class, () -> recipeManager.selectRecipeForEditing(testRecipeName));
    }

    @Test
    void selectRecipe_oneEditedRecipe_editedRecipe() throws RecipeManagerException {
        recipeManager.addRecipe(testRecipe);
        recipeManager.selectRecipeForEditing(testRecipeName);
        assertEquals(recipeManager.getEditingRecipe().getName(), testRecipeName);
    }

    @Test
    void selectRecipe_nonExistentRecipeName_expectException() {
        String testRecipeName = "testRecipeName";
        assertThrows(RecipeManagerException.class, () -> recipeManager.selectRecipeForEditing(testRecipeName));
    }

    @Test
    void pushModuleIntoEditedRecipe_oneEditedRecipe_oneModule() throws RecipeManagerException {
        recipeManager.addRecipe(testRecipe);
        recipeManager.selectRecipeForEditing(testRecipeName);

        recipeManager.pushModuleIntoEditedRecipe(insertedModule);
        assertEquals(recipeManager.getEditingRecipe().getModuleList().size(), 1);
    }

    @Test
    void pushModuleIntoEditedRecipe_noEditedRecipe_expectException() throws RecipeManagerException {
        recipeManager.addRecipe(testRecipe);
        assertThrows(RecipeManagerException.class, () -> recipeManager.pushModuleIntoEditedRecipe(insertedModule));
    }

    @Test
    void popModuleFromEditedRecipe_oneEditedRecipe_poppedModule() throws RecipeException, RecipeManagerException {
        testRecipe.push(insertedModule);
        recipeManager.addRecipe(testRecipe);
        recipeManager.selectRecipeForEditing(testRecipeName);

        Module poppedModule = recipeManager.popModuleFromEditedRecipe();
        assertEquals(insertedModule.getName(), poppedModule.getName());
    }

    @Test
    void popModuleFromEditedRecipe_noEditedRecipe_expectException() throws RecipeManagerException {
        testRecipe.push(insertedModule);
        recipeManager.addRecipe(testRecipe);

        assertThrows(RecipeManagerException.class, () -> recipeManager.popModuleFromEditedRecipe());
    }

    @Test
    void resetEditedRecipe_oneEditedRecipe_noError() throws RecipeManagerException {
        recipeManager.addRecipe(testRecipe);
        recipeManager.selectRecipeForEditing(testRecipeName);

        recipeManager.resetEditedRecipe();
        assertEquals(recipeManager.getEditingRecipe().getModuleList().size(), 0);
    }

    @Test
    void resetEditedRecipe_noEditedRecipe_expectException() throws RecipeManagerException {
        recipeManager.addRecipe(testRecipe);
        assertThrows(RecipeManagerException.class, () -> recipeManager.resetEditedRecipe());
    }
}
