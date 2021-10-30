package terminus.storage;

import java.io.BufferedReader;
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

/**
 * Storage class to handle any filo I/O operations.
 */
public class Storage {

    /**
     * Creates a folder given by its folder path.
     *
     * @param folderPath The full path of the folder to be created.
     * @throws InvalidFileException when any file I/O operations has error.
     */
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

    /**
     * Creates a file given by its file path.
     *
     * @param filePath The full path of the file to be created.
     * @throws InvalidFileException when any file I/O operations has error.
     */
    public void createFile(Path filePath) throws InvalidFileException {
        try {
            if (Files.notExists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (FileAlreadyExistsException e) {
            // Should not reach here
            throw new InvalidFileException(String.format(Messages.ERROR_STORAGE_FILE_EXIST, filePath));
        } catch (IOException e) {
            throw new InvalidFileException(String.format(Messages.ERROR_STORAGE_CREATE_FILE, filePath));
        } catch (Exception e) {
            throw new InvalidFileException(e.getMessage());
        }
    }

    /**
     * Reads the contents of the given file by its file path.
     *
     * @param filePath The full path of the file to be read.
     * @return The string contents of the file to be read.
     * @throws InvalidFileException when any file I/O operations has error.
     */
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

    /**
     * Writes content into the given file by its file path.
     *
     * @param filePath The full path of the file to be written.
     * @param data The content to be written into the given file.
     * @throws InvalidFileException when any file I/O operations has error.
     */
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

    /**
     * Delete a file or folder given by their file path.
     *
     * @param filePath The full path of the file / folder to be deleted.
     * @throws InvalidFileException when any file I/O operations has error.
     */
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

    /**
     * Cleans all files and folders within the given file path and deletes itself after.
     *
     * @param filePath The full path of the folder to be cleaned.
     * @throws InvalidFileException when any file I/O operations has error.
     */
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

    /**
     * Appends the path of the folder the item is in to itself.
     *
     * @param corePath The base folder in which the item is in.
     * @param path The file name of the item.
     * @return The full path of the given path.
     */
    public Path getAppendPath(Path corePath, String path) {
        return Paths.get(corePath.toString(), path);
    }

    /**
     * Gets a list of files within a folder by its folder path.
     *
     * @param folderPath The full path of the folder where all files within it is retrieved.
     * @return A list of all files within the given folder.
     * @throws InvalidFileException when any file I/O operations has error.
     */
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

    /**
     * Gets the file type of the given file.
     *
     * @param file The given file to be checked on.
     * @return The file type of the given file.
     * @throws InvalidFileException when any file I/O operations has error.
     */
    public String getFileType(File file) throws InvalidFileException {
        try {
            return Files.probeContentType(Paths.get(file.getAbsolutePath()));
        } catch (IOException e) {
            throw new InvalidFileException(String.format(Messages.ERROR_GET_FILE_CONTENT, file.getAbsolutePath()));
        }
    }

    /**
     * Renames the a folder given by its old name and new name to be renamed to.
     *
     * @param oldPath The full path of the folder.
     * @param newPath The full path of the newly renamed folder.
     * @throws InvalidFileException when any file I/O operations has error.
     */
    public void renameFolder(Path oldPath, Path newPath) throws InvalidFileException {
        if (Files.notExists(newPath) && Files.notExists(oldPath)) {
            createFolder(newPath);
        } else if (Files.exists(oldPath) && Files.notExists(newPath)) {
            File oldFile = new File(oldPath.toString());
            File newFile = new File(newPath.toString());
            if (oldFile == null || newFile == null) {
                throw new InvalidFileException(Messages.ERROR_CHANGE_FILE_NAME);
            }
            if (!oldFile.renameTo(newFile)) {
                throw new InvalidFileException(Messages.ERROR_CHANGE_FILE_NAME);
            }
        }
    }

    /**
     * Returns a buffer reader for a file.
     *
     * @param filePath The file name to be read on.
     * @return The buffer reader for the given file.
     * @throws InvalidFileException when any file I/O operations has error.
     */
    public BufferedReader getBufferedReader(Path filePath) throws InvalidFileException {
        try {
            return Files.newBufferedReader(filePath);
        } catch (IOException e) {
            throw new InvalidFileException(String.format(Messages.ERROR_FILE_BUFFERED_READER, filePath));
        } catch (Exception e) {
            throw new InvalidFileException(e.getMessage());
        }
    }
}
