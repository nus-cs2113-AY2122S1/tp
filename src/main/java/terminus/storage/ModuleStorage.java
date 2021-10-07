package terminus.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import terminus.module.NusModule;

public class ModuleStorage {
    
    private Path filePath;
    private Gson gson;

    public ModuleStorage(Path filePath) {
        this.filePath = filePath;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }
    
    private void createRequiredFolders() throws IOException {
        if (!Files.isDirectory(filePath.getParent())) {
            Files.createDirectories(filePath.getParent());
        }
    }
    
    public NusModule loadFile() throws IOException {
        createRequiredFolders();
        if (!Files.exists(filePath) || !Files.isReadable(filePath)) {
            return null;
        }
        return gson.fromJson(Files.newBufferedReader(filePath), NusModule.class);
    }
    
    public void saveFile(NusModule module) throws IOException {
        createRequiredFolders();
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }
        String jsonString = gson.toJson(module);
        Files.writeString(filePath, jsonString);
    }
    
}
