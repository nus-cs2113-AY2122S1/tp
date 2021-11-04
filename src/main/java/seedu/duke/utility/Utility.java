package seedu.duke.utility;

public class Utility {
    private static final String INTEGER_REGEX = "^\\d+$";

    /**
     * Returns whether <code>toCheck</code> is an <code>int</code>.
     *
     * @return <code>boolean toCheck</code> is <code>int</code>?
     */
    public static boolean isInteger(String toCheck) {
        return toCheck.matches(INTEGER_REGEX);
    }
}
