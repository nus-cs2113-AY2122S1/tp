package seedu.duke.parser;

import seedu.duke.command.CommandType;

public enum DayOfTheWeek {
    MON, TUE, WED, THU, FRI, SAT, SUN;

    /**
     * Determines if the input parameter is a day of the week.
     *
     * @param param the input parameter
     * @return true if it is a day, false otherwise
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
