package taa.util;

//@@author leyondlee
import taa.exception.FileCreationException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Util {
    /**
     * Checks if a string can be converted to an integer.
     *
     * @param string The string to check.
     * @return true if string can convert to integer, else false.
     */
    public static boolean isStringInteger(String string) {
        if (string.isEmpty()) {
            return false;
        }

        boolean isInt;
        try {
            int value = Integer.parseInt(string);
            isInt = true;
        } catch (NumberFormatException e) {
            isInt = false;
        }

        return isInt;
    }

    /**
     * Checks if a string can be converted to a double.
     *
     * @param string The string to check.
     * @return true if string can convert to double, else false.
     */
    public static boolean isStringDouble(String string) {
        if (string.isEmpty()) {
            return false;
        }

        boolean isDouble;
        try {
            double value = Double.parseDouble(string);
            isDouble = true;
        } catch (NumberFormatException e) {
            isDouble = false;
        }

        return isDouble;
    }

    /**
     * Checks if a string can be converted to a boolean.
     *
     * @param string The string to check.
     * @return true if string can convert to boolean, else false.
     */
    public static boolean isStringBoolean(String string) {
        if (string.isEmpty()) {
            return false;
        }

        return string.equals("true") || string.equals("false");
    }

    /**
     * Checks if file exists.
     *
     * @param filename The file to check.
     * @return true if exists, else false.
     */
    public static boolean fileExists(String filename) {
        File file = new File(filename);

        return file.exists();
    }

    /**
     * Creates a file and parent directories (if necessary). This method has no effect if the file already exists.
     *
     * @param filename The file to create.
     * @throws FileCreationException if fail to create file.
     */
    public static void createFile(String filename) throws FileCreationException {
        if (fileExists(filename)) {
            return;
        }

        File file = new File(filename);
        File parentFile = file.getParentFile();

        boolean result;
        try {
            boolean hasCreatedDir = true;
            if (parentFile != null && (!parentFile.exists() || !parentFile.isDirectory())) {
                boolean parentFileExists = parentFile.exists();
                boolean parentFileIsDir = parentFile.isDirectory();
                boolean needCreateParent = !parentFileExists || !parentFileIsDir;
                if (needCreateParent) {
                    hasCreatedDir = parentFile.mkdirs();
                }
            }

            if (hasCreatedDir) {
                result = file.createNewFile();
            } else {
                result = false;
            }
        } catch (IOException e) {
            result = false;
        }

        if (!result) {
            throw new FileCreationException(filename);
        }
    }

    public static String getAbsolutePath(String pathStr) {
        Path path = Paths.get(pathStr).toAbsolutePath().normalize();
        return path.toString();
    }

    public static boolean isFile(String path) {
        if (!fileExists(path)) {
            return false;
        }

        File file = new File(path);
        return file.isFile();
    }

    public static boolean isFolder(String path) {
        if (!fileExists(path)) {
            return false;
        }

        File file = new File(path);
        return file.isDirectory();
    }
}
