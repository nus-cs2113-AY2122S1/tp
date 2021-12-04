package seedu.duke.storage.data.exercise.exerciselist;

import seedu.duke.data.item.exercise.ExerciseList;
import seedu.duke.storage.exceptions.UnableToReadFileException;
import seedu.duke.storage.exceptions.UnableToWriteFileException;

/**
 * Interface that ensures both the storage and storage manager has the
 * required functions to load and save from exercise list storage.
 */
public interface ExerciseListStorage {

    /**
     * Load exercises into an ExerciseList object.
     * Used when the selected profile is accessed and its respective ExerciseList is loaded.
     *
     * @return ExerciseList object with the details from the storage file
     * @throws UnableToReadFileException If the file is inaccessible or due to environment variables
     */
    ExerciseList loadExerciseList() throws UnableToReadFileException;

    /**
     * Saves the exercises into storage.
     * Used when there is an update to the list.
     *
     * @param exercises ExerciseList to be saved
     * @throws UnableToReadFileException If the file is inaccessible or due to environment variables
     */
    void saveExerciseList(ExerciseList exercises) throws UnableToWriteFileException;

}
