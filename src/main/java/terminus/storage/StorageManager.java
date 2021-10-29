package terminus.storage;

import java.io.IOException;
import java.nio.file.Path;
import terminus.common.Messages;
import terminus.exception.InvalidFileException;
import terminus.module.ModuleManager;

public class StorageManager {

    private Path baseDirectory;

    private NoteStorage noteStorage;
    private FolderStorage folderStorage;

    public StorageManager(Path baseDirectory) {
        this.baseDirectory = baseDirectory;
        this.noteStorage = new NoteStorage(baseDirectory);
        this.folderStorage = new FolderStorage(baseDirectory);
    }

    public void execute(ModuleManager moduleManager, String module, String deletedItem, StorageActionEnum action,
            StorageTypeEnum type)
            throws InvalidFileException {
        if (moduleManager == null) {
            throw new InvalidFileException(Messages.ERROR_MISSING_MODULE_MANAGER);
        }
        switch (type) {
        case JSON:
            break;
        case FOLDER:
            folderStorage.execute(module, deletedItem, action);
            break;
        case TEXT:
            noteStorage.execute(moduleManager, module, deletedItem, action);
            break;
        case PDF:
            break;
        default:
            throw new InvalidFileException(Messages.ERROR_STORAGE_INVALID_TYPE);
        }
    }

    public void initialise() {

    }


}
