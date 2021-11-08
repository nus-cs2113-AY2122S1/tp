package seedu.duke.commons.util;

//@@author richwill28
public class StringUtil {
    public static boolean isVerbose(String userResponse) {
        return userResponse.equalsIgnoreCase("verbose")
                || userResponse.equalsIgnoreCase("-v");
    }

    /**
     * Remove the first occurence of the specified parameter
     * (case-insensitive) from the user response.
     *
     * @param userResponse user response
     * @param firstParam first parameter of the user response
     * @return modified user response with the first parameter removed
     */
    public static String removeFirstParam(String userResponse, String firstParam) {
        return userResponse.replaceFirst("(?i)" + firstParam, "").strip();
    }

    public static String[] splitToLength(String string, int length) {
        return string.replaceAll("\\r\\n|\\r|\\n", " ").split("(?<=\\G.{" + length + "})");
    }
}
