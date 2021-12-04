package seedu.duke.storage.data.exercise.exercisebank;

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
 * This storage handles the loading and saving of exercise bank items.
 */
public class ExerciseBankStorageUtils extends StorageUtils implements ExerciseBankStorage {

    public static final String TYPE = "Exercise";

    /**
     * Constructor for the exercise bank storage.
     *
     * @param filePath of the exercise bank data file
     */
    public ExerciseBankStorageUtils(String filePath) {
        this.filePath = filePath;
        fileName = getFileName(filePath);
    }

    @Override
    public ItemBank loadExerciseBank() throws UnableToReadFileException {
        try {
            FileChecker.createFileIfMissing(filePath);
            return ItemBankDecoder.retrieveDataFromItemBank(filePath, TYPE);
        } catch (IOException e) {
            logger.log(Level.FINE, "The path is missing ", filePath);
            throw new UnableToReadFileException(filePath);
        }
    }

    @Override
    public void saveExerciseBank(ItemBank exerciseBank) throws UnableToWriteFileException {
        FileSaver.saveTo(filePath, ItemEncoder.encode(exerciseBank));
    }
}
