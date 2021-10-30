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

public class NoteStorage extends Storage {

    private Path baseDirectory;
    private final String FILE_EXTENSION = ".txt";
    public final String CONTENT_TYPE = "text/plain";

    public NoteStorage(Path baseDirectory) {
        this.baseDirectory = baseDirectory;
    }

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

    private void createNoteFile(ModuleManager moduleManager, String module) throws InvalidFileException {
        createFolder(baseDirectory);
        Path moduleFolder = getAppendPath(baseDirectory, module);
        createFolder(moduleFolder);
        ArrayList<Note> noteArrayList = moduleManager.getModule(module).getContentManager(Note.class).getContents();
        Note note = noteArrayList.get(noteArrayList.size() - 1);
        Path noteFilePath = getAppendPath(moduleFolder, appendFileExtension(note.getName()));
        writeFile(noteFilePath, note.getData());
    }

    private void removeNoteFile(String module, String deletedNoteName) throws InvalidFileException {
        Path moduleFolder = getAppendPath(baseDirectory, module);
        Path noteFilePath = getAppendPath(moduleFolder, appendFileExtension(deletedNoteName));
        if (Files.isDirectory(noteFilePath)) {
            throw new InvalidFileException(String.format(Messages.ERROR_FILE_FOLDER_MISMATCH, noteFilePath));
        }
        delete(noteFilePath);
    }

    private void loadNoteIntoModuleManager(ModuleManager moduleManager, String module) throws InvalidFileException {
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

    public void saveAllNotes(ModuleManager moduleManager) throws InvalidFileException {
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

    private String appendFileExtension(String name) {
        return name + FILE_EXTENSION;
    }

    private boolean isValidTextFile(File file) {
        try {
            String contentType = getFileType(file);
            return CONTENT_TYPE.equals(contentType);
        } catch (InvalidFileException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
    }
}
