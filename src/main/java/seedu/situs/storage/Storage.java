package seedu.situs.storage;

import seedu.situs.exceptions.DukeException;
import seedu.situs.ingredients.Ingredient;
import seedu.situs.ingredients.IngredientGroup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private static final String DATA_FILE_PATH = "./data/ingredients.txt";
    private File dataFile;

    public Storage() throws IOException {
        File dataFile = new File(DATA_FILE_PATH);
        File dataDir = new File(dataFile.getParent());

        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }

        if (!dataFile.exists()) {
            dataFile.createNewFile();
        }

        this.dataFile = dataFile;
    }

    /**
     * Checks for the existence of the {@code data} folder, and creates one if it does not exist.
     *
     * @return loadedTaskList The Tasks read from the data file.
     */
    public ArrayList<Ingredient> loadIngredientsFromMemory() {
        ArrayList<Ingredient> extractedIngredients = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(this.dataFile);

            while (scanner.hasNextLine()) {
                Ingredient ingredient = readStoredIngredients(scanner.nextLine());
                extractedIngredients.add(ingredient);
            }
        } catch (FileNotFoundException e) {
            System.out.println("\tCannot open the saved memory file!");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

        return extractedIngredients;
    }



    private Ingredient readStoredIngredients(String savedIngredientString) throws DukeException {
        try {
            String[] ingredientDetails = savedIngredientString.split("\\|", 4);
            String ingredientName = ingredientDetails[0].trim();
            double ingredientAmount = Double.parseDouble(ingredientDetails[1].trim());
            LocalDate ingredientExpiry = Ingredient.stringToDate(ingredientDetails[2].trim());
            return new Ingredient(ingredientName, ingredientAmount, ingredientExpiry);
        } catch (NumberFormatException e) {
            throw new DukeException("Wrong ingredient amount format!");
        } catch (DateTimeParseException e) {
            throw new DukeException("Wrong expiry date format!");
        }
    }

    /*
    public void writeIngredientsToMemory(ArrayList<Ingredient> ingredients) throws IOException {
        FileWriter fw = new FileWriter(DATA_FILE_PATH);
        for (Ingredient i : ingredients) {
            fw.write(i.getName() + "|" + i.getAmount() + "|"
                    + Ingredient.dateToString(i.getExpiry()) + System.lineSeparator());
        }
        fw.close();
    }
    */

    public void writeIngredientsToMemory(ArrayList<IngredientGroup> ingredientGroups) throws IOException,
            IndexOutOfBoundsException {
        FileWriter fw = new FileWriter(DATA_FILE_PATH);

        for (IngredientGroup ig : ingredientGroups) {
            String dataToWrite = ig.getIngredientGroupName() + '|' + ig.getTotalAmount() + '|';

            for (int i = 1; i <= ig.getIngredientGroupSize(); i++) {
                dataToWrite += ig.get(i).getAmount() + '%' + Ingredient.dateToString(ig.get(i).getExpiry()) + '|';
            }

            dataToWrite += System.lineSeparator();
            fw.write(dataToWrite);
        }

        fw.close();
    }
}
