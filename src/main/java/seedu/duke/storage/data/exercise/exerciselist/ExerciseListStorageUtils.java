package seedu.duke.storage.data.exercise.exerciselist;

import seedu.duke.data.item.exercise.ExerciseList;
import seedu.duke.storage.StorageUtils;
import seedu.duke.storage.data.ItemEncoder;
import seedu.duke.storage.exceptions.UnableToReadFileException;
import seedu.duke.storage.exceptions.UnableToWriteFileException;
import seedu.duke.storage.utilities.FileChecker;
import seedu.duke.storage.utilities.FileSaver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;

/**
 * This storage handles the loading and saving of exercise list items.
 */
public class ExerciseListStorageUtils extends StorageUtils implements ExerciseListStorage {

    /**
     * Constructor for exercise list storage object.
     *
     * @param filePath of where the exercise list should be stored.
     */
    public ExerciseListStorageUtils(String filePath) {
        this.filePath = filePath;
        this.fileName = getFileName(filePath);
    }

    @Override
    public ExerciseList loadExerciseList() throws UnableToReadFileException {
        try {
            FileChecker.createFileIfMissing(filePath);
            return ExerciseListDecoder.retrieveExerciseListFromData(filePath);
        } catch (IOException e) {
            throw new UnableToReadFileException(filePath);
        }
    }

    @Override
    public void saveExerciseList(ExerciseList exercises) throws UnableToWriteFileException {
        FileSaver.saveTo(filePath, ItemEncoder.encode(exercises));
    }
}
