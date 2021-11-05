package seedu.situs.storage;

import seedu.situs.Situs;
import seedu.situs.command.AlertExpiringSoonCommand;
import seedu.situs.command.AlertLowStockCommand;
import seedu.situs.exceptions.SitusException;
import seedu.situs.ingredients.Ingredient;
import seedu.situs.ingredients.IngredientGroup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private static final String DATA_FILE_PATH = "./data/ingredients.txt";
    private static final String THRESHOLDS_FILE_PATH = "./data/thresholds.txt";

    private static final String THRESHOLD_DATA_CORRUPTED_MESSAGE = "Threshold data is corrupted. "
            + "Setting to 5 days for expiry and 1.0 kg for stock";
    private static final String EXPIRY_THRESHOLD_CORRUPTED_MESSAGE = "Expiry threshold data corrupted. "
            + "Setting to 5 days";
    private static final String STOCK_THRESHOLD_CORRUPTED_MESSAGE = "Stock threshold data corrupted. "
            + "Setting to 1.0kg";

    private static final String INGREDIENT_DATA_CORRUPTED_MESSAGE = "Exit to fix the ingredients.txt file, or continue"
            + " by modifying the ingredient list,\n which will overwrite error content in the memory file.";

    private File dataFile;
    private File thresholdFile;

    //@@author datn02
    public Storage() throws IOException {
        File dataFile = new File(DATA_FILE_PATH);
        File thresholdFile = new File(THRESHOLDS_FILE_PATH);
        File dataDir = new File(dataFile.getParent());

        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }

        if (!dataFile.exists()) {
            dataFile.createNewFile();
        }

        if (!thresholdFile.exists()) {
            thresholdFile.createNewFile();
        }

        this.dataFile = dataFile;
        this.thresholdFile = thresholdFile;
    }

    /**
     * Loads the ingredients from memory.
     *
     * @return the list of ingredients loaded
     */
    public ArrayList<IngredientGroup> loadIngredientsFromMemory() {
        ArrayList<IngredientGroup> extractedIngredients = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(this.dataFile);
            int lineCounter = 0;

            while (scanner.hasNextLine()) {
                String memoryContent = scanner.nextLine();
                lineCounter++;

                if (!isValidIngredientLine(memoryContent)) {
                    System.out.println("Wrong ingredient format found in line " + lineCounter
                            + " of ingredients.txt\n"+ INGREDIENT_DATA_CORRUPTED_MESSAGE);
                    continue;
                }

                IngredientGroup ingredientGroup = readStoredIngredients(memoryContent);
                extractedIngredients.add(ingredientGroup);
            }
        } catch (FileNotFoundException e) {
            System.out.println("\tCannot open the saved memory file!");
        } catch (SitusException e) {
            System.out.println(e.getMessage());
        }

        return extractedIngredients;
    }

    /**
     * Reads the stored ingredient from the string saved in the memory file.
     *
     * @param savedIngredientString String representing the saved ingredient
     * @return the ingredient group
     * @throws SitusException if the saved data is of the wrong format
     */
    private IngredientGroup readStoredIngredients(String savedIngredientString) throws SitusException {
        try {

            String[] ingredientDetails = savedIngredientString.split("\\|");
            IngredientGroup ingredientGroup = new IngredientGroup();

            String ingredientGroupName = ingredientDetails[0].trim();
            ingredientGroup.setIngredientGroupName(ingredientGroupName);

            if (ingredientDetails.length <= 1) {
                throw new SitusException("Invalid ingredient format!");
            }

            for (int i = 1; i < ingredientDetails.length; i++) {
                String[] amountAndExpiry = ingredientDetails[i].split("%");

                if (amountAndExpiry.length < 2) {
                    throw new SitusException("Wrong ingredient format found!\n" + INGREDIENT_DATA_CORRUPTED_MESSAGE);
                }

                double ingredientAmount = Double.parseDouble(amountAndExpiry[0]);
                LocalDate ingredientExpiry = Ingredient.stringToDate(amountAndExpiry[1]);

                ingredientGroup.add(new Ingredient(ingredientGroupName, ingredientAmount, ingredientExpiry));
            }

            return ingredientGroup;
        } catch (NumberFormatException e) {
            throw new SitusException("Wrong ingredient amount format!\n" + INGREDIENT_DATA_CORRUPTED_MESSAGE);
        } catch (DateTimeParseException e) {
            throw new SitusException("Wrong expiry date format!\n" + INGREDIENT_DATA_CORRUPTED_MESSAGE);
        }
    }

    /**
     * Checks to see the current line of memory file is a correct ingredient format or not.
     *
     * @param savedIngredientString the current string line of the memory file
     * @return true if it is a correct format, false otherwise
     */
    private boolean isValidIngredientLine(String savedIngredientString) {
        try {
            String[] ingredientDetails = savedIngredientString.split("\\|");
            String ingredientGroupName = ingredientDetails[0].trim();

            if (ingredientDetails.length <= 1) {
                return false;
            }

            for (int i = 1; i < ingredientDetails.length; i++) {
                String[] amountAndExpiry = ingredientDetails[i].split("%");

                if (amountAndExpiry.length < 2) {
                    return false;
                }

                double ingredientAmount = Double.parseDouble(amountAndExpiry[0]);
                LocalDate ingredientExpiry = Ingredient.stringToDate(amountAndExpiry[1]);

            }
        } catch (NumberFormatException e) {
            return false;
        } catch (DateTimeParseException e) {
            return false;
        }

        return true;
    }

    //@@author mudkip8

    /**
     * Gets the threshold data from the storage file.
     *
     * @return threshold data
     */
    public String getThresholdData() {
        String thresholdData = "5|1.0";
        try {
            Scanner scanner = new Scanner(this.thresholdFile);
            if (scanner.hasNextLine()) {
                thresholdData = scanner.nextLine();
            }
        } catch (FileNotFoundException e) {
            return thresholdData;
        }

        return thresholdData;
    }

    /**
     * Loads the expiry threshold.
     *
     * @return the expiry threshold
     */
    public long loadExpiryThreshold() throws SitusException, IOException {
        String[] thresholdData = getThresholdData().split("\\|");
        if (thresholdData.length != 2) {
            writeThresholdData();
            throw new SitusException(THRESHOLD_DATA_CORRUPTED_MESSAGE);
        }
        long expiryThreshold;
        try {
            expiryThreshold = Long.parseLong(thresholdData[0]);
        } catch (NumberFormatException e) {
            writeThresholdData();
            throw new SitusException(EXPIRY_THRESHOLD_CORRUPTED_MESSAGE);
        }
        return expiryThreshold;
    }

    /**
     * Loads the stock threshold.
     *
     * @return the stock threshold
     */
    public double loadStockThreshold() throws SitusException, IOException {
        String[] thresholdData = getThresholdData().split("\\|");
        if (thresholdData.length != 2) {
            writeThresholdData();
            throw new SitusException(THRESHOLD_DATA_CORRUPTED_MESSAGE);
        }
        double stockThreshold;
        try {
            stockThreshold = Double.parseDouble(thresholdData[1]);
        } catch (NumberFormatException e) {
            writeThresholdData();
            throw new SitusException(STOCK_THRESHOLD_CORRUPTED_MESSAGE);
        }
        return stockThreshold;
    }

    /**
     * Writes the threshold data to memory. Code adapted from
     * <a href="https://coderedirect.com/questions/564153/how-to-replace-a-specific-line-in-a-file-using-java">
     * Code Redirect</a>
     *
     * @throws IOException when error with file
     */
    public void writeThresholdData() throws IOException {
        FileWriter fw = new FileWriter(THRESHOLDS_FILE_PATH);
        String thresholdData = convertThresholdDataForStorage();
        fw.write(thresholdData + System.lineSeparator());
        fw.close();
    }

    /**
     * Converts threshold data to a string for storage.
     *
     * @return string of threshold data
     */
    public String convertThresholdDataForStorage() {
        long expiryThreshold = AlertExpiringSoonCommand.getExpiryThreshold();
        double stockThreshold = AlertLowStockCommand.getLowStockThreshold();
        return expiryThreshold + "|" + stockThreshold;
    }

    //@@author datn02

    /**
     * Writes the contents of the ingredient list to memory.
     *
     * @param ingredientGroups List of the ingredient groups
     * @throws IOException               when error with file
     * @throws IndexOutOfBoundsException if reading from non-existent ingredient group
     */
    public void writeIngredientsToMemory(ArrayList<IngredientGroup> ingredientGroups) throws IOException,
            IndexOutOfBoundsException {
        FileWriter fw = new FileWriter(DATA_FILE_PATH);

        for (IngredientGroup ig : ingredientGroups) {
            String dataToWrite = ig.getIngredientGroupName() + "|";

            for (int i = 1; i <= ig.getIngredientGroupSize(); i++) {
                dataToWrite += String.format("%.3f", ig.get(i).getAmount()) + "%"
                        + Ingredient.dateToString(ig.get(i).getExpiry()) + "|";
            }

            dataToWrite += System.lineSeparator();
            fw.write(dataToWrite);
        }

        fw.close();
    }

}
