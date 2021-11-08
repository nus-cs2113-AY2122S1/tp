package decodex.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Arrays;

import decodex.data.exception.RecipeException;
import decodex.data.exception.StorageException;
import decodex.modules.ModuleManager;
import decodex.recipes.Recipe;
import decodex.recipes.RecipeManager;
import decodex.ui.Ui;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StorageTest {
    private static final String RECIPE_FILE_SUFFIX = ".txt";

    // Used for creating test recipes and files.
    private static final String TEST_FILE_NAME_1 = "testfile1.txt";
    private static final String TEST_RECIPE_NAME = "testfile1";

    // Used for one time set up for storage test.
    private static boolean isRecipeDirectoryExistAtStart;
    private static File recipeDirectory;

    File file1;
    Path path1;

    ModuleManager moduleManager;
    RecipeManager recipeManager;
    Ui ui;
    Storage storage;
    Recipe testRecipe;

    // Used to specify the recipe files (both valid and invalid)
    // that already exist on the user/developer's recipe directory.
    String[] preExistingRecipeFiles;

    @TempDir
    Path tempDir;

    @BeforeAll
    public static void oneTimeSetUpStorageTest() {
        recipeDirectory = new File(System.getProperty("user.dir") + "/recipe");
        if (recipeDirectory.exists()) {
            isRecipeDirectoryExistAtStart = true;
        } else {
            recipeDirectory.mkdir();
        }
    }

    @AfterAll
    public static void oneTimeTearDownStorageTest() {
        if (!isRecipeDirectoryExistAtStart) {
            recipeDirectory.delete();
        }
    }

    // Acknowledgements - JUnit testing for storage.
    // https://blogs.oracle.com/javamagazine/post/working-and-unit-testing-with-temporary-files-in-java.
    // https://tinyurl.com/kp5rc33v - To remove the auto-appended numbers after the temporary file prefix.
    @BeforeEach
    public void setUpStorageTest() throws RecipeException {
        testRecipe = new Recipe(TEST_RECIPE_NAME);
        tempDir = recipeDirectory.toPath();
        moduleManager = new ModuleManager();
        recipeManager = new RecipeManager();
        storage = new Storage();
        ui = new Ui();
        path1 = tempDir.resolve(TEST_FILE_NAME_1);
        file1 = path1.toFile();
        getAllExistingRecipeFiles();
    }

    /**
     * Tears down and resets the current test state for the next JUnit test.
     */
    @AfterEach
    public void tearDownStorageTest() {
        if (file1.exists()) {
            file1.delete();
        }
    }

    /**
     * This test method is to test if given that a new valid (empty) recipe file is added to the recipe folder,
     * then this recipes file should be loaded into Decodex.
     * Empty recipe files are by default valid recipes, since recipes can have no modules in it.
     * Note that any invalid recipe files in the recipe/ directory will be flagged out as a print-out message.
     */
    @Test
    void loadRecipesFromDirectory_twoNewValidRecipeFiles_twoNewLoadedRecipes() throws IOException, StorageException {
        createOneValidTemporaryRecipeFiles();
        String[] expectedLoadedRecipes = {"testfile1"};
        storage.loadRecipesFromDirectory(moduleManager, recipeManager, ui);

        String[] loadedRecipes = recipeManager.getRecipeNames();
        String[] newLoadedRecipes = Arrays.stream(loadedRecipes)
                .filter(recipeName -> {
                    String correspondingRecipeFileName = recipeName + RECIPE_FILE_SUFFIX;
                    return !Arrays.asList(preExistingRecipeFiles).contains(correspondingRecipeFileName);
                })
                .toArray(size -> new String[size]);
        assertArrayEquals(expectedLoadedRecipes, newLoadedRecipes);
    }

    @Test
    void saveRecipeToFile_newRecipe_newCorrespondingRecipeFile() throws IOException, StorageException {
        storage.saveRecipeToFile(testRecipe);

        boolean isExistAndFile = file1.exists() && file1.isFile();
        assertTrue(isExistAndFile, "The new test recipe file has been created and is an actual file!");
    }

    @Test
    void deleteRecipeFile_deleteRecipe_correspondingRecipeFileDeleted() throws IOException, StorageException {
        createOneValidTemporaryRecipeFiles();

        storage.deleteRecipeFile(testRecipe.getName());
        boolean isDeleted = !file1.exists();
        assertTrue(isDeleted, "The corresponding test recipe file has been deleted!");
    }

    /**
     * Gets all the existing recipe files at the point of time when this is run.
     */
    private void getAllExistingRecipeFiles() {
        File recipeDir = tempDir.toFile();
        File[] files = recipeDir.listFiles();

        preExistingRecipeFiles = Arrays.stream(files)
                .filter(file -> file.isFile())
                .filter(file -> {
                    String fileName = file.getName();
                    return fileName.endsWith(RECIPE_FILE_SUFFIX);
                })
                .map(file -> file.getName())
                .toArray(size -> new String[size]);
    }

    private void createOneValidTemporaryRecipeFiles() {
        try {
            file1.createNewFile();
        } catch (InvalidPathException | IOException err) {
            System.err.println("Error creating temporary test file in " + this.getClass().getSimpleName());
        }
    }
}
