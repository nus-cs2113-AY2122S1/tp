package seedu.duke.commons.core;

import seedu.duke.commons.core.exceptions.PriorityException;

//@@author ptejasv
public enum Priority {
    L, LOW, M, MEDIUM, H, HIGH;

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
     * Returns a properly capitalised priority type.
     *
     * @param param the priority with any capitalisation
     * @return either "LOW", "MEDIUM", or "HIGH"
     */
    public static String toProper(String param) throws PriorityException {
        if (!is(param)) {
            throw new PriorityException(Message.ERROR_INVALID_PRIORITY);
        }

        switch (param.toUpperCase()) {
        case "L":
            // Fallthrough
        case "LOW":
            return "LOW";
        case "M":
            // Fallthrough
        case "MEDIUM":
            return "MEDIUM";
        case "H":
            // Fallthrough
        case "HIGH":
            return "HIGH";
        default:
            throw new PriorityException(Message.ERROR_INVALID_PRIORITY);
        }
    }
}
