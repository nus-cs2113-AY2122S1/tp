package seedu.duke.storage.data.food.foodlist;

import seedu.duke.data.item.food.FoodList;
import seedu.duke.storage.StorageUtils;
import seedu.duke.storage.data.ItemEncoder;
import seedu.duke.storage.exceptions.UnableToReadFileException;
import seedu.duke.storage.exceptions.UnableToWriteFileException;
import seedu.duke.storage.utilities.FileChecker;
import seedu.duke.storage.utilities.FileSaver;

import java.io.IOException;
import java.util.logging.Level;

/**
 * Storage that handles the saving and loading of data files of upcoming exercises in future exercise storage.
 */
public class FoodListStorageUtils extends StorageUtils implements FoodListStorage {

    /**
     * Constructor for food list storage object.
     *
     * @param filePath of where the food list should be saved
     */
    public FoodListStorageUtils(String filePath) {
        this.filePath = filePath;
        this.fileName = getFileName(filePath);
    }

    @Override
    public FoodList loadFoodList() throws UnableToReadFileException {
        try {
            FileChecker.createFileIfMissing(filePath);
            return FoodListDecoder.retrieveFoodListFromData(filePath);
        } catch (IOException e) {
            logger.log(Level.FINE, "The path is missing ", filePath);
            throw new UnableToReadFileException(fileName);
        }
    }

    @Override
    public void saveFoodList(FoodList foodItems) throws UnableToWriteFileException {
        FileSaver.saveTo(filePath, ItemEncoder.encode(foodItems));
    }
}
