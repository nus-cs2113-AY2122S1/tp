package happybit.storage;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StorageTest {
    private static final String FILEDIR = "test/dir";
    private static final String FILEPATH = "test/dir/testFile.txt";

    @Test
    public void createStorageFileTest() {
        new Storage().createStorageFile(FILEPATH, FILEDIR);

        File fileDir = new File(FILEDIR);
        File filePath = new File(FILEPATH);

        assertTrue(fileDir.exists());
        assertTrue(filePath.exists());
        assertFalse(filePath.canWrite());
    }
}
