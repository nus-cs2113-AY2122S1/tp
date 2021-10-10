package expiryeliminator.common;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Utils {
    //@@author bernardboey-reused
    // Referenced from https://www.baeldung.com/java-string-title-case
    public static String toTitleCase(String string) {
        return Arrays.stream(string.toLowerCase().split(" "))
                .filter(word -> !word.isEmpty())
                .map(word -> Character.toUpperCase(word.charAt(0)) + word.substring(1))
                .collect(Collectors.joining(" "));
    }
    //@@author
}
