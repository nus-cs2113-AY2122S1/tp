package seedu.situs.storage;

import seedu.situs.exceptions.SitusException;
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

    public ArrayList<IngredientGroup> loadIngredientsFromMemory() {
        ArrayList<IngredientGroup> extractedIngredients = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(this.dataFile);

            while (scanner.hasNextLine()) {
                IngredientGroup ingredientGroup = readStoredIngredients(scanner.nextLine());
                extractedIngredients.add(ingredientGroup);
            }
        } catch (FileNotFoundException e) {
            System.out.println("\tCannot open the saved memory file!");
        } catch (SitusException e) {
            System.out.println(e.getMessage());
        }

        return extractedIngredients;
    }



    private IngredientGroup readStoredIngredients(String savedIngredientString) throws SitusException {
        try {

            String[] ingredientDetails = savedIngredientString.split("\\|");
            IngredientGroup ingredientGroup = new IngredientGroup();

            String ingredientGroupName = ingredientDetails[0].trim();
            ingredientGroup.setIngredientGroupName(ingredientGroupName);

            for (int i = 1; i < ingredientDetails.length; i++) {
                String[] amountAndExpiry = ingredientDetails[i].split("%");
                double ingredientAmount = Double.parseDouble(amountAndExpiry[0]);
                LocalDate ingredientExpiry = Ingredient.stringToDate(amountAndExpiry[1]);

                ingredientGroup.add(new Ingredient(ingredientGroupName, ingredientAmount, ingredientExpiry));
            }

            return ingredientGroup;
        } catch (NumberFormatException e) {
            throw new SitusException("Wrong ingredient amount format!");
        } catch (DateTimeParseException e) {
            throw new SitusException("Wrong expiry date format!");
        }
    }


    public void writeIngredientsToMemory(ArrayList<IngredientGroup> ingredientGroups) throws IOException,
            IndexOutOfBoundsException {
        FileWriter fw = new FileWriter(DATA_FILE_PATH);

        for (IngredientGroup ig : ingredientGroups) {
            String dataToWrite = ig.getIngredientGroupName() + "|";

            for (int i = 1; i <= ig.getIngredientGroupSize(); i++) {
                dataToWrite += ig.get(i).getAmount() + "%" + Ingredient.dateToString(ig.get(i).getExpiry()) + "|";
            }

            dataToWrite += System.lineSeparator();
            fw.write(dataToWrite);
        }

        fw.close();
    }
}
