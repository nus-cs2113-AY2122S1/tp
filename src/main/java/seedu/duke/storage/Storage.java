package seedu.duke.storage;

import seedu.duke.exceptions.DukeException;
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
     * Checks for the existence of the {@code data} folder, and creates one if it does not exist.
     *
     * @return loadedTaskList The Tasks read from the data file.
     * @throws DukeException if there is an error when reading the file.
     */
    public ArrayList<Ingredient> load() throws DukeException {
        File taskFile = new File("data");
        boolean isDir = Files.isDirectory(Path.of(taskFile.getAbsolutePath()));
        if (!isDir) {
            taskFile.mkdir();
            return loadedIngredientList;
        }
        try {
            readFile();
        } catch (FileNotFoundException | NumberFormatException e) {
            throw new DukeException("Error in reading file");
        }
        return loadedIngredientList;
    }

    /**
     * Reads the contents of the {@code tasks} file.
     *
     * @throws FileNotFoundException if the {@code tasks} file does not exist.
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

    public void processStoredIngredients(String savedIngredientString) throws NumberFormatException {
        String[] ingredientDetails = savedIngredientString.split("\\|", 4);
        String ingredientName = ingredientDetails[0].trim();
        double ingredientAmount = Double.parseDouble(ingredientDetails[1].trim());
        String ingredientUnits = ingredientDetails[2].trim();
        String ingredientExpiry = ingredientDetails[3].trim();
        loadedIngredientList.add(new Ingredient(ingredientName, ingredientAmount, ingredientUnits, ingredientExpiry));
    }

    public static void save() throws IOException {
        FileWriter fw = new FileWriter("data/tasks.txt");
        for (Ingredient i : IngredientList.getInstance().getIngredientList()) {
            fw.write(i.getName() + "|" + i.getAmount() + "|"
                    + i.getUnits() + "|" + i.getExpiry() + System.lineSeparator());
        }
        fw.close();
    }
}
