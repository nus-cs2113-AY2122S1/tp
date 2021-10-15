package seedu.duke.commons.core;

public enum DayOfTheWeek {
    MON, TUE, WED, THU, FRI, SAT, SUN;

    /**
     * Determines if the input parameter is a valid day of the week.
     *
     * @param param the input parameter
     * @return true if the input is valid, false otherwise
     */
    public static boolean is(String param) {
        for (DayOfTheWeek day : DayOfTheWeek.values()) {
            if (param.equalsIgnoreCase(day.toString())) {
                return true;
            }
        }
        return false;
    }
}
