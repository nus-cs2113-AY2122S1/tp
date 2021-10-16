package seedu.duke.storage;

import org.junit.jupiter.api.Test;
import seedu.duke.storage.exceptions.StorageException;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class StorageTest {
    @Test
    public void getPath_validFileName_pathReturned() {
        try {
            Storage storage = new Storage();
            Path pathTask = storage.getPath("tasks.txt");
            Path pathLesson = storage.getPath("lessons.txt");
            Path pathModule = storage.getPath("modules.txt");
            assertNotNull(pathTask);
            assertNotNull(pathLesson);
            assertNotNull(pathModule);
        } catch (StorageException e) {
            fail();
        }
    }

    @Test
    public void getPath_invalidFileName_exceptionThrown() {
        assertThrows(StorageException.class, () -> {
            Storage storage = new Storage();
            storage.getPath("invalid.txt");
        });
    }
}
