package seedu.duke.commons.core;

import java.time.LocalDate;

public enum DayOfTheWeek {
    MON, TUE, WED, THU, FRI, SAT, SUN;

    public static final String TODAY_PERIOD = "today";
    public static final String TOMORROW_PERIOD = "tomorrow";

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

    /**
     * Checks if period given is "today" or "tomorrow".
     *
     * @param period the given period
     * @return true if period is "today" or "tomorrow", false otherwise
     */
    public static boolean isTodayOrTomorrow(String period) {
        return period.equalsIgnoreCase(TODAY_PERIOD) | period.equalsIgnoreCase(TOMORROW_PERIOD);
    }

    /**
     * Returns the DayOfTheWeek for today.
     *
     * @return DayOfTheWeek for today in lowercase
     */
    public static String getCurrentDayOfWeek() {
        LocalDate currentDate = LocalDate.now();
        String currentDayOfWeek = currentDate.getDayOfWeek().toString();
        return currentDayOfWeek.substring(0,3).toLowerCase();
    }

    /**
     * Returns the DayOfTheWeek for tomorrow.
     *
     * @return DayOfTheWeek for tomorrow in lowercase
     */
    public static String getNextDayOfWeek() {
        LocalDate currentDate = LocalDate.now().plusDays(1);
        String currentDayOfWeek = currentDate.getDayOfWeek().toString();
        return currentDayOfWeek.substring(0,3).toLowerCase();
    }
}
