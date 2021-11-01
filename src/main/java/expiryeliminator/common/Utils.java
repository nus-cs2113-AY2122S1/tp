package expiryeliminator.common;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Contains shared utility methods.
 */
public class Utils {
    /**
     * Returns a string converted to title case.
     *
     * @param string String to be converted to title case.
     * @return Title case string.
     */
    public static String toTitleCase(String string) {
        //@@author bernardboey-reused
        // Referenced from https://www.baeldung.com/java-string-title-case
        assert string != null;
        return Arrays.stream(string.toLowerCase().split(" "))
                .filter(word -> !word.isEmpty())
                .map(word -> Character.toUpperCase(word.charAt(0)) + word.substring(1))
                .collect(Collectors.joining(" "));
        //@@author
    }

    //@@author JoshHDs

    /**
     * Checks whether a string is alphabetic (i.e. only contains letters).
     *
     * @param string String to be checked.
     * @return True if the string only contains letters; false otherwise.
     */
    public static Boolean isAlphabetic(String string) {
        assert string != null;
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (!Character.isLetter(c) && !Character.isSpaceChar(c)) {
                return false;
            }
        }
        return true;
    }
    //@@author
}
