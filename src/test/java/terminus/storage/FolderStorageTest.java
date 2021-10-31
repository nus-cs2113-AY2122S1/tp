package terminus.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.TestFilePath;
import terminus.content.ContentManager;
import terminus.content.Note;
import terminus.exception.InvalidFileException;
import terminus.module.ModuleManager;
import terminus.module.NusModule;

public class FolderStorageTest {

    private FolderStorage folderStorage;

    private String tempModule = "test".toUpperCase();

    @BeforeEach
    void setup() {
        this.folderStorage = new FolderStorage(TestFilePath.RESOURCE_FOLDER);
    }

    @AfterEach
    void reset() throws InvalidFileException {
        Path folderPath = folderStorage.getAppendPath(TestFilePath.RESOURCE_FOLDER, tempModule);
        folderStorage.delete(folderPath);
    }

    @Test
    void createModuleFolder_success() throws InvalidFileException {
        Path folderPath = folderStorage.getAppendPath(TestFilePath.RESOURCE_FOLDER, tempModule);
        folderStorage.createModuleFolder(tempModule);
        assertTrue(Files.exists(folderPath));
    }

    @Test
    void createModuleFolder_invalidInput_exceptionThrown() {
        assertThrows(InvalidFileException.class, () -> folderStorage.createModuleFolder(null));
    }

    @Test
    void deleteModuleFolder_success() throws InvalidFileException {
        Path folderPath = folderStorage.getAppendPath(TestFilePath.RESOURCE_FOLDER, tempModule);
        folderStorage.createFolder(folderPath);
        assertTrue(Files.exists(folderPath));
        folderStorage.deleteModuleFolder(tempModule);
        assertFalse(Files.exists(folderPath));
    }

    @Test
    void deleteModuleFolder_nullInput_exceptionThrown() {
        assertThrows(InvalidFileException.class, () -> folderStorage.deleteModuleFolder(null));
    }

    @Test
    void renameModuleFolder_success() throws InvalidFileException {
        Path folderPath = folderStorage.getAppendPath(TestFilePath.RESOURCE_FOLDER, tempModule);
        Path newFolderPath = folderStorage.getAppendPath(TestFilePath.RESOURCE_FOLDER, "NEWTEST");
        folderStorage.createFolder(folderPath);
        assertTrue(Files.exists(folderPath));
        assertFalse(Files.exists(newFolderPath));
        folderStorage.renameModuleFolder("NEWTEST", tempModule);
        assertFalse(Files.exists(folderPath));
        assertTrue(Files.exists(newFolderPath));
        folderStorage.delete(newFolderPath);
    }

    @Test
    void execute_success() throws InvalidFileException {
        folderStorage.execute(tempModule, null, StorageActionEnum.CREATE);
        folderStorage.execute(tempModule, tempModule, StorageActionEnum.UPDATE);
        folderStorage.execute(null, tempModule, StorageActionEnum.DELETE);
    }

    @Test
    void execute_invalidAction_exceptionThrown() {
        assertThrows(InvalidFileException.class, () -> folderStorage.execute(tempModule, null,
                StorageActionEnum.EXPORT));
    }

}
