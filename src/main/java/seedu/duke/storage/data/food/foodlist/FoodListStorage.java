package seedu.duke.storage.data.food.foodlist;

import seedu.duke.data.item.food.FoodList;
import seedu.duke.storage.exceptions.UnableToReadFileException;
import seedu.duke.storage.exceptions.UnableToWriteFileException;

/**
 * Interface that ensures both the storage and storage manager has the
 * required functions to load and save from food list.
 */
public interface FoodListStorage {

    /**
     * Load food items into a FoodList object.
     * Used when the selected profile is accessed and its respective ExerciseList is loaded.
     *
     * @return FoodList object with the details from the storage file
     * @throws UnableToReadFileException If the file is inaccessible or due to environment variables
     */
    FoodList loadFoodList() throws UnableToReadFileException;

    /**
     * Saves the food items into the respective food list data file.
     *
     * @param foodItems food items to be saved to the storage file
     * @throws UnableToWriteFileException If the file is inaccessible or due to environment variables
     */
    void saveFoodList(FoodList foodItems) throws UnableToWriteFileException;
}
