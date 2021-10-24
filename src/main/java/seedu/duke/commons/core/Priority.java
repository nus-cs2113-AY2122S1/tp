package seedu.duke.commons.core;

public enum Priority {
    LOW, MEDIUM, HIGH, NONE;

    public static boolean is(String param) {
        for (Priority priority : Priority.values()) {
            if (param.equalsIgnoreCase(priority.toString())) {
                return true;
            }
        }
        return false;
    }

    public static String toProper(String param) {
        return param.substring(0, 1).toUpperCase() + param.substring(1).toLowerCase();
    }
}
