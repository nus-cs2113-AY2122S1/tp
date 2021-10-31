package terminus.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.gson.JsonSyntaxException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.TestFilePath;
import terminus.common.CommonFormat;
import terminus.content.ContentManager;
import terminus.content.Link;
import terminus.content.Note;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidFileException;
import terminus.module.ModuleManager;
import terminus.module.NusModule;

public class StorageTest {

    public static final Path RESOURCE_FOLDER = TestFilePath.RESOURCE_FOLDER;
    public static final String SAVE_FILE = TestFilePath.SAVE_FILE;
    public static final String MALFORMED_FILE = TestFilePath.MALFORMED_FILE;
    public static final String VALID_FILE = TestFilePath.VALID_FILE;

    private String tempModule = "test".toUpperCase();
    private Storage storage;

    @BeforeEach
    void setup() {
        this.storage = new Storage();
    }

    @Test
    void createFolder_success() throws InvalidFileException {
        Path folderPath = storage.getAppendPath(RESOURCE_FOLDER, tempModule);
        assertFalse(Files.exists(folderPath));
        storage.createFolder(folderPath);
        assertTrue(Files.exists(folderPath));
        storage.delete(folderPath);
    }

    @Test
    void createFolder_folderExists() throws InvalidFileException {
        Path folderPath = storage.getAppendPath(RESOURCE_FOLDER, tempModule);
        assertFalse(Files.exists(folderPath));
        storage.createFolder(folderPath);
        assertTrue(Files.exists(folderPath));
        storage.createFolder(folderPath);
        assertTrue(Files.exists(folderPath));
        storage.delete(folderPath);
    }

    @Test
    void createFolder_missingParentFolder() throws InvalidFileException {
        Path folderPath = storage.getAppendPath(RESOURCE_FOLDER, tempModule);
        Path secondFolderPath = storage.getAppendPath(folderPath, tempModule);
        assertFalse(Files.exists(folderPath));
        assertFalse(Files.exists(secondFolderPath));
        storage.createFolder(secondFolderPath);
        assertTrue(Files.exists(folderPath));
        assertTrue(Files.exists(secondFolderPath));
        storage.delete(folderPath);
    }

    @Test
    void createFolder_invalidFolderName_exceptionThrown() {
        assertThrows(InvalidFileException.class, () -> storage.createFolder(null));
    }

    @Test
    void createFile_success() throws InvalidFileException {
        Path folderPath = storage.getAppendPath(RESOURCE_FOLDER, tempModule);
        storage.createFolder(folderPath);
        assertTrue(Files.exists(folderPath));
        Path filePath = storage.getAppendPath(folderPath, "test1.txt");
        assertFalse(Files.exists(filePath));
        storage.createFile(filePath);
        assertTrue(Files.exists(folderPath));
        assertTrue(Files.exists(filePath));
        storage.delete(folderPath);
    }

    @Test
    void createFile_duplicateFile() throws InvalidFileException {
        Path folderPath = storage.getAppendPath(RESOURCE_FOLDER, tempModule);
        Path filePath = storage.getAppendPath(folderPath, "test1.txt");
        storage.createFolder(folderPath);
        assertFalse(Files.exists(filePath));
        storage.createFile(filePath);
        assertTrue(Files.exists(filePath));
        storage.createFile(filePath);
        assertTrue(Files.exists(filePath));
        storage.delete(folderPath);
    }

    @Test
    void createFile_missingFolder_exceptionThrown() {
        Path folderPath = storage.getAppendPath(RESOURCE_FOLDER, tempModule);
        Path filePath = storage.getAppendPath(folderPath, "test1.txt");
        assertThrows(InvalidFileException.class, () -> storage.createFile(filePath));
    }

    @Test
    void createFile_invalidPath_exceptionThrown() {
        assertThrows(InvalidFileException.class, () -> storage.createFile(null));
    }


}
