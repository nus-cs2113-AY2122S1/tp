package terminus.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.TestFilePath;
import terminus.content.Link;
import terminus.exception.InvalidFileException;
import terminus.module.ModuleManager;

public class JsonStorageTest {

    private JsonStorage jsonStorage;
    private ModuleManager moduleManager;

    public static final Path RESOURCE_FOLDER = TestFilePath.RESOURCE_FOLDER;
    public static final String SAVE_FILE = TestFilePath.SAVE_FILE;
    public static final String MALFORMED_FILE = TestFilePath.MALFORMED_FILE;
    public static final String VALID_FILE = TestFilePath.VALID_FILE;
    public static final String FAULTY_FOLDER = TestFilePath.FAULTY_FOLDER;

    @BeforeEach
    void setup() {
        this.jsonStorage = new JsonStorage(RESOURCE_FOLDER, VALID_FILE);
        this.moduleManager = new ModuleManager();
    }

    @Test
    void createJson_success() throws InvalidFileException {
        Path filePath = jsonStorage.getAppendPath(RESOURCE_FOLDER, "test.json");
        jsonStorage.setJsonFileName("test.json");
        assertFalse(Files.exists(filePath));
        jsonStorage.createJson();
        assertTrue(Files.exists(filePath));
        jsonStorage.delete(filePath);
    }

    @Test
    void createJson_invalidFile_exceptionThrown() {
        jsonStorage.setJsonFileName(null);
        assertThrows(InvalidFileException.class, () -> jsonStorage.createJson());
    }

    @Test
    void loadJson_success() throws InvalidFileException {
        ModuleManager moduleManager = jsonStorage.loadJson();
        assertNotEquals(null, moduleManager);
    }

    @Test
    void loadJson_malformedJson_exceptionThrown() {
        jsonStorage.setJsonFileName(MALFORMED_FILE);
        assertThrows(InvalidFileException.class, () -> jsonStorage.loadJson());
    }

    @Test
    void saveJson_success() throws InvalidFileException, IOException {
        String tempModule = "test".toUpperCase();
        moduleManager.addModule(tempModule);
        moduleManager.getModule(tempModule).getContentManager(Link.class).add(new Link("test", "tuesday",
                LocalTime.of(11, 11), 2, "https://zoom.us/"));
        jsonStorage.setJsonFileName(SAVE_FILE);
        jsonStorage.saveJson(moduleManager);
        assertTextFilesEqual(RESOURCE_FOLDER.resolve(SAVE_FILE), RESOURCE_FOLDER.resolve(VALID_FILE));
    }

    @Test
    void saveJson_nullInput_exceptionThrown() {
        assertThrows(AssertionError.class, () -> jsonStorage.saveJson(null));
    }

    @Test
    void execute_success() throws InvalidFileException {
        String tempModule = "test".toUpperCase();
        moduleManager.addModule(tempModule);
        moduleManager.getModule(tempModule).getContentManager(Link.class).add(new Link("test", "tuesday",
                LocalTime.of(11, 11), 2, "https://zoom.us/"));
        jsonStorage.setJsonFileName(SAVE_FILE);
        jsonStorage.execute(moduleManager, StorageActionEnum.CREATE);
        jsonStorage.execute(moduleManager, StorageActionEnum.UPDATE);
    }

    @Test
    void execute_invalidAction_exceptionThrown() {
        assertThrows(InvalidFileException.class, () -> jsonStorage.execute(moduleManager, StorageActionEnum.DELETE));
    }

    /**
     * Asserts whether the text in the two given files are the same. Ignores any differences in line endings. Taken
     * from: https://github.com/se-edu/addressbook-level2/blob/master/test/java/seedu/addressbook/util/TestUtil.java#L128
     */
    public static void assertTextFilesEqual(Path path1, Path path2) throws IOException {
        List<String> list1 = Files.readAllLines(path1);
        List<String> list2 = Files.readAllLines(path2);
        assertEquals(String.join("\n", list1), String.join("\n", list2));
    }
}
