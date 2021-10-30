package terminus.storage;

import java.nio.file.Path;
import terminus.common.Messages;
import terminus.exception.InvalidFileException;

public class FolderStorage extends Storage {

    private Path baseDirectory;

    public FolderStorage(Path baseDirectory) {
        this.baseDirectory = baseDirectory;
    }

    public void execute(String module, String deletedModule, StorageActionEnum action) throws InvalidFileException {
        switch (action) {
        case CREATE:
            createModuleFolder(module);
            break;
        case DELETE:
            deleteModuleFolder(deletedModule);
            break;
        case UPDATE:
            renameModuleFolder(module, deletedModule);
            break;
        default:
            throw new InvalidFileException(Messages.ERROR_STORAGE_INVALID_TYPE);
        }
    }

    private void createModuleFolder(String module) throws InvalidFileException {
        Path moduleFolder = getAppendPath(baseDirectory, module);
        try {
            delete(moduleFolder);
            createFolder(moduleFolder);
        } catch (InvalidFileException e) {
            throw new InvalidFileException(String.format(Messages.ERROR_STORAGE_CREATE_FOLDER, moduleFolder));
        }

    }

    private void deleteModuleFolder(String deletedModule) throws InvalidFileException {
        Path moduleFolder = getAppendPath(baseDirectory, deletedModule);
        delete(moduleFolder);
    }

    private void renameModuleFolder(String module, String deletedModule) throws InvalidFileException {
        Path newModuleFolder = getAppendPath(baseDirectory, module);
        Path moduleFolder = getAppendPath(baseDirectory, deletedModule);
        renameFolder(moduleFolder, newModuleFolder);
    }
}
