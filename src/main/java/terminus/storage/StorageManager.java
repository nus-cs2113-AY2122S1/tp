package terminus.storage;

import java.nio.file.Path;
import terminus.common.Messages;
import terminus.common.TerminusLogger;
import terminus.exception.InvalidFileException;
import terminus.module.ModuleManager;

public class StorageManager {

    private Path baseDirectory;

    private boolean isDisabled;

    private NoteStorage noteStorage;
    private FolderStorage folderStorage;
    private JsonStorage jsonStorage;
    private PdfStorage pdfStorage;
    private Storage storage;

    public StorageManager(Path baseDirectory, String mainJsonFileName) {
        this.baseDirectory = baseDirectory;
        this.noteStorage = new NoteStorage(baseDirectory);
        this.folderStorage = new FolderStorage(baseDirectory);
        this.jsonStorage = new JsonStorage(baseDirectory, mainJsonFileName);
        this.pdfStorage = new PdfStorage(baseDirectory);
        this.storage = new Storage();
        this.isDisabled = false;
    }

    public void execute(ModuleManager moduleManager, String module, String deletedItem, StorageActionEnum action,
            StorageTypeEnum type)
            throws InvalidFileException {
        if (isDisabled) {
            return;
        }
        if (moduleManager == null) {
            throw new InvalidFileException(Messages.ERROR_MISSING_MODULE_MANAGER);
        }
        switch (type) {
        case JSON:
            jsonStorage.execute(moduleManager, action);
            break;
        case FOLDER:
            folderStorage.execute(module, deletedItem, action);
            break;
        case TEXT:
            noteStorage.execute(moduleManager, module, deletedItem, action);
            break;
        case PDF:
            pdfStorage.execute(moduleManager, module, action);
            break;
        default:
            throw new InvalidFileException(Messages.ERROR_STORAGE_INVALID_TYPE);
        }
    }

    public ModuleManager initialize() throws InvalidFileException {
        storage.createFolder(baseDirectory);
        jsonStorage.execute(null, StorageActionEnum.CREATE);
        return load();
    }

    private ModuleManager load() throws InvalidFileException {
        ModuleManager moduleManager = jsonStorage.loadJson();
        if (moduleManager == null) {
            return new ModuleManager();
        }

        // Filter
        FilterManager filterManager = new FilterManager();
        filterManager.filter(moduleManager);

        // Load Notes
        for (String module : moduleManager.getAllModules()) {
            try {
                noteStorage.execute(moduleManager, module, null, StorageActionEnum.RELOAD);
            } catch (Exception e) {
                TerminusLogger.warning(String.format("Some notes in module %s cannot be loaded.", module));
            }
        }
        return moduleManager;
    }

    public void save(ModuleManager moduleManager) throws InvalidFileException {
        if (isDisabled) {
            return;
        }
        if (moduleManager == null) {
            throw new InvalidFileException(Messages.ERROR_MISSING_MODULE_MANAGER);
        }
        jsonStorage.execute(moduleManager, StorageActionEnum.UPDATE);
        noteStorage.saveAllNotes(moduleManager);
    }

    public void setDisabled(boolean disabled) {
        isDisabled = disabled;
    }
}
