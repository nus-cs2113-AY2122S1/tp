package terminus.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.gson.JsonSyntaxException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
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
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
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
    public static final String FAULTY_FOLDER = TestFilePath.FAULTY_FOLDER;

    private String tempModule = "test".toUpperCase();
    private Storage storage;

    @BeforeEach
    void setup() {
        this.storage = new Storage();
    }

    @AfterEach
    void reset() throws InvalidFileException {
        Path folderPath = storage.getAppendPath(RESOURCE_FOLDER, tempModule);
        storage.delete(folderPath);
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
        assertFalse(Files.exists(folderPath));
        Path filePath = storage.getAppendPath(folderPath, "test1.txt");
        assertThrows(InvalidFileException.class, () -> storage.createFile(filePath));
    }

    @Test
    void createFile_invalidPath_exceptionThrown() {
        assertThrows(InvalidFileException.class, () -> storage.createFile(null));
    }

    @Test
    void readFile_success() throws InvalidFileException {
        Path folderPath = storage.getAppendPath(RESOURCE_FOLDER, tempModule);
        Path filePath = storage.getAppendPath(folderPath, "test1.txt");
        storage.createFolder(folderPath);
        storage.createFile(filePath);
        String s = storage.readFile(filePath);
        assertEquals("", s);
        storage.delete(folderPath);
    }

    @Test
    void readFile_invalidContent_exceptionThrown() {
        Path folderPath = storage.getAppendPath(RESOURCE_FOLDER, FAULTY_FOLDER);
        Path filePath = storage.getAppendPath(folderPath, "100.txt");
        assertThrows(InvalidFileException.class, () -> storage.readFile(filePath));
    }

    @Test
    void readFile_nullPath_exceptionThrown() {
        assertThrows(InvalidFileException.class, () -> storage.readFile(null));
    }

    @Test
    void writeFile_success() throws InvalidFileException {
        Path folderPath = storage.getAppendPath(RESOURCE_FOLDER, tempModule);
        Path filePath = storage.getAppendPath(folderPath, "test1.txt");
        storage.createFolder(folderPath);
        storage.writeFile(filePath, "hello");
        String s = storage.readFile(filePath);
        assertEquals("hello", s);
        storage.delete(folderPath);
    }

    @Test
    void writeFile_nullInputs_exceptionThrown() throws InvalidFileException {
        Path folderPath = storage.getAppendPath(RESOURCE_FOLDER, tempModule);
        Path filePath = storage.getAppendPath(folderPath, "test1.txt");
        storage.createFolder(folderPath);
        assertThrows(InvalidFileException.class, () -> storage.writeFile(null, "hello"));
        assertThrows(InvalidFileException.class, () -> storage.writeFile(filePath, null));
        storage.delete(folderPath);
    }

    @Test
    void writeFile_missingDirectory_exceptionThrown() throws InvalidFileException, IOException {
        Path folderPath = storage.getAppendPath(RESOURCE_FOLDER, tempModule);
        Path filePath = storage.getAppendPath(folderPath, "test1.txt");
        assertThrows(InvalidFileException.class, () -> storage.writeFile(filePath, "test1"));
    }

    @Test
    void delete_success() throws InvalidFileException {
        Path folderPath = storage.getAppendPath(RESOURCE_FOLDER, tempModule);
        assertFalse(Files.exists(folderPath));
        storage.createFolder(folderPath);
        assertTrue(Files.exists(folderPath));
        storage.delete(folderPath);
        assertFalse(Files.exists(folderPath));
    }

    @Test
    void delete_nestedFolder() throws InvalidFileException {
        Path folderPath = storage.getAppendPath(RESOURCE_FOLDER, tempModule);
        Path secondFolderPath = storage.getAppendPath(folderPath, tempModule);
        Path thirdFolderPath = storage.getAppendPath(secondFolderPath, tempModule);
        assertFalse(Files.exists(folderPath));
        assertFalse(Files.exists(secondFolderPath));
        assertFalse(Files.exists(thirdFolderPath));
        storage.createFolder(thirdFolderPath);
        assertTrue(Files.exists(folderPath));
        assertTrue(Files.exists(secondFolderPath));
        assertTrue(Files.exists(thirdFolderPath));
        storage.delete(folderPath);
        assertFalse(Files.exists(folderPath));
        assertFalse(Files.exists(secondFolderPath));
        assertFalse(Files.exists(thirdFolderPath));
    }

    @Test
    void delete_nullPath_exceptionThrown() {
        assertThrows(InvalidFileException.class, () -> storage.delete(null));
    }

    @Test
    void delete_pathDoesNotExists() throws InvalidFileException {
        Path folderPath = storage.getAppendPath(RESOURCE_FOLDER, tempModule);
        Path secondFolderPath = storage.getAppendPath(folderPath, tempModule);
        Path thirdFolderPath = storage.getAppendPath(secondFolderPath, tempModule);
        storage.delete(thirdFolderPath);
    }


    @Test
    @EnabledOnOs({OS.WINDOWS})
    void delete_fileInUsed_exceptionThrown() throws IOException, InvalidFileException {
        Path folderPath = storage.getAppendPath(RESOURCE_FOLDER, tempModule);
        Path filePath = storage.getAppendPath(folderPath, "test1.txt");
        storage.createFolder(folderPath);
        storage.createFile(filePath);
        File file = new File(filePath.toString());
        FileOutputStream output = new FileOutputStream(file);
        ObjectOutputStream objectOutput = new ObjectOutputStream(output);
        objectOutput.writeObject("save");
        assertThrows(InvalidFileException.class, () -> storage.delete(folderPath));
        assertThrows(InvalidFileException.class, () -> storage.delete(filePath));
        assertThrows(InvalidFileException.class, () -> storage.delete(folderPath));
        objectOutput.flush();
        objectOutput.close();
    }

    @Test
    void getAppendPath_success() {
        Path folderPath = storage.getAppendPath(RESOURCE_FOLDER, tempModule);
        Path path = RESOURCE_FOLDER.resolve(tempModule);
        assertEquals(path, folderPath);
    }

    @Test
    void getAppendPath_nullInput() {
        Path resultPath = storage.getAppendPath(RESOURCE_FOLDER, null);
        assertEquals(null, resultPath);

    }

    @Test
    @EnabledOnOs({OS.WINDOWS})
    void getAppendPath_invalidPath() {
        Path resultPath = storage.getAppendPath(RESOURCE_FOLDER, "*");
        assertEquals(null, resultPath);
    }


    @Test
    void getListOfFiles_success() throws InvalidFileException {
        Path folderPath = storage.getAppendPath(RESOURCE_FOLDER, tempModule);
        storage.createFolder(folderPath);
        Path filePath1 = storage.getAppendPath(folderPath, "test1.txt");
        storage.createFile(filePath1);
        Path filePath2 = storage.getAppendPath(folderPath, "test2.txt");
        storage.createFile(filePath2);
        Path filePath3 = storage.getAppendPath(folderPath, "test3.txt");
        storage.createFile(filePath3);
        File[] resultList = storage.getListOfFiles(folderPath);
        assertEquals(3, resultList.length);
        storage.delete(folderPath);
    }

    @Test
    void getListOfFiles_nullPath() {
        assertThrows(InvalidFileException.class, () -> storage.getListOfFiles(null));
    }

    @Test
    void getListOfFiles_missingPath() {
        Path folderPath = storage.getAppendPath(RESOURCE_FOLDER, tempModule);
        assertThrows(InvalidFileException.class, () -> storage.getListOfFiles(folderPath));
    }

    @Test
    void getListOfFiles_noFiles() throws InvalidFileException {
        Path folderPath = storage.getAppendPath(RESOURCE_FOLDER, tempModule);
        storage.createFolder(folderPath);
        File[] resultList = storage.getListOfFiles(folderPath);
        assertEquals(0, resultList.length);
        storage.delete(folderPath);
    }

    @Test
    void getFileType_success() throws InvalidFileException {
        Path folderPath = storage.getAppendPath(RESOURCE_FOLDER, tempModule);
        Path filePath = storage.getAppendPath(folderPath, "test1.txt");
        File file = new File(filePath.toString());
        String result = storage.getFileType(file);
        assertEquals("text/plain", result);
    }

    @Test
    void getFileType_nullInput_exceptionThrown() {
        assertThrows(InvalidFileException.class, () -> storage.getFileType(null));
    }

    @Test
    void renameFolder_success() throws InvalidFileException {
        Path folderPath = storage.getAppendPath(RESOURCE_FOLDER, tempModule);
        storage.createFolder(folderPath);
        Path newFolderPath = storage.getAppendPath(RESOURCE_FOLDER, "test1");
        assertTrue(Files.exists(folderPath));
        assertFalse(Files.exists(newFolderPath));
        storage.renameFolder(folderPath, newFolderPath);
        assertTrue(Files.exists(newFolderPath));
        assertFalse(Files.exists(folderPath));
        storage.delete(newFolderPath);
    }

    @Test
    void renameFolder_oldPathNotExist() throws InvalidFileException {
        Path folderPath = storage.getAppendPath(RESOURCE_FOLDER, tempModule);
        Path newFolderPath = storage.getAppendPath(RESOURCE_FOLDER, "test1");
        assertFalse(Files.exists(folderPath));
        assertFalse(Files.exists(newFolderPath));
        storage.renameFolder(folderPath, newFolderPath);
        assertTrue(Files.exists(newFolderPath));
        assertFalse(Files.exists(folderPath));
        storage.delete(newFolderPath);
    }

    @Test
    void renameFolder_newPathExist() throws InvalidFileException {
        Path folderPath = storage.getAppendPath(RESOURCE_FOLDER, tempModule);
        storage.createFolder(folderPath);
        Path newFolderPath = storage.getAppendPath(RESOURCE_FOLDER, "test1");
        storage.createFolder(newFolderPath);
        assertTrue(Files.exists(folderPath));
        assertTrue(Files.exists(newFolderPath));
        storage.renameFolder(folderPath, newFolderPath);
        assertTrue(Files.exists(newFolderPath));
        assertTrue(Files.exists(folderPath));
        storage.delete(newFolderPath);
        storage.delete(folderPath);
    }

    @Test
    void renameFolder_nullInputs_exceptionThrown() {
        Path folderPath = storage.getAppendPath(RESOURCE_FOLDER, tempModule);
        Path newFolderPath = storage.getAppendPath(RESOURCE_FOLDER, "test1");
        assertThrows(AssertionError.class, () -> storage.renameFolder(null, newFolderPath));
        assertThrows(AssertionError.class, () -> storage.renameFolder(folderPath, null));
    }

    @Test
    void getBufferedReader_success() throws InvalidFileException {
        Path filePath = storage.getAppendPath(RESOURCE_FOLDER, VALID_FILE);
        BufferedReader reader = storage.getBufferedReader(filePath);
        assertNotEquals(null, reader);
    }

    @Test
    void getBufferedReader_nullPath_exceptionThrown() {
        assertThrows(InvalidFileException.class, () -> storage.getBufferedReader(null));
    }

    @Test
    void getBufferedReader_missingFile_exceptionThrown() {
        Path folderPath = storage.getAppendPath(RESOURCE_FOLDER, tempModule);
        Path filePath = storage.getAppendPath(folderPath, "test1.txt");
        assertThrows(InvalidFileException.class, () -> storage.getBufferedReader(filePath));
    }

    @Test
    void cleanAllFilesInclusive_nullInput_exceptionThrown() {
        assertThrows(AssertionError.class, () -> storage.cleanAllFilesInclusive(null));
    }

    @Test
    void cleanAllFilesInclusive_missingPath_exceptionThrown() {
        Path folderPath = storage.getAppendPath(RESOURCE_FOLDER, tempModule);
        Path filePath = storage.getAppendPath(folderPath, "test1.txt");
        assertThrows(InvalidFileException.class, () -> storage.cleanAllFilesInclusive(filePath));
    }



}
