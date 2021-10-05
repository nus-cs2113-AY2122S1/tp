package seedu.duke.storage;

import seedu.duke.exception.GetJackDException;

import java.io.File;
import java.io.IOException;

/**
 * To deal with loading tasks from the json file and saving tasks in the json file.
 */
public class Storage {
    private final String STORAGE_PATH = "data/workouts.json";
    private final File file = new File(STORAGE_PATH);
    private final String FILE_PATH = file.getAbsolutePath();

    public Storage() throws GetJackDException {
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException e) {
            throw new GetJackDException("☹ OOPS!!! Data file can't be found.");
        }
    }
}
