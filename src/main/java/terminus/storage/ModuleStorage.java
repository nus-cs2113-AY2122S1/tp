package terminus.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import terminus.common.TerminusLogger;
import terminus.module.ModuleManager;
import terminus.module.NusModule;

public class ModuleStorage {

    private final Path filePath;
    private final Gson gson;

    /**
     * Initializes the ModuleStorage with a specific Path to the file.
     *
     * @param filePath The Path to the file to store at.
     */
    public ModuleStorage(Path filePath) {
        this.filePath = filePath;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
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
        return gson.fromJson(Files.newBufferedReader(filePath), ModuleManager.class);
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
    }

}
