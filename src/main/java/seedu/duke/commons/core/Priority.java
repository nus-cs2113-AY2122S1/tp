package seedu.duke.commons.core;

public enum Priority {
    LOW, MEDIUM, HIGH, NONE;

    /**
     * Determines if the input parameter is a valid priority type.
     *
     * @param param the input parameter
     * @return true if the input is valid, false otherwise
     */
    public static boolean is(String param) {
        for (Priority priority : Priority.values()) {
            if (param.equalsIgnoreCase(priority.toString())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a properly capitalised priority type
     *
     * @param param the priority with any capitalisation
     * @return either "Low", "Medium", "High" or "None"
     */
    public static String toProper(String param) {
        return param.substring(0, 1).toUpperCase() + param.substring(1).toLowerCase();
    }
}
