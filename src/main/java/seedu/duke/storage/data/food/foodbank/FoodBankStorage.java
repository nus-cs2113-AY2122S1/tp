package seedu.duke.storage.data.food.foodbank;

import seedu.duke.data.item.ItemBank;
import seedu.duke.storage.exceptions.UnableToReadFileException;
import seedu.duke.storage.exceptions.UnableToWriteFileException;

/**
 * Interface that ensures the storage device has a load and save food bank method.
 */
public interface FoodBankStorage {

    /**
     * Loads the food bank file from data storage.
     *
     * @return FoodBank object from data storage
     * @throws UnableToReadFileException if the filepath given is inaccessible or I/O was interrupted
     */
    ItemBank loadFoodBank() throws UnableToReadFileException;

    /**
     * Saves the Food Bank into storage.
     * Used when there is an update to the Food Bank.
     *
     * @param foodBank FoodBank that is to be saved
     */
    void saveFoodBank(ItemBank foodBank) throws UnableToWriteFileException;
}
