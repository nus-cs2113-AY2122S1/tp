package seedu.duke.utility;

public class Utility {
    private static final String INTEGER_REGEX = "^\\d+$";

    public static boolean isInteger(String string) {
        return string.matches(INTEGER_REGEX);
    }
}
