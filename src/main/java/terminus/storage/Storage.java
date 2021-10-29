package terminus.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import terminus.common.Messages;
import terminus.exception.InvalidFileException;

public class Storage {

    public void createFolder(Path folderPath) throws InvalidFileException {
        try {
            if (Files.notExists(folderPath)) {
                Files.createDirectories(folderPath);
            }
        } catch (FileAlreadyExistsException e) {
            // Should not reach here
            throw new InvalidFileException(String.format(Messages.ERROR_STORAGE_FILE_EXIST, folderPath));
        } catch (IOException e) {
            throw new InvalidFileException(String.format(Messages.ERROR_STORAGE_CREATE_FOLDER, folderPath));
        } catch (Exception e) {
            throw new InvalidFileException(e.getMessage());
        }
    }

    public String readFile(Path filePath) throws InvalidFileException {
        try {
            return Files.readString(filePath);
        } catch (IOException e) {
            throw new InvalidFileException(String.format(Messages.ERROR_STORAGE_READ_FILE, filePath));
        } catch (OutOfMemoryError e) {
            throw new InvalidFileException(String.format(Messages.ERROR_STORAGE_FILE_TOO_LARGE, filePath));
        } catch (Exception e) {
            throw new InvalidFileException(e.getMessage());
        }
    }

    public void writeFile(Path filePath, String data) throws InvalidFileException {
        if (data == null) {
            throw new InvalidFileException(String.format(Messages.ERROR_STORAGE_WRITE_DATA_NULL, filePath));
        }
        try {
            Files.writeString(filePath, data);
        } catch (IOException e) {
            throw new InvalidFileException(String.format(Messages.ERROR_STORAGE_WRITE_FILE, filePath));
        } catch (Exception e) {
            throw new InvalidFileException(e.getMessage());
        }
    }

    public void delete(Path filePath) throws InvalidFileException {
        try {
            Files.deleteIfExists(filePath);
        } catch (DirectoryNotEmptyException e) {
            cleanAllFilesInclusive(filePath);
        } catch (IOException e) {
            throw new InvalidFileException(String.format(Messages.ERROR_STORAGE_FILE_NOT_DELETED, filePath));
        } catch (Exception e) {
            throw new InvalidFileException(e.getMessage());
        }
    }

    private void cleanAllFilesInclusive(Path filePath) throws InvalidFileException {
        File file = new File(filePath.toString());
        try {
            Files.walk(Paths.get(file.getAbsolutePath()))
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        } catch (IOException e) {
            throw new InvalidFileException(String.format(Messages.ERROR_STORAGE_CLEAN_FILE, filePath));
        } catch (Exception e) {
            throw new InvalidFileException(e.getMessage());
        }
        if (Files.exists(filePath)) {
            throw new InvalidFileException(String.format(Messages.ERROR_STORAGE_CLEAN_FILE, filePath));
        }
    }

    public Path getAppendPath(Path corePath, String path) {
        return Paths.get(corePath.toString(), path);
    }

    public File[] getListOfFiles(Path folderPath) throws InvalidFileException {
        File folder = new File(folderPath.toString());
        if (folder == null) {
            throw new InvalidFileException(String.format(Messages.ERROR_MISSING_FOLDER, folderPath));
        }
        File[] result = folder.listFiles();
        if (result == null) {
            throw new InvalidFileException(String.format(Messages.ERROR_GET_FILES, folderPath));
        }
        return result;
    }

    public String getFileType(File file) throws InvalidFileException {
        try {
            return Files.probeContentType(Paths.get(file.getAbsolutePath()));
        } catch (IOException e) {
            throw new InvalidFileException(String.format(Messages.ERROR_GET_FILE_CONTENT, file.getAbsolutePath()));
        }
    }
}
