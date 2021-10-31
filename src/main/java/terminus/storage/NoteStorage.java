package terminus.storage;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import terminus.common.CommonUtils;
import terminus.common.Messages;
import terminus.content.ContentManager;
import terminus.content.Note;
import terminus.exception.InvalidFileException;
import terminus.module.ModuleManager;

/**
 * NoteStorage class to handle any note related file operations.
 */
public class NoteStorage extends Storage {

    private Path baseDirectory;
    private static final String FILE_EXTENSION = ".txt";
    public static final String CONTENT_TYPE = "text/plain";

    public NoteStorage(Path baseDirectory) {
        this.baseDirectory = baseDirectory;
    }

    /**
     * Executes the specified operation with the given arguments.
     *
     * @param moduleManager The Module Manager containing all item information used in TermiNUS.
     * @param module The related module name for the note.
     * @param deletedNote The deleted note name.
     * @param action The operation type to determine which operation to execute.
     * @throws InvalidFileException when any file I/O operations has error.
     */
    public void execute(ModuleManager moduleManager, String module, String deletedNote, StorageActionEnum action)
            throws InvalidFileException {
        switch (action) {
        case CREATE:
            createNoteFile(moduleManager, module);
            break;
        case RELOAD:
            loadNoteIntoModuleManager(moduleManager, module);
            break;
        case DELETE:
            removeNoteFile(module, deletedNote);
            break;
        default:
            throw new InvalidFileException(Messages.ERROR_STORAGE_INVALID_TYPE);
        }
    }

    /**
     * Creates a note file.
     *
     * @param moduleManager The Module Manager containing all item information used in TermiNUS.
     * @param module The folder name where the note file should be stored in.
     * @throws InvalidFileException when any file I/O operations has error.
     */
    protected void createNoteFile(ModuleManager moduleManager, String module) throws InvalidFileException {
        createFolder(baseDirectory);
        Path moduleFolder = getAppendPath(baseDirectory, module);
        createFolder(moduleFolder);
        ArrayList<Note> noteArrayList = moduleManager.getModule(module).getContentManager(Note.class).getContents();
        Note note = noteArrayList.get(noteArrayList.size() - 1);
        Path noteFilePath = getAppendPath(moduleFolder, appendFileExtension(note.getName()));
        writeFile(noteFilePath, note.getData());
    }

    /**
     * Deletes the note file given by its name.
     *
     * @param module The folder name where the note file should be deleted from.
     * @param deletedNoteName The name of the file to be deleted.
     * @throws InvalidFileException when any file I/O operations has error.
     */
    protected void removeNoteFile(String module, String deletedNoteName) throws InvalidFileException {
        Path moduleFolder = getAppendPath(baseDirectory, module);
        Path noteFilePath = getAppendPath(moduleFolder, appendFileExtension(deletedNoteName));
        if (Files.isDirectory(noteFilePath)) {
            throw new InvalidFileException(String.format(Messages.ERROR_FILE_FOLDER_MISMATCH, noteFilePath));
        }
        delete(noteFilePath);
    }

    /**
     * Loads all note contents from the given module folder into ModuleManager.
     *
     * @param moduleManager The Module Manager containing all item information used in TermiNUS.
     * @param module The folder name where the all note files in it should be loaded from.
     * @throws InvalidFileException when any file I/O operations has error.
     */
    protected void loadNoteIntoModuleManager(ModuleManager moduleManager, String module) throws InvalidFileException {
        Path moduleFolder = getAppendPath(baseDirectory, module);
        File[] listOfNoteFiles = getListOfFiles(moduleFolder);
        ContentManager<Note> contentManager = moduleManager.getModule(module).getContentManager(Note.class);
        contentManager.purgeData();
        for (File file : listOfNoteFiles) {
            String fileName = CommonUtils.getFileNameOnly(file.getName());
            if (isValidTextFile(file) && CommonUtils.isValidFileName(fileName)) {
                try {
                    String data = readFile(file.toPath());
                    if (data != null) {
                        String noteName = CommonUtils.getFileNameOnly(file.getName());
                        Note newNote = new Note(noteName, data);
                        contentManager.add(newNote);
                    }
                } catch (Exception e) {
                    // Ignore all exception and skip the file
                }
            }
        }
    }

    /**
     * Saves all notes from the ModuleManager into their respective module folder.
     *
     * @param moduleManager The Module Manager containing all item information used in TermiNUS.
     * @throws InvalidFileException when any file I/O operations has error.
     */
    protected void saveAllNotes(ModuleManager moduleManager) throws InvalidFileException {
        for (String module : moduleManager.getAllModules()) {
            Path moduleFolder = getAppendPath(baseDirectory, module);
            createFolder(moduleFolder);
            ContentManager<Note> contentManager = moduleManager.getModule(module).getContentManager(Note.class);
            ArrayList<Note> noteArrayList = contentManager.getContents();
            for (Note note : noteArrayList) {
                String noteFileName = appendFileExtension(note.getName());
                writeFile(getAppendPath(moduleFolder, noteFileName), note.getData());
            }
        }
    }

    /**
     * Appends the text file extension to the given string.
     *
     * @param name The filename without the extension.
     * @return The complete filename with the extension of a text file.
     */
    private String appendFileExtension(String name) {
        return name + FILE_EXTENSION;
    }

    /**
     * Checks if the file is a text file.
     *
     * @param file The given file to be tested on.
     * @return True if the given file is a text file, false otherwise.
     */
    protected boolean isValidTextFile(File file) {
        try {
            String contentType = getFileType(file);
            return CONTENT_TYPE.equals(contentType);
        } catch (InvalidFileException e) {
            return false;
        }
    }
}
