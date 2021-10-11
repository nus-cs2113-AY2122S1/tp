package taa.util;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class Util {
    public static boolean isStringInteger(String string) {
        boolean isInt;
        try {
            int value = Integer.parseInt(string);
            isInt = true;
        } catch (NumberFormatException e) {
            isInt = false;
        }

        return isInt;
    }

    public static boolean isStringDouble(String string) {
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
     * Gets the Path object from filename.
     *
     * @param filename The filename.
     * @return A Path object.
     */
    public static Path getPathFromFilename(String filename) {
        String separator = FileSystems.getDefault().getSeparator();
        String[] filenameSplit = filename.split(separator.replace("\\", "\\\\"));

        String firstPathString = filenameSplit[0];
        Path path;
        if (filenameSplit.length > 1) {
            String[] additionalPathStrings = Arrays.copyOfRange(filenameSplit, 1, filenameSplit.length - 1);
            path = Paths.get(firstPathString, additionalPathStrings);
        } else {
            path = Paths.get(firstPathString);
        }

        return path;
    }

    /**
     * Checks if file exists.
     *
     * @param filename The file to check.
     * @return true if exists, else false.
     */
    public static boolean fileExists(String filename) {
        Path path = getPathFromFilename(filename);

        return Files.exists(path);
    }

    /**
     * Creates a file and parent directories (if necessary).
     *
     * @param filename The file to create.
     * @return true if file already exists or successfully created, else false.
     */
    public static boolean createFile(String filename) {
        if (fileExists(filename)) {
            return true;
        }

        Path path = getPathFromFilename(filename);
        Path parentPath = path.getParent();

        try {
            Files.createDirectories(parentPath);
            Files.createFile(path);
        } catch (IOException e) {
            return false;
        }

        return true;
    }
}
