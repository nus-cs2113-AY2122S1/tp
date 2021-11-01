package seedu.duke.commons.util;

//@@author richwill28
public class StringUtil {
    public static String[] splitToLength(String string, int length) {
        return string.replaceAll("\\r\\n|\\r|\\n", " ").split("(?<=\\G.{" + length + "})");
    }
}
