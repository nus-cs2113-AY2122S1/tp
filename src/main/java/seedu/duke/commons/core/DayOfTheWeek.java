package seedu.duke.commons.core;

import seedu.duke.commons.core.exceptions.DayOfTheWeekException;

//@@author richwill28
public enum DayOfTheWeek {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY;

    /**
     * Determines if the input parameter is a valid day of the week.
     *
     * @param param the input parameter
     * @return true if the input is valid, false otherwise
     */
    public static boolean is(String param) {
        if (param.length() < 3) {
            return false;
        }

        for (DayOfTheWeek day : DayOfTheWeek.values()) {
            String substring;
            try {
                substring = day.toString().substring(0, param.length());
            } catch (IndexOutOfBoundsException e) {
                continue;   // Ignore and check next value
            }

            if (param.equalsIgnoreCase(substring)) {
                return true;
            }
        }
        return false;
    }

    public static String toProper(String param) throws DayOfTheWeekException {
        if (!is(param)) {
            throw new DayOfTheWeekException(Messages.ERROR_INVALID_DAY);
        }

        switch (param.substring(0, 3).toLowerCase()) {
        case "mon":
            return "Monday";
        case "tue":
            return "Tuesday";
        case "wed":
            return "Wednesday";
        case "thu":
            return "Thursday";
        case "fri":
            return "Friday";
        case "sat":
            return "Saturday";
        case "sun":
            return "Sunday";
        default:
            throw new DayOfTheWeekException(Messages.ERROR_INVALID_DAY);
        }
    }
}
