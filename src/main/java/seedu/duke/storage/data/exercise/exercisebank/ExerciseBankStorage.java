package seedu.duke.storage.data.exercise.exercisebank;

import seedu.duke.data.item.ItemBank;
import seedu.duke.storage.exceptions.UnableToReadFileException;
import seedu.duke.storage.exceptions.UnableToWriteFileException;

/**
 * Interface that ensures both the storage and storage manager has the
 * required functions to load and save from the exercise bank.
 */
public interface ExerciseBankStorage {

    /**
     * Loads the exercise bank from the file.
     *
     * @return ItemBank object with the loaded bank items from the data file.
     * @throws UnableToReadFileException If the file is inaccessible or due to environment variables
     */
    ItemBank loadExerciseBank() throws UnableToReadFileException;

    /**
     * Saves the exercise bank to the file.
     *
     * @throws UnableToReadFileException If the file is inaccessible or due to environment variables
     */
    void saveExerciseBank(ItemBank exerciseBank) throws UnableToWriteFileException;

}
