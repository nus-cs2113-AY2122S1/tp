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
        return Arrays.stream(string.toLowerCase().split(" "))
                .filter(word -> !word.isEmpty())
                .map(word -> Character.toUpperCase(word.charAt(0)) + word.substring(1))
                .collect(Collectors.joining(" "));
        //@@author
    }
}
