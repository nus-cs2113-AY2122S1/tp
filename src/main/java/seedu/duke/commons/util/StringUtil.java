package seedu.duke.commons.util;

//@@author richwill28
public class StringUtil {
    /**
     * Determines whether the input string is a valid verbose flag.
     *
     * @param userResponse the input string
     * @return true if "verbose" or "-v" (case-insensitive), otherwise false
     */
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

    /**
     * Splits a string into an array of strings, each with
     * the specified length.
     *
     * @param string the input string
     * @param length the specified length
     * @return an array of strings, each with the specified length
     */
    public static String[] splitToLength(String string, int length) {
        return string.replaceAll("\\r\\n|\\r|\\n", " ").split("(?<=\\G.{" + length + "})");
    }
}
