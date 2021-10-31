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

public class NoteStorageTest {

    private NoteStorage noteStorage;
    private ModuleManager moduleManager;

    private String tempModule = "test".toUpperCase();

    @BeforeEach
    void setup() {
        this.noteStorage = new NoteStorage(TestFilePath.RESOURCE_FOLDER);
        this.moduleManager = new ModuleManager();
        moduleManager.addModule(tempModule);
        NusModule module = moduleManager.getModule(tempModule);
        ContentManager<Note> noteContentManager = module.getContentManager(Note.class);
        noteContentManager.add(new Note("a", "this is a test"));
    }

    @AfterEach
    void reset() throws InvalidFileException {
        Path folderPath = noteStorage.getAppendPath(TestFilePath.RESOURCE_FOLDER, tempModule);
        noteStorage.delete(folderPath);
    }

    @Test
    void createNoteFile_success() throws InvalidFileException {
        Path folderPath = noteStorage.getAppendPath(TestFilePath.RESOURCE_FOLDER, tempModule);
        assertFalse(Files.exists(folderPath));
        Path filePath = noteStorage.getAppendPath(folderPath, "a.txt");
        assertFalse(Files.exists(filePath));
        noteStorage.createNoteFile(moduleManager, tempModule);
        assertTrue(Files.exists(folderPath));
        assertTrue(Files.exists(filePath));
    }

    @Test
    void removeNoteFile_success() throws InvalidFileException {
        Path folderPath = noteStorage.getAppendPath(TestFilePath.RESOURCE_FOLDER, tempModule);
        noteStorage.createFolder(folderPath);
        Path filePath = noteStorage.getAppendPath(folderPath, "a.txt");
        noteStorage.writeFile(filePath, "this is a test");
        assertTrue(Files.exists(folderPath));
        assertTrue(Files.exists(filePath));
        noteStorage.removeNoteFile(tempModule, "a");
        assertFalse(Files.exists(filePath));
    }

    @Test
    void removeNoteFile_directoryGiven_exceptionThrown() throws InvalidFileException {
        Path folderPath = noteStorage.getAppendPath(TestFilePath.RESOURCE_FOLDER, tempModule);
        Path secondFolderPath = noteStorage.getAppendPath(folderPath, "TEST2.txt");
        noteStorage.createFolder(folderPath);
        noteStorage.createFolder(secondFolderPath);
        assertTrue(Files.exists(folderPath));
        assertTrue(Files.exists(secondFolderPath));
        assertThrows(InvalidFileException.class, () -> noteStorage.removeNoteFile(tempModule, "TEST2"));
    }

    @Test
    void saveAllNotes_success() throws InvalidFileException {
        Path folderPath = noteStorage.getAppendPath(TestFilePath.RESOURCE_FOLDER, tempModule);
        assertFalse(Files.exists(folderPath));
        Path filePath = noteStorage.getAppendPath(folderPath, "a.txt");
        assertFalse(Files.exists(filePath));
        noteStorage.saveAllNotes(moduleManager);
        assertTrue(Files.exists(folderPath));
        assertTrue(Files.exists(filePath));
    }

    @Test
    void loadNoteIntoModuleManager_success() throws InvalidFileException {
        NusModule module = moduleManager.getModule(tempModule);
        ContentManager<Note> noteContentManager = module.getContentManager(Note.class);
        assertEquals(1, noteContentManager.getTotalContents());
        Path folderPath = noteStorage.getAppendPath(TestFilePath.RESOURCE_FOLDER, tempModule);
        noteStorage.createFolder(folderPath);
        for (int i = 1; i <= 5; i++) {
            String filename = String.format("a%s.txt", String.valueOf(i));
            Path filepath = noteStorage.getAppendPath(folderPath, filename);
            noteStorage.createFile(filepath);
        }
        assertEquals(1, noteContentManager.getTotalContents());
        noteStorage.loadNoteIntoModuleManager(moduleManager, tempModule);
        assertEquals(5, noteContentManager.getTotalContents());
    }

    @Test
    void loadNoteIntoModuleManager_invalidFile() throws InvalidFileException {
        NusModule module = moduleManager.getModule(tempModule);
        ContentManager<Note> noteContentManager = module.getContentManager(Note.class);
        assertEquals(1, noteContentManager.getTotalContents());
        Path folderPath = noteStorage.getAppendPath(TestFilePath.RESOURCE_FOLDER, tempModule);
        noteStorage.createFolder(folderPath);
        for (int i = 1; i <= 5; i++) {
            String filename = String.format("a%s.txt", String.valueOf(i));
            Path filepath = noteStorage.getAppendPath(folderPath, filename);
            noteStorage.createFile(filepath);
        }
        assertEquals(1, noteContentManager.getTotalContents());
        Path invalidFilepath = noteStorage.getAppendPath(folderPath, "a.ser");
        noteStorage.createFile(invalidFilepath);
        noteStorage.loadNoteIntoModuleManager(moduleManager, tempModule);
        assertEquals(5, noteContentManager.getTotalContents());
    }

    @Test
    void isValidTextFile_success() {
        Path folderPath = noteStorage.getAppendPath(TestFilePath.RESOURCE_FOLDER, tempModule);
        Path filePath = noteStorage.getAppendPath(folderPath, "a.txt");
        File file1 = new File(filePath.toString());
        assertTrue(noteStorage.isValidTextFile(file1));
        filePath = noteStorage.getAppendPath(folderPath, "a.ser");
        File file2 = new File(filePath.toString());
        assertFalse(noteStorage.isValidTextFile(file2));
    }

    @Test
    void isValidTextFile_nullInput_exceptionThrown() {
        assertFalse(noteStorage.isValidTextFile(null));
    }

    @Test
    void execute_success() throws InvalidFileException {
        noteStorage.execute(moduleManager, tempModule, null, StorageActionEnum.CREATE);
        noteStorage.execute(moduleManager, tempModule, null, StorageActionEnum.RELOAD);
        noteStorage.execute(moduleManager, tempModule, "a", StorageActionEnum.DELETE);
    }

    @Test
    void execute_invalidAction_exceptionThrown() {
        assertThrows(InvalidFileException.class, () -> noteStorage.execute(moduleManager, tempModule, null,
                StorageActionEnum.EXPORT));
    }

}
