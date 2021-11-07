package seedu.duke.commons.util;

//@@author richwill28
public class StringUtil {
    public static boolean isVerbose(String userResponse) {
        return userResponse.equalsIgnoreCase("verbose")
                || userResponse.equalsIgnoreCase("-v");
    }

    public static String removeFirstParam(String userResponse, String firstParam) {
        return userResponse.replaceFirst(firstParam, "").strip();
    }

    public static String[] splitToLength(String string, int length) {
        return string.replaceAll("\\r\\n|\\r|\\n", " ").split("(?<=\\G.{" + length + "})");
    }
}
