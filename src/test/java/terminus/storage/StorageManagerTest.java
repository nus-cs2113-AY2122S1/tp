package terminus.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.TestFilePath;
import terminus.command.CommandResult;
import terminus.content.ContentManager;
import terminus.content.Note;
import terminus.exception.InvalidFileException;
import terminus.module.ModuleManager;

public class StorageManagerTest {

    private StorageManager storageManager;
    private ModuleManager moduleManager;

    private String tempModule = "TEST";

    public static final Path RESOURCE_FOLDER = TestFilePath.RESOURCE_FOLDER;
    public static final String SAVE_FILE = TestFilePath.SAVE_FILE;
    public static final String MALFORMED_FILE = TestFilePath.MALFORMED_FILE;
    public static final String VALID_FILE = TestFilePath.VALID_FILE;
    public static final String FAULTY_FOLDER = TestFilePath.FAULTY_FOLDER;

    @BeforeEach
    void setup() {
        this.storageManager = new StorageManager(RESOURCE_FOLDER, VALID_FILE);
        this.moduleManager = new ModuleManager();
    }

    @Test
    void executeCommandResult_success() throws InvalidFileException {
        storageManager.setDisabled(true);
        CommandResult result = new CommandResult();
        storageManager.executeCommandResult(moduleManager, result);
    }

    @Test
    void executeCommandResult_folder_success() throws InvalidFileException {
        CommandResult result = new CommandResult(null, StorageActionEnum.DELETE, StorageTypeEnum.FOLDER, "");
        result.setDeletedItemName(tempModule);
        storageManager.executeCommandResult(moduleManager, result);
    }

    @Test
    void executeCommandResult_text_success() throws InvalidFileException {
        CommandResult result = new CommandResult(tempModule, StorageActionEnum.DELETE, StorageTypeEnum.TEXT, "");
        result.setDeletedItemName("a");
        storageManager.executeCommandResult(moduleManager, result);
    }

    @Test
    void executeCommandResult_pdf_success() throws InvalidFileException, IOException {
        CommandResult result = new CommandResult(tempModule, StorageActionEnum.EXPORT, StorageTypeEnum.PDF, "");
        moduleManager.addModule(tempModule);
        ContentManager<Note> noteContentManager = moduleManager.getModule(tempModule).getContentManager(Note.class);
        noteContentManager.add(new Note("a", "a"));
        storageManager.executeCommandResult(moduleManager, result);
        Path filepath = RESOURCE_FOLDER.resolve(tempModule + ".pdf");
        assertTrue(Files.exists(filepath));
        Files.delete(filepath);
    }

    @Test
    void executeCommandResult_invalidType() throws InvalidFileException {
        CommandResult result = new CommandResult("", StorageActionEnum.RELOAD, StorageTypeEnum.TEST, "");
        assertThrows(InvalidFileException.class, () -> storageManager.executeCommandResult(moduleManager, result));
    }

    @Test
    void executeCommandResult_nullModuleManager_exceptionThrown() {
        CommandResult result = new CommandResult();
        assertThrows(InvalidFileException.class, () -> storageManager.executeCommandResult(null, result));
    }

    @Test
    void updateMainJsonFile_success() throws InvalidFileException {
        this.storageManager = new StorageManager(RESOURCE_FOLDER, SAVE_FILE);
        storageManager.updateMainJsonFile(moduleManager);
    }

    @Test
    void initialize_success() throws InvalidFileException, IOException {
        Path folderPath = RESOURCE_FOLDER.resolve(tempModule);
        Path filepath = folderPath.resolve("test.json");
        this.storageManager = new StorageManager(folderPath, "test.json");
        assertFalse(Files.exists(folderPath));
        assertFalse(Files.exists(filepath));
        storageManager.initialize();
        assertTrue(Files.exists(folderPath));
        assertTrue(Files.exists(filepath));
        Files.deleteIfExists(filepath);
        Files.deleteIfExists(folderPath);
    }

    @Test
    void initialize_load_success() throws InvalidFileException {
        moduleManager.addModule(tempModule);
        ContentManager<Note> noteContentManager = moduleManager.getModule(tempModule).getContentManager(Note.class);
        noteContentManager.add(new Note("a", "a"));
        Path folderPath = RESOURCE_FOLDER.resolve(tempModule);
        Path filepath = folderPath.resolve("test.json");
        Storage storage = new Storage();
        storage.createFolder(folderPath);
        storage.createFile(filepath);
        this.storageManager = new StorageManager(folderPath, "test.json");
        JsonStorage jsonStorage = new JsonStorage(folderPath,"test.json");
        jsonStorage.saveJson(moduleManager);
        storageManager.initialize();
        storage.delete(folderPath);
    }

    @Test
    void save_success() throws InvalidFileException {
        this.storageManager = new StorageManager(RESOURCE_FOLDER, SAVE_FILE);
        this.storageManager.save(moduleManager);
    }

    @Test
    void save_nullInput_exceptionThrown() {
        assertThrows(InvalidFileException.class, () -> this.storageManager.save(null));
    }



}
