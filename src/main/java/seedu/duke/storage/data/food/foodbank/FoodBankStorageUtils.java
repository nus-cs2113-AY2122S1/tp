package seedu.duke.storage.data.food.foodbank;

import seedu.duke.data.item.ItemBank;
import seedu.duke.storage.StorageUtils;
import seedu.duke.storage.data.ItemBankDecoder;
import seedu.duke.storage.data.ItemEncoder;
import seedu.duke.storage.exceptions.UnableToReadFileException;
import seedu.duke.storage.exceptions.UnableToWriteFileException;
import seedu.duke.storage.utilities.FileChecker;
import seedu.duke.storage.utilities.FileSaver;

import java.io.IOException;
import java.util.logging.Level;

/**
 * A Storage class that handles the saving and loading of the FoodBank.
 */
public class FoodBankStorageUtils extends StorageUtils implements FoodBankStorage {

    public static final String TYPE = "Food";

    /**
     * Constructs the food bank storage handler with its respective path.
     *
     * @param path the directory to save the food bank file
     */
    public FoodBankStorageUtils(String path) {
        this.filePath = path;
        this.fileName = getFileName(path);
    }

    @Override
    public ItemBank loadFoodBank() throws UnableToReadFileException {
        try {
            FileChecker.createFileIfMissing(filePath);
            return ItemBankDecoder.retrieveDataFromItemBank(filePath, TYPE);
        } catch (IOException e) {
            logger.log(Level.FINE, "The path is missing ", filePath);
            throw new UnableToReadFileException(filePath);
        }
    }

    @Override
    public void saveFoodBank(ItemBank foodBank) throws UnableToWriteFileException {
        FileSaver.saveTo(filePath, ItemEncoder.encode(foodBank));
    }
}
