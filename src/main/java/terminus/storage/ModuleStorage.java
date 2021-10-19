package terminus.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import terminus.common.CommonFormat;
import terminus.common.CommonUtils;
import terminus.common.Messages;
import terminus.common.TerminusLogger;
import terminus.content.ContentManager;
import terminus.content.Note;
import terminus.module.ModuleManager;

/**
 * ModuleStorage is a class that handles any file I/O operation within TermiNUS.
 */
public class ModuleStorage {

    private final Path filePath;
    private final Gson gson;

    private static ModuleStorage moduleStorage;

    /**
     * Initializes the ModuleStorage with a specific Path to the file.
     *
     * @param filePath The Path to the file to store at.
     */
    public ModuleStorage(Path filePath) {
        this.filePath = filePath;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        moduleStorage = this;
    }

    /**
     * Returns the singleton object of ModuleStorage.
     *
     * @return ModuleStorage object of current session.
     */
    public static ModuleStorage getInstance() {
        return moduleStorage;
    }

    /**
     * Creates main data folder and main JSON object containing TermiNUS information.
     *
     * @throws IOException When the file is inaccessible (e.g. file is locked by OS).
     */
    private void initializeFile() throws IOException {
        assert filePath != null : "filePath should not be null";
        if (!Files.isDirectory(filePath.getParent())) {
            TerminusLogger.warning("Parent directories not found, attempting to create them...");
            Files.createDirectories(filePath.getParent());
            TerminusLogger.info("Parent directories created.");
        }
        if (!Files.exists(filePath)) {
            TerminusLogger.warning(String.format("%s not found, attempting to create file...",
                    filePath.getFileName().toString()));
            Files.createFile(filePath);
            TerminusLogger.info(String.format("%s created.", filePath.getFileName().toString()));
        }
    }

    /**
     * Loads a JSON file and parses it as a ModuleManager object based on GSON followed by loading all notes content.
     * Returns null if the file does not exist or the file is not in a valid format.
     *
     * @return NusModule based on the contents of the file.
     * @throws IOException When the file is inaccessible (e.g. file is locked by OS).
     */
    public ModuleManager loadFile() throws IOException {
        initializeFile();
        if (!Files.isReadable(filePath)) {
            TerminusLogger.severe("File is does not exist or is not readable!");
            return null;
        }
        TerminusLogger.info("Decoding JSON to object");
        ModuleManager moduleManager = gson.fromJson(Files.newBufferedReader(filePath), ModuleManager.class);
        TerminusLogger.info("Loading notes content into ModuleManager");
        loadAllNotes(moduleManager);
        return moduleManager;
    }

    /**
     * Saves NusModule instance into a JSON file based on GSON. Throws NullPointerException if the `module` is null.
     *
     * @param moduleManager The ModuleManager to convert to JSON file.
     * @throws IOException When the file is inaccessible (e.g. file is locked by OS).
     */
    public void saveFile(ModuleManager moduleManager) throws IOException {
        if (moduleManager == null) {
            throw new NullPointerException("module cannot be null!");
        }
        initializeFile();
        TerminusLogger.info("Converting ModuleManager object into String...");
        String jsonString = gson.toJson(moduleManager);
        TerminusLogger.info("String conversion completed.");
        TerminusLogger.info(String.format("Writing to file: %s", filePath.toString()));
        assert jsonString != null && !jsonString.isBlank() : "File saved is blank";
        Files.writeString(filePath, jsonString);
    }

    /**
     * Loads all notes data from existing modules if there is any.
     *
     * @param moduleManager The ModuleManager containing existing modules.
     * @throws IOException When the file is inaccessible (e.g. file is locked by OS).
     */
    private void loadAllNotes(ModuleManager moduleManager) throws IOException {
        Path modDirPath;
        for (String mod : moduleManager.getAllModules()) {
            modDirPath = Paths.get(filePath.getParent().toString(), mod);
            // Check if module name is a valid module and file name
            if (!CommonUtils.isValidFileName(mod) || !mod.matches(CommonFormat.SPACE_NEGATED_DELIMITER)) {
                // Skip this module and remove from moduleManager
                TerminusLogger.warning(String.format("Invalid module name detected: %s", mod));
                moduleManager.removeModule(mod);
                continue;
            }
            // Check if directory does not exist and proceed to create directory, otherwise retrieve notes.
            if (Files.notExists(modDirPath)) {
                TerminusLogger.info("Creating directory: " + modDirPath);
                Files.createDirectories(modDirPath);
            } else {
                // Load its notes file data if there is any.
                loadNotesFromModule(moduleManager, mod);
            }
        }
    }

    /**
     * Loads all notes data from a specified module if any.
     *
     * @param moduleManager The ModuleManager containing existing modules.
     * @param mod A module name in the moduleManager.
     * @throws IOException When the file is inaccessible (e.g. file is locked by OS).
     */
    public void loadNotesFromModule(ModuleManager moduleManager, String mod) throws IOException {
        Path modDirPath = Paths.get(filePath.getParent().toString(), mod);
        File folder = new File(modDirPath.toString());
        assert folder != null;
        File[] listOfFiles = folder.listFiles();
        ContentManager<Note> contentManager = moduleManager.getModule(mod).getContentManager(Note.class);
        contentManager.purgeData();
        for (File file : listOfFiles) {
            if (isValidFile(file)) {
                TerminusLogger.info(String.format("Loading note file %s.", file.getAbsolutePath()));
                contentManager.add(new Note(CommonUtils.getFileNameOnly(file.getName()),
                        Files.readString(Paths.get(file.getAbsolutePath()))));
            } else {
                TerminusLogger.info(String.format("File %s is not a valid note file.", file.getAbsolutePath()));
            }
        }
    }

