package terminus.storage;

import java.nio.file.Path;
import terminus.common.Messages;
import terminus.common.TerminusLogger;
import terminus.exception.InvalidFileException;
import terminus.module.ModuleManager;

/**
 * StorageManager class to handle all file I/O related operations in TermiNUS.
 */
public class StorageManager {

    private Path baseDirectory;

    /**
     * Determine if the file storage for TermiNUS is disabled or not.
     */
    private boolean isDisabled;

    private NoteStorage noteStorage;
    private FolderStorage folderStorage;
    private JsonStorage jsonStorage;
    private PdfStorage pdfStorage;
    private Storage storage;

    /**
     * Initialises all related storage type that handles different file I/O operations.
     *
     * @param baseDirectory The base directory in which all data is stored in.
     * @param mainJsonFileName The main json file where the main data for TermiNUS is stored in.
     */
    public StorageManager(Path baseDirectory, String mainJsonFileName) {
        this.baseDirectory = baseDirectory;
        this.noteStorage = new NoteStorage(baseDirectory);
        this.folderStorage = new FolderStorage(baseDirectory);
        this.jsonStorage = new JsonStorage(baseDirectory, mainJsonFileName);
        this.pdfStorage = new PdfStorage(baseDirectory);
        this.storage = new Storage();
        this.isDisabled = false;
    }

    /**
     * Executes the file operation with their respective storage type.
     *
     * @param moduleManager The Module Manager containing all item information used in TermiNUS.
     * @param module The related module name for the operation.
     * @param deletedItem The related deleted item name fo the operation.
     * @param action The action determining the next step to take for the operation.
     * @param type The storage typeof the operation.
     * @throws InvalidFileException when any file I/O operations has error.
     */
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

    /**
     * Initializes the base directory and main json file and loads any data if any.
     *
     * @return The ModuleManager objects containing the contents from the json file and its respective note file data.
     * @throws InvalidFileException when any file I/O operations has error.
     */
    public ModuleManager initialize() throws InvalidFileException {
        storage.createFolder(baseDirectory);
        jsonStorage.execute(null, StorageActionEnum.CREATE);
        return load();
    }

    /**
     * Loads the data from the json file and all its respective note data for each module.
     *
     * @return The ModuleManager objects containing the contents from the json file and its respective note file data.
     * @throws InvalidFileException when any file I/O operations has error.
     */
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

    /**
     * Saves all data in the given ModuleManager.
     *
     * @param moduleManager The Module Manager containing all item information used in TermiNUS.
     * @throws InvalidFileException when any file I/O operations has error.
     */
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

    /**
     * Sets the disabled status of the storage manager.
     *
     * @param disabled The value for the disabled status. Either True or False.
     */
    public void setDisabled(boolean disabled) {
        isDisabled = disabled;
    }
}
