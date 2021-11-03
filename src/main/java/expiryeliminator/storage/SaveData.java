package expiryeliminator.storage;

import expiryeliminator.data.IngredientRepository;
import expiryeliminator.data.RecipeList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SaveData {

    /**
     * Saves the recipe list to the txt file.
     *
     * @param recipes The whole recipe list.
     */
    public static void saveRecipeListToFile(RecipeList recipes) {
        String pathName = "./data/";
        String fileName = "recipeList.txt";
        flushFile(pathName, fileName);
        try {
            String textToAppend = recipes.getWholeRecipeList();
            appendToFile(pathName + fileName, textToAppend);
        } catch (IOException e) {
            System.out.println("An recipeList IO error has occurred: " + e.getMessage());
        }
    }

    /**
     * Saves the whole ingredient repository to the txt file.
     *
     * @param ingredients The whole ingredient repository.
     */
    public static void saveIngredientRepoToFile(IngredientRepository ingredients) {
        String pathName = "./data/";
        String fileName = "ingredientRepo.txt";
        flushFile(pathName, fileName);
        try {
            String textToAppend = ingredients.printWholeList();
            appendToFile(pathName + fileName, textToAppend);
        } catch (IOException e) {
            System.out.println("An IngredientList IO error has occurred: " + e.getMessage());
        }
    }

    /**
     * Flushes the txt file.
     *
     * @param pathName The pathname of the txt file, containing the directory name.
     * @param fileName The filename of the txt file, containing the file name.
     */
    public static void flushFile(String pathName, String fileName) {
        File file = new File(pathName + fileName);
        try {
            FileWriter fw = new FileWriter(file);
            fw.flush();
        } catch (IOException e) {
            System.out.println("An flush IO error has occurred: " + e.getMessage());
        }
    }

    /**
     * Creates the txt file or the folder.
     *
     * @param pathName The pathname of the txt file, containing the directory name.
     * @param fileName The filename of the txt file, containing the file name.
     */
    public static void createFileOrFolder(String pathName, String fileName) {
        boolean hasExist = false;
        try {
            Path path = Paths.get(pathName);
            Files.createDirectory(path);
        } catch (FileAlreadyExistsException ignored) {
            hasExist = true;
        } catch (IOException e) {
            System.out.println("An createFile IO error has occurred: " + e.getMessage());
        }

        try {
            Path file = Paths.get(pathName + fileName);
            Files.createFile(file);
        } catch (FileAlreadyExistsException ignored) {
            hasExist = true;
        } catch (IOException e) {
            System.out.println("An createFile IO error has occurred: " + e.getMessage());
        }
    }

    /**
     * Append text to the txt file.
     *
     * @param filePath     The whole pathname of the txt file.
     * @param textToAppend The string that is going to be appended to the txt file.
     */
    public static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend);
        fw.close();
    }
}
