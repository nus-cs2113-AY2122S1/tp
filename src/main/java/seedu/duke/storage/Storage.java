package seedu.duke.storage;

import seedu.duke.ingredients.Ingredient;
import seedu.duke.ingredients.IngredientList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private ArrayList<Ingredient> loadedIngredientList = new ArrayList<>();

    public Storage() {
    }

    /**
     * Checks for the existence of the {@code data} folder, and creates one if it does not exist
     *
     * @return loadedTaskList The Tasks read from the data file
     * @throws FileNotFoundException if the {@code data} folder cannot be found
     */
    public ArrayList<Ingredient> load() throws FileNotFoundException {
        File taskFile = new File("data");
        boolean isDir = Files.isDirectory(Path.of(taskFile.getAbsolutePath()));
        if (!isDir) {
            taskFile.mkdir();
            return loadedIngredientList;
        }
        readFile();
        return loadedIngredientList;
    }

    /**
     * Reads the contents of the {@code tasks} file
     *
     * @throws FileNotFoundException if the {@code tasks} file does not exist
     */
    public void readFile() throws FileNotFoundException {
        File taskFile = new File("data/ingredients.txt");
        Scanner scanner = new Scanner(taskFile);
        if (taskFile.exists() && !taskFile.isDirectory()) {
            while (scanner.hasNext()) {
                processStoredIngredients(scanner.nextLine());
            }
        }
    }

    public void processStoredIngredients(String savedIngredientString) {
        String[] ingredientDetails = savedIngredientString.split("\\|", 3);
        String ingredientName = ingredientDetails[0];

    }

    public static void save(IngredientList ingredientList) throws IOException {
        FileWriter fw = new FileWriter("data/tasks.txt");
        for (Ingredient i : ingredientList.getInstance()) {
            fw.write(t.toString() + System.lineSeparator());
        }
        fw.close();
    }
}
