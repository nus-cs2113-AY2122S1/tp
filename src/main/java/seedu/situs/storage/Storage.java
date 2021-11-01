package seedu.situs.storage;

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
import java.util.List;
import java.util.Scanner;

public class Storage {

    private static final String DATA_FILE_PATH = "./data/ingredients.txt";
    private File dataFile;

    //@@author datn02
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
     * Loads the ingredients from memory.
     *
     * @return the list of ingredients loaded
     */
    public ArrayList<IngredientGroup> loadIngredientsFromMemory() {
        ArrayList<IngredientGroup> extractedIngredients = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(this.dataFile);

            if (scanner.hasNextLine()) { //to skip the first line, which is used for thresholds
                scanner.nextLine();
            }

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

    //@@author mudkip8

    /**
     * Gets the threshold data from the storage file.
     *
     * @return threshold data
     */
    public String getThresholdData() {
        String thresholdData = "5|1.0";
        try {
            Scanner scanner = new Scanner(this.dataFile);
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
    public long loadExpiryThreshold() {
        String[] thresholdData = getThresholdData().split("\\|");
        return Long.parseLong(thresholdData[0]);
    }

    /**
     * Loads the stock threshold.
     *
     * @return the stock threshold
     */
    public double loadStockThreshold() {
        String[] thresholdData = getThresholdData().split("\\|");

        return Double.parseDouble(thresholdData[1]);
    }

    /**
     * Writes the threshold data to memory. Code adapted from
     * <a href="https://coderedirect.com/questions/564153/how-to-replace-a-specific-line-in-a-file-using-java">
     *     Code Redirect</a>
     *
     *
     * @throws IOException when error with file
     */
    public void writeThresholdData() throws IOException {
        Path path = Paths.get(DATA_FILE_PATH);
        List<String> fileContents = Files.readAllLines(path);
        String thresholdData = convertThresholdDataForStorage();
        fileContents.set(0, thresholdData);
        Files.write(path, fileContents);
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
     * @throws IOException when error with file
     * @throws IndexOutOfBoundsException if reading from non-existent ingredient group
     */
    public void writeIngredientsToMemory(ArrayList<IngredientGroup> ingredientGroups) throws IOException,
            IndexOutOfBoundsException {
        FileWriter fw = new FileWriter(DATA_FILE_PATH);

        fw.write(convertThresholdDataForStorage() + System.lineSeparator());

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
