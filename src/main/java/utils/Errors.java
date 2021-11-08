package utils;

/**
 * Error-related utilities.
 */
public class Errors {
    private Errors() {
        // Disallow instantiation
    }

    /**
     * Prints out an error message.
     *
     * @param errorMessage the error message
     */
    public static void print(String errorMessage) {
        print(errorMessage, true);
    }

    /**
     * Prints out an error message.
     *
     * @param errorMessage  the error message
     * @param appendNewLine whether a new line is appended or not
     */
    public static void print(String errorMessage, boolean appendNewLine) {
        System.err.println(errorMessage);
        if (appendNewLine) {
            System.err.println();
        }
    }

    /**
     * Prints out an error message with the original erroneous command.
     *
     * @param source       the original erroneous command
     * @param errorMessage the error message
     */
    public static void print(String source, String errorMessage) {
        print(source, errorMessage, true);
    }

    /**
     * Prints out an error message with the original erroneous command.
     *
     * @param source        the original erroneous command
     * @param errorMessage  the error message
     * @param appendNewLine whether a new line is appended or not
     */
    public static void print(String source, String errorMessage, boolean appendNewLine) {
        System.err.printf("%s: %s\n", source, errorMessage);
        System.err.println();
    }
}