    /**
     * Saves all notes from all modules into multiple text files separated by its module directory.
     *
     * @param moduleManager The ModuleManager containing all data from each module.
     * @throws IOException When the file is inaccessible (e.g. file is locked by OS).
     */
    public void saveAllNotes(ModuleManager moduleManager) throws IOException {
        for (String mod : moduleManager.getAllModules()) {
            saveNotesFromModule(moduleManager, mod);
        }
    }

    /**
     * Saves all notes from a specified module into multiple text files inside the directory of its module name.
     *
     * @param moduleManager The ModuleManager containing all data from each module.
     * @param mod A module name in the moduleManager.
     * @throws IOException When the file is inaccessible (e.g. file is locked by OS).
     */
    public void saveNotesFromModule(ModuleManager moduleManager, String mod) throws IOException {
        Path modDirPath = Paths.get(filePath.getParent().toString(), mod);
        assert CommonUtils.isValidFileName(mod);
        // Create module folder if it is missing.
        if (Files.notExists(modDirPath)) {
            TerminusLogger.info("Creating directory: " + modDirPath);
            Files.createDirectories(modDirPath);
        }

        // Remove all files within the folder, used when notes have been deleted.
        TerminusLogger.info("Removing files from directory: " + modDirPath);
        deleteAllFilesInDirectory(modDirPath);

        // Write to its specific note files.
        TerminusLogger.info("Adding note files into directory: " + modDirPath);
        ContentManager<Note> contentManager = moduleManager.getModule(mod).getContentManager(Note.class);
        ArrayList<Note> noteArrayList = contentManager.getContents();
        for (Note note : noteArrayList) {
            assert Files.isDirectory(modDirPath);
            assert CommonUtils.isValidFileName(note.getName());
            Path filePath = Paths.get(modDirPath.toString(), note.getName() + CommonFormat.EXTENSION_TEXT_FILE);
            Files.writeString(filePath, note.getData());
            TerminusLogger.info("Added file: " + filePath);
        }
    }

    /**
     * Deletes all files within a specified directory given by its full path.
     *
     * @param directoryPath Directory path where all files inside will be deleted.
     */
    private void deleteAllFilesInDirectory(Path directoryPath) throws IOException {
        File folder = new File(directoryPath.toString());
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            cleanAllFilesInclusive(file);
            if (file.exists()) {
                throw new IOException(Messages.ERROR_FILES_NOT_DELETED);
            }
        }
    }

    /**
     * Deletes all files from the deleted module folder.
     *
     * @param mod A module name in the moduleManager.
     * @throws IOException When the file is inaccessible (e.g. file is locked by OS).
     */
    public void cleanAfterDeleteModule(String mod) throws IOException {
        TerminusLogger.info("Cleaning up deleted modules.");
        Path modDirPath = Paths.get(filePath.getParent().toString(), mod);
        if (Files.notExists(modDirPath)) {
            TerminusLogger.info("Directory does not exists: " + modDirPath);
            return;
        }
        File folder = new File(modDirPath.toString());
        cleanAllFilesInclusive(folder);
        if (folder.exists()) {
            throw new IOException(Messages.ERROR_FILES_NOT_DELETED);
        }
    }

    /**
     * Deletes all files within itself and itself from a specified file.
     *
     * @param file The file to be deleted.
     * @throws IOException When the file is inaccessible (e.g. file is locked by OS).
     */
    private void cleanAllFilesInclusive(File file) throws IOException {
        Files.walk(Paths.get(file.getAbsolutePath()))
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
    }

    /**
     * Creates a module directory and checks if directory is empty.
     *
     * @param moduleName The name of the directory to be created.
     * @return True if the directory created is empty.
     * @throws IOException When the file is inaccessible (e.g. file is locked by OS).
     */
    public boolean createModuleDirectory(String moduleName) throws IOException {
        assert moduleName != null;
        assert CommonUtils.isValidFileName(moduleName);
        assert moduleName.matches(CommonFormat.SPACE_NEGATED_DELIMITER);
        Path modDirPath = Paths.get(filePath.getParent().toString(), moduleName);
        if (Files.notExists(modDirPath)) {
            TerminusLogger.info("Creating directory: " + modDirPath);
            Files.createDirectories(modDirPath);
        } else {
            TerminusLogger.info("Removing files from directory: " + modDirPath);
            deleteAllFilesInDirectory(modDirPath);
        }
        return true;
    }

    private boolean isValidFile(File file) throws IOException {
        boolean isValid = true;
        if (!Files.isReadable(Paths.get(file.getAbsolutePath()))) {
            isValid = false;
        } else if (!CommonUtils.isValidFileName(CommonUtils.getFileNameOnly(file.getName()))) {
            isValid = false;
        } else if (!isValidFileSize(file)) {
            isValid = false;
        }
        return isValid;
    }

    private boolean isValidFileSize(File file) throws IOException {
        return Files.size(Paths.get(file.getAbsolutePath())) <= CommonFormat.MAX_FILE_SIZE;
    }


}
