package terminus.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import terminus.common.CommonUtils;
import terminus.common.TerminusLogger;
import terminus.content.ContentManager;
import terminus.content.Note;
import terminus.module.ModuleManager;
import terminus.module.NusModule;
import terminus.parser.MainCommandParser;

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

    public static ModuleStorage getInstance() {
        return moduleStorage;
    }

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
     * Loads a JSON file and parses it as a NusModule object based on GSON. Returns null if the file does not exist or
     * the file is not in a valid format.
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
        TerminusLogger.info("Converting NusModule object into String...");
        String jsonString = gson.toJson(moduleManager);
        TerminusLogger.info("String conversion completed.");
        TerminusLogger.info(String.format("Writing to file: %s", filePath.toString()));
        assert jsonString != null && !jsonString.isBlank() : "File saved is blank";
        Files.writeString(filePath, jsonString);
        saveAllNotes(moduleManager);
    }

    private void loadAllNotes(ModuleManager moduleManager) throws IOException {
        Path modDirPath;
        for (String mod : moduleManager.getAllModules()) {
            modDirPath = Paths.get(filePath.getParent().toString(), mod);
            // Check if module name is a valid file name
            if (!CommonUtils.isValidFileName(mod)) {
                moduleManager.removeModule(mod);
            }
            // Check if directory does not exist proceed to create directory, retrieve notes otherwise.
            if (!Files.isDirectory(modDirPath)) {
                Files.createDirectories(modDirPath);
            } else {
                loadNotesFromModule(moduleManager, mod);
            }
        }
    }

    public void loadNotesFromModule(ModuleManager moduleManager, String mod) throws IOException {
        Path modDirPath;
        modDirPath = Paths.get(filePath.getParent().toString(), mod);
        File folder = new File(modDirPath.toString());
        File[] listOfFiles = folder.listFiles();
        ContentManager<Note> contentManager = moduleManager.getModule(mod).getContentManager(Note.class);
        contentManager.purgeData();
        for (File file : listOfFiles) {
            if (file.isFile()) {
                contentManager.add(new Note(CommonUtils.getFileNameOnly(file.getName()),
                        Files.readString(
                                Paths.get(modDirPath.toString(), file.getName()), StandardCharsets.US_ASCII)));
            } else {
                file.delete();
            }
        }
    }

    private void saveAllNotes(ModuleManager moduleManager) throws IOException {
        for (String mod : moduleManager.getAllModules()) {
            saveNotesFromModule(moduleManager, mod);
        }
    }

    public void saveNotesFromModule(ModuleManager moduleManager, String mod) throws IOException {
        Path modDirPath;
        modDirPath = Paths.get(filePath.getParent().toString(), mod);
        assert CommonUtils.isValidFileName(mod);
        if (!Files.isDirectory(modDirPath)) {
            Files.createDirectories(modDirPath);
        }
        deleteAllFilesInDirectory(modDirPath);
        ContentManager<Note> contentManager = moduleManager.getModule(mod).getContentManager(Note.class);
        ArrayList<Note> noteArrayList = contentManager.getContents();
        for (Note note : noteArrayList) {
            assert Files.isDirectory(modDirPath);
            assert CommonUtils.isValidFileName(note.getName());
            Path filePath = Paths.get(modDirPath.toString(), note.getName() + ".txt");
            Files.writeString(filePath, note.getData());
        }
    }

    private void deleteAllFilesInDirectory(Path directoryPath) {
        File folder = new File(directoryPath.toString());
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            file.delete();
        }
    }


}
