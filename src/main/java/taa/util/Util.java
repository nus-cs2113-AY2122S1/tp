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
        if (string == null || string.isEmpty()) {
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

    private static boolean checkDecimalPlaces(String string, int maxDp) {
        String[] splitString = string.split("\\.");
        if (splitString.length == 1) {
            return true;
        }

        if (splitString.length != 2) {
            return false;
        }

        String secondString = splitString[1];
        if (secondString.isEmpty()) {
            return true;
        }

        return secondString.length() <= maxDp;
    }

    /**
     * Checks if a string can be converted to a double.
     *
     * @param string The string to check.
     * @return true if string can convert to double, else false.
     */
    public static boolean isStringDouble(String string) {
        if (string == null || string.isEmpty()) {
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
     * Checks if a string can be converted to a double and the no. of decimal places.
     *
     * @param string The string to check.
     * @param maxDp The no. of decimal places to allow.
     * @return true if string can be converted and is within the no. of decimal places allowed.
     */
    public static boolean isStringDouble(String string, int maxDp) {
        if (string == null || string.isEmpty()) {
            return false;
        }

        boolean isDouble;
        try {
            double value = Double.parseDouble(string);
            isDouble = checkDecimalPlaces(string, maxDp);
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
        if (string == null || string.isEmpty()) {
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
            if (parentFile != null) {
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

    public static double setPrecision(double value, int dp) {
        String stringFormat = String.format("%%.%df", dp);
        String string = String.format(stringFormat, value);

        if (isStringDouble(string, dp)) {
            return Double.parseDouble(string);
        }

        return 0.0;
    }
}
