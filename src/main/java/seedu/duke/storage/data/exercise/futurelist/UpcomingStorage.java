package seedu.duke.storage.data.exercise.futurelist;

import seedu.duke.data.item.exercise.FutureExerciseList;
import seedu.duke.storage.exceptions.UnableToReadFileException;
import seedu.duke.storage.exceptions.UnableToWriteFileException;

/**
 * Interface that ensures both the storage and storage manager has the
 * required functions to load and save from the future exercise list in storage.
 */
public interface UpcomingStorage {

    /**
     * Loads the future exercise list from storage.
     *
     * @return the future exercise list with data loaded in
     * @throws UnableToReadFileException If the file is inaccessible or due to environment variables
     */
    FutureExerciseList loadFutureExerciseList() throws UnableToReadFileException;

    /**
     * Saves the future exercise list into storage.
     *
     * @param futureExercises the future exercises to be saved to data
     * @throws UnableToWriteFileException If the file is inaccessible or due to environment variables
     */
    void saveFutureExerciseList(FutureExerciseList futureExercises) throws UnableToWriteFileException;

}
