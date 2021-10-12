package seedu.duke.utility;

public class Utility {
    private static final String INTEGER_REGEX = "^\\d+$";

    public static boolean isInteger(String string) {
        return string.matches(INTEGER_REGEX);
    }

    private static final String FUNCTION_SHOULD_NOT_REACH_END_ASSERTION
            = "Execution should not reach the end of function.";
}
